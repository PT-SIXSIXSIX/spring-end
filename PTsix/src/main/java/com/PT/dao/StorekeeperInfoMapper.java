package com.PT.dao;

import com.PT.bean.Storekeeper.StorekeeperInfoBean;

public interface StorekeeperInfoMapper {
    StorekeeperInfoBean selectByIdCard(StorekeeperInfoBean info);
}
