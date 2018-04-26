package com.sky.tzsc.gntzsc.persistence.dao.impl;
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.sky.tzsc.gntzsc.persistence.dao.BzcxHistoryDao;
import com.sky.tzsc.gntzsc.persistence.model.BzcxHistory;

/**
 * 
 * <pre> 
 * 描述：[tzsc_bzcx_history]数据处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-02-26 09:17:18
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Repository
public class BzcxHistoryDaoImpl extends MyBatisDaoImpl<String, BzcxHistory> implements BzcxHistoryDao{

    @Override
    public String getNamespace() {
        return BzcxHistory.class.getName();
    }
}



