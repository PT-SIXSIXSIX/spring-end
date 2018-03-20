package com.PT.service.impl;

import com.PT.dao.StaffMapper;
import com.PT.dao.UserMapper;
import com.PT.dao.YkatCommonUtilMapper;
import com.PT.entity.User;
import com.PT.entity.UserExample;
import com.PT.service.LogService;
import com.PT.service.StaffService;
import com.PT.tools.PasswordUtil;
import com.PT.tools.QueryToMap;
import com.PT.tools.RegexUtil;
import com.PT.tools.YkatConstant;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private YkatCommonUtilMapper ykatCommonUtilMapper;

    /**
     * 查找店员
     * @param userId 唯一用户标识
     * @param page 页数
     * @param ipp 每页数据量
     * @param queryCondition 查询语句
     * @return
     */
    public Map<String , Object> listStaff(int userId, int page, int ipp, String queryCondition){

        PageHelper.startPage(page,ipp);

        Map factors = new HashMap();
        if(queryCondition!=null && !"".equals(queryCondition)){
            factors.put("content",queryCondition);
        }

        factors.put("id",userId);
        List<Map> staffs = staffMapper.selectByFactor(factors);
        PageHelper.clearPage();
        int maxPage = (staffMapper.countByFactor(factors)-1)/ipp + 1;
        Map<String,Object> map = new HashMap<>();
        map.put("maxPage",maxPage);
        map.put("staffs",staffs);
        return map;
    }

    /**
     * 添加店员
     * @param userId 唯一用户标识
     * @param factors 参数 ，密码，姓名，电话
     * @throws Exception
     */
    @Transactional
    @Override
    public void addStaff(int userId, Map factors) throws Exception{
        String name=null,password=null,phone=null;
        if(factors.containsKey("name") && factors.containsKey("password") && factors.containsKey("phone")){
            name = (String) factors.get("name"); password = (String) factors.get("password"); phone = (String) factors.get("phone");
            password = PasswordUtil.MD5Encode(password, "utf-8", false);
        }else{
            throw new Exception("参数缺失");
        }

        if(ykatCommonUtilMapper.getUserIdByPhone(phone)!=null){
            throw new Exception("手机号已被使用");
        }

        if(RegexUtil.find(YkatConstant.illegalCharacterRegex,name)){
            throw new Exception("姓名包含非法字符");
        }

        User record = new User();
        record.setPhone(phone);
        record.setName(name);
        record.setPassword(password);
        record.setRole(1);//店员标志
        try {
            staffMapper.insertStaffSelective(record); // 在ykat_user表中 插入数据。 返回主键
        }catch (Exception e){
            throw new Exception("添加失败");
        }

        int recordId;
        if( ( recordId = record.getId() )>0){
            Map map = new HashMap();
            map.put("userId",userId);
            map.put("staffId",recordId);
            staffMapper.insertRelation(map);
        }
    }

    /**
     * 删除员工
     * @param userId 唯一用户表示
     * @param staffId 员工唯一用户标识
     */
    @Transactional
    @Override
    public void deleteByStaffId(int userId, int staffId) throws  Exception{
        int res = staffMapper.deleteRelationByUserId(staffId);
        Integer role = ykatCommonUtilMapper.getRoleByUserId(staffId);
        if (!role.equals(YkatConstant.USER_ROLE_STAFF)){
            throw new Exception("要删除的用户不是店员");
        }
        userMapper.deleteByPrimaryKey(staffId);
        logService.insertLog(userId,"delete","on table ykat_users:"+"by id = "+staffId);
    }

    @Transactional
    @Override
    /**
     * 更新员工信息
     * @param userId 唯一用户表示
     * @param staffId 要更新信息的员工标识
     * @param factor 更新参数， 包含电话，姓名
     */
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
