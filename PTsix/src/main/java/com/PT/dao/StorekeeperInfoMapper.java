package com.PT.dao;

import com.PT.bean.StorekeeperInfoBean;

public interface StorekeeperInfoMapper {
    StorekeeperInfoBean selectByIdCard(StorekeeperInfoBean info);
}
