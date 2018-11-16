package com.maxcar.tenant.app.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.StringUtil;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.tenant.app.bean.RoleResBean;
import com.maxcar.tenant.app.dao.StaffCheckMapper;
import com.maxcar.tenant.app.dao.StaffRoleMapper;
import com.maxcar.tenant.app.dao.TenantRoleMapper;
import com.maxcar.tenant.app.dao.TenantRoleResMapper;
import com.maxcar.tenant.app.entity.*;
import com.maxcar.tenant.app.service.TenantRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author songxuefeng
 * @create 2018-10-15 11:38
 * @description: ${description}
 **/
@Service("tenantRoleService")
public class TenantRoleServiceImpl extends BaseServiceImpl<TenantRole, String> implements TenantRoleService {

    @Autowired
    private TenantRoleMapper tenantRoleMapper;
    @Autowired
    private TenantRoleResMapper tenantRoleResMapper;
    @Autowired
    private StaffRoleMapper staffRoleMapper;
    @Autowired
    private StaffCheckMapper staffCheckMapper;

    @Override
    public BaseDao<TenantRole, String> getBaseMapper() {
        return tenantRoleMapper;
    }

    @Override
    public List<TenantRole> selectRoleByTenantId(String groupId) {
        TenantRoleExample example=new TenantRoleExample();
        example.createCriteria().andTenantIdEqualTo(groupId).andIsvalidEqualTo((byte)1);
        return tenantRoleMapper.selectByExample(example);
    }

    @Override
    public InterfaceResult addRoleAndRes(TenantRole role) {
        InterfaceResult result = new InterfaceResult();
        tenantRoleMapper.insertSelective(role);
        role.getResIds().forEach(resId->{
            TenantRoleRes tenantRoleRes=new TenantRoleRes();
            tenantRoleRes.setId(UuidUtils.generateIdentifier());
            tenantRoleRes.setResId(resId);
            tenantRoleRes.setRoleId(role.getId());
            tenantRoleResMapper.insertSelective(tenantRoleRes);
        });
        return result;
    }

    @Override
    public List<TenantRole> getRoleListByTenant(String tenantId) {
        return tenantRoleMapper.getRoleListByTenant(tenantId);
    }

    @Override
    public TenantRole getRoleAndStaffAndRes(String roleId) {
        return tenantRoleMapper.getRoleAndStaffAndRes(roleId);
    }

    @Override
    public InterfaceResult roleResRelation(RoleResBean bean) {
        InterfaceResult result = new InterfaceResult();
        TenantRoleResExample example=new TenantRoleResExample();
        example.createCriteria().andRoleIdEqualTo(bean.getRoleId());
        tenantRoleResMapper.deleteByExample(example);

        bean.getResIds().forEach(s->{
                TenantRoleRes roleRes=new TenantRoleRes();
                roleRes.setId(UuidUtils.generateIdentifier());
                roleRes.setRoleId(bean.getRoleId());
                roleRes.setResId(s);
                tenantRoleResMapper.insertSelective(roleRes);
        });


//        if (bean.getRelation()==0){//解除
//            bean.getResIds().forEach(s->{
//                TenantRoleResExample example=new TenantRoleResExample();
//                example.createCriteria().andRoleIdEqualTo(bean.getRoleId()).andResIdEqualTo(s);
//                tenantRoleResMapper.deleteByExample(example);
//            });
//
//        }else if (bean.getRelation()==1){//关联
//            bean.getResIds().forEach(s->{
//                TenantRoleRes roleRes=new TenantRoleRes();
//                roleRes.setId(UuidUtils.generateIdentifier());
//                roleRes.setRoleId(bean.getRoleId());
//                roleRes.setResId(s);
//                tenantRoleResMapper.insertSelective(roleRes);
//            });
//        }else {
//            result.InterfaceResult600("没选择是否关联");
//        }
        return result;
    }

    @Override
    public InterfaceResult updateRole(TenantRole role) {
        InterfaceResult result = new InterfaceResult();
        if (role.getId()!=null){
            TenantRole tenantRole=tenantRoleMapper.selectByPrimaryKey(role.getId());
            if (tenantRole!=null){
                if (StringUtil.isNotEmpty(role.getRoleName())){
                    tenantRole.setRoleName(role.getRoleName());
                }
                if (StringUtil.isNotEmpty(role.getRoleDesc())){
                    tenantRole.setRoleDesc(role.getRoleDesc());
                }
                tenantRole.setGmtModified(new Date());
                tenantRoleMapper.updateByPrimaryKeySelective(tenantRole);
            }else {
                result.InterfaceResult600("该角色不存在");
            }
        }else {
            result.InterfaceResult600("角色id参数不能为空");
        }
        return result;
    }

    @Override
    public InterfaceResult delRole(String roleId) {
        InterfaceResult result = new InterfaceResult();
        StaffRoleExample staffRoleExample =new StaffRoleExample();
        staffRoleExample.createCriteria().andRoleIdEqualTo(roleId);
        List<StaffRole> staffRoles = staffRoleMapper.selectByExample(staffRoleExample);
        if (staffRoles!=null&&staffRoles.size()>0){
            result.InterfaceResult600("该角色已被绑定 不能删除");
            return result;
        }

        //查看是否有待审批
        StaffCheckExample staffCheckExample=new StaffCheckExample();
        staffCheckExample.createCriteria().andRoleIdEqualTo(roleId).andStateEqualTo((byte)0);
        List<StaffCheck> staffChecks = staffCheckMapper.selectByExample(staffCheckExample);
        if (staffChecks!=null&&staffChecks.size()>0){
            result.InterfaceResult600("该角色下已有待审批员工 不能删除");
            return result;
        }

        //删除
        tenantRoleMapper.deleteByPrimaryKey(roleId);
        return result;
    }
}
