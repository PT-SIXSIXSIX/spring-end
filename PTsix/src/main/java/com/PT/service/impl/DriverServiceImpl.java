package com.PT.service.impl;

import com.PT.dao.DriverMapper;
import com.PT.entity.Driver;
import com.PT.entity.DriverExample;
import com.PT.service.DriverService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverMapper driverMapper;
    @Override
    public Map<String, Object> getDriverMessage() {
        DriverExample example = new DriverExample();
        example.setOrderByClause("id");
        Map map = new HashMap<String, Object>();
        try {
            Integer page = 1, ipp = 5;
            PageHelper.startPage(page, ipp);
            List driverList = driverMapper.selectByExample(example);
            int maxPage = (driverMapper.countByExample(example)-1)/ipp+1;
            map.put("drivers", driverList);
            map.put("maxPage", maxPage);
            return map;
        } catch (Exception e) {
            throw e;
        }
    }
}
