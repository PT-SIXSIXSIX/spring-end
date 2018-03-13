package com.PT.service.impl;

import com.PT.dao.StaffMapper;
import com.PT.dao.UserMapper;
import com.PT.entity.User;
import com.PT.entity.UserExample;
import com.PT.service.LogService;
import com.PT.service.StaffService;
import com.PT.tools.PasswordUtil;
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

    @Autowired
    private LogService logService;

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


    public void addStaff(int userId, Map factors) throws Exception{
        String name=null,password=null,phone=null;
        if(factors.containsKey("name") && factors.containsKey("password") && factors.containsKey("phone")){
            name = (String) factors.get("name"); password = (String) factors.get("password"); phone = (String) factors.get("phone");
            password = PasswordUtil.MD5Encode(password, "utf-8", false);
        }else{
            throw new Exception("参数缺失");
        }

        User record = new User();
        record.setPhone(phone);
        record.setName(name);
        record.setPassword(password);
        record.setRole(1);//店员标志
        staffMapper.insertStaffSelective(record); // 在ykat_user表中 插入数据。 返回主键
        int recordId;
        if( ( recordId = record.getId() )>0){
            Map map = new HashMap();
            map.put("userId",userId);
            map.put("staffId",recordId);
            staffMapper.insertRelation(map);
        }
    }
    public void deleteByStaffId(int userId, int staffId){
        int res = staffMapper.deleteRelationByUserId(staffId);
        userMapper.deleteByPrimaryKey(staffId);
        logService.insertLog(userId,"delete","on table ykat_users:"+"by id = "+staffId);
    }

    public void updateByStaffId(int userId, int staffId, Map<String,String> factor){
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(staffId);

        User user = new User();

        if(factor.containsKey("name") && !"".equals(factor.get("name")))
            user.setName(factor.get("name"));
        if(factor.containsKey("phone") && !"".equals(factor.get("phone")))
            user.setPhone(factor.get("phone"));

        int updateRes = userMapper.updateByExampleSelective(user,example);
        if(updateRes > 0) {
            logService.insertLog(userId, "update", "on table ykat_users: by id = " + staffId);
        }
    }
}
