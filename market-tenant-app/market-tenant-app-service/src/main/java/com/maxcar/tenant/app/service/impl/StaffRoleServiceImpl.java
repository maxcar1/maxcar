package com.maxcar.tenant.app.service.impl;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.tenant.app.bean.StaffBindRoleBean;
import com.maxcar.tenant.app.bean.StaffRoleCheckBean;
import com.maxcar.tenant.app.dao.StaffCheckMapper;
import com.maxcar.tenant.app.dao.StaffRoleMapper;
import com.maxcar.tenant.app.entity.StaffCheck;
import com.maxcar.tenant.app.entity.StaffCheckExample;
import com.maxcar.tenant.app.entity.StaffRole;
import com.maxcar.tenant.app.entity.StaffRoleExample;
import com.maxcar.tenant.app.service.StaffRoleService;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.service.StaffService;
import org.apache.xmlbeans.impl.schema.StscChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author songxuefeng
 * @create 2018-10-15 10:30
 * @description: ${description}
 **/
@Service("staffRoleService")
public class StaffRoleServiceImpl implements StaffRoleService {
    @Autowired
    private StaffRoleMapper staffRoleMapper;
    @Autowired
    private StaffCheckMapper staffCheckMapper;
    @Autowired
    private StaffService staffService;



    @Override
    public int deleteByStaffId(String staffId) {
        try {
            StaffRoleExample staffRoleExample =new StaffRoleExample();
            staffRoleExample.createCriteria().andStaffIdEqualTo(staffId);
            staffRoleMapper.deleteByExample(staffRoleExample);

            StaffCheckExample staffCheckExample=new StaffCheckExample();
            staffCheckExample.createCriteria().andStaffIdEqualTo(staffId);
            staffCheckMapper.deleteByExample(staffCheckExample);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int selectStaffCountByRole(String id) {
        StaffRoleExample example=new StaffRoleExample();
        example.createCriteria().andRoleIdEqualTo(id);
        return staffRoleMapper.countByExample(example);
    }

    @Override
    public String getRoleIdByStaffId(String staffId) {
        return staffRoleMapper.getRoleIdByStaffId(staffId);
    }

    @Override
    public InterfaceResult staffRelRole(StaffBindRoleBean bean) {

        InterfaceResult result = new InterfaceResult();
        String staffId=bean.getStaffId();
        String roleId=bean.getRoleId();

        //判断员工与岗位是否已被关联
        StaffRoleExample example=new StaffRoleExample();
        example.createCriteria().andStaffIdEqualTo(staffId).andRoleIdEqualTo(roleId);
        List<StaffRole> staffRoles = staffRoleMapper.selectByExample(example);
        if (bean.getRelation()==0){//解除关联
            if (staffRoles!=null&&staffRoles.size()>0){
                staffRoleMapper.deleteByExample(example);
            }
        }else if (bean.getRelation()==1){//关联
            if (staffRoles!=null&&staffRoles.size()>0){
                result.InterfaceResult600("该员工已被关联");
            }else {
                StaffRole staffRole=new StaffRole();
                staffRole.setId(UuidUtils.generateIdentifier());
                staffRole.setRoleId(roleId);
                staffRole.setStaffId(staffId);
                staffRoleMapper.insertSelective(staffRole);
            }
        }
        return result;
    }

    @Override
    public InterfaceResult staffRoleCheck(StaffRoleCheckBean bean) {
        InterfaceResult result = new InterfaceResult();
        StaffCheck staffCheck = staffCheckMapper.selectByPrimaryKey(bean.getStaffCheckId());
        if (staffCheck!=null){
            byte b=-1;
            if (bean.getIsCheck()==-1){//拒绝
                staffCheck.setState(b);
                staffCheck.setRemark(bean.getRefuseReason());
                staffCheck.setGmtModified(new Date());
                staffCheckMapper.updateByPrimaryKeySelective(staffCheck);
            }else if (bean.getIsCheck()==1){//同意
                b=1;
                staffCheck.setState(b);
                staffCheck.setGmtModified(new Date());
                staffCheckMapper.updateByPrimaryKeySelective(staffCheck);

                //员工信息完善
                Staff staff=new Staff();
                staff.setId(staffCheck.getStaffId());
                staff.setMarketId(staffCheck.getMarketId());
                staff.setGroupId(staffCheck.getTenantId());
                staff.setUpdateTime(new Date());
                staffService.updateStaff(staff);

                //建立员工与角色关系
                StaffRole staffRole=new StaffRole();
                staffRole.setId(UuidUtils.generateIdentifier());
                staffRole.setStaffId(staffCheck.getStaffId());
                staffRole.setRoleId(staffCheck.getRoleId());
                staffRoleMapper.insertSelective(staffRole);
            }
        }else {
            result.InterfaceResult600("改员工审批数据不存在");
        }
        return result;
    }
}
