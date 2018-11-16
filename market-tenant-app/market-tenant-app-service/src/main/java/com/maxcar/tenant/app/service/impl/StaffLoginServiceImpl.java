package com.maxcar.tenant.app.service.impl;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.Constants;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.redis.service.RedisService;
import com.maxcar.redis.util.CacheKey;
import com.maxcar.tenant.app.entity.StaffCheck;
import com.maxcar.tenant.app.service.StaffCheckService;
import com.maxcar.tenant.app.service.StaffLoginService;
import com.maxcar.tenant.app.vo.StaffLoginVo;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.service.StaffService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

@Service("staffLoginService")
public class StaffLoginServiceImpl implements StaffLoginService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffCheckService staffCheckService;

    @Override
    public InterfaceResult getStaffIdByToken(String staffToken) {
        InterfaceResult interfaceResult = new InterfaceResult();

        String staffTokenKey = MessageFormat.format(CacheKey.STAFF_TOKEN_UID, staffToken);
        String staffId = redisService.get(staffTokenKey);
        if (StringUtils.isEmpty(staffId)) {
            interfaceResult.setCode("60001");
            interfaceResult.setMsg("session已经过期，请重新登录");
            return interfaceResult;
        }

        redisService.expire(staffTokenKey, Constants.STAFF_TOKEN_TIMEOUT);

        interfaceResult.InterfaceResult200(staffId);
        return interfaceResult;
    }

    @Override
    public InterfaceResult login(String phoneNum) {
        InterfaceResult interfaceResult = new InterfaceResult();

        StaffLoginVo staffLoginVo = new StaffLoginVo();
        String token = UuidUtils.generateIdentifier();
        staffLoginVo.setStaffToken(token);

        List<Staff> staffList = staffService.getStaffByPhone(phoneNum, 2);
        if (CollectionUtils.isNotEmpty(staffList)) {
            Staff staff = staffList.get(0);
            // 账户被禁用
            if (staff.getIsValid() == 0) {
                staffLoginVo.setChecked(3);
                interfaceResult.InterfaceResult200(staffLoginVo);
                return interfaceResult;
            }

            if (staff.getIsAdmin() == 1) {
                staffLoginVo.setIsAdmin(staff.getIsAdmin());
                if (staffList.size() > 1) {
                    staffLoginVo.setSwitchTenant(1);
                    redisService.set(MessageFormat.format(CacheKey.STAFF_TOKEN_PHONE, token), phoneNum, 120);
                } else {
                    cacheToken(token, staff.getId(), phoneNum);
                }
            } else {
                // 审批结果
                StaffCheck staffCheck = staffCheckService.getStaffCheckByStaffId(staff.getId());
                if (staffCheck == null) {
                    staffLoginVo.setChecked(2);
                } else {
                    staffLoginVo.setChecked(staffCheck.getState());
                }
                cacheToken(token, staff.getId(), phoneNum);
            }

        } else {
            Staff staff = new Staff();
            String staffId = UuidUtils.generateIdentifier();
            staff.setId(staffId);
            staff.setStaffPhone(phoneNum);
            staff.setIsAdmin(0);
            staff.setInsertTime(new Date());
            staff.setStaffType(2);
            int row = staffService.insertSelective(staff);
            if (row == 0) {
                interfaceResult.InterfaceResult600("新增失败");
                return interfaceResult;
            }

            staffLoginVo.setChecked(2);
            cacheToken(token, staff.getId(), phoneNum);
        }

        interfaceResult.InterfaceResult200(staffLoginVo);
        return interfaceResult;
    }

    private void cacheToken(String token, String staffId, String phoneNum) {
        String staffToken = redisService.get(MessageFormat.format(CacheKey.PHONE_STAFF_TOKEN, phoneNum));
        if (StringUtils.isNotBlank(staffToken)) {
            redisService.del(MessageFormat.format(CacheKey.STAFF_TOKEN_UID, staffToken));
            redisService.set(MessageFormat.format(CacheKey.STAFF_TOKEN_MARK, staffToken), "1", Constants.STAFF_TOKEN_TIMEOUT);
        }

        redisService.set(MessageFormat.format(CacheKey.PHONE_STAFF_TOKEN, phoneNum), token);
        redisService.set(MessageFormat.format(CacheKey.STAFF_TOKEN_UID, token), staffId, Constants.STAFF_TOKEN_TIMEOUT);
    }
}
