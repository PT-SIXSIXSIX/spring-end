package com.PT.service.impl;

import com.PT.bean.Staff.StaffInfoBean;
import com.PT.dao.StaffMapper;
import com.PT.dao.UserMapper;
import com.PT.service.StaffService;
import com.PT.tools.QueryToMap;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private UserMapper userMapper;
    public Map<String , Object> listStaff(int userId, int page, int ipp, String queryCondition){

        PageHelper.startPage(page,ipp);

        Map factors = new HashMap();
        if(queryCondition!=null && !"".equals(queryCondition)){
            factors = QueryToMap.stringToMap(queryCondition);
        }

        factors.put("id",userId);
        List<Map> staffs = staffMapper.selectByFactor(factors);
        PageHelper.clearPage();
        int maxPage = (staffMapper.countByFactor(factors)-1)/ipp + 1;
        Map<String,Object> map = new HashMap<>();
        map.put("maxPage",maxPage);
        map.put("records",staffs);
        return map;
    }

    public void deleteByIStaffId(int staffId){
        int res = staffMapper.deleteRelationByUserId(staffId);
        userMapper.deleteByPrimaryKey(staffId);
    }
}
