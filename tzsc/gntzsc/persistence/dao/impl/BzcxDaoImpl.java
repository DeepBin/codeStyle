package com.sky.tzsc.gntzsc.persistence.dao.impl;
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.sky.tzsc.gntzsc.persistence.dao.BzcxDao;
import com.sky.tzsc.gntzsc.persistence.model.Bzcx;

/**
 * 
 * <pre> 
 * 描述：[tzsc_bzcx]数据处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-20 15:37:20
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Repository
public class BzcxDaoImpl extends MyBatisDaoImpl<String, Bzcx> implements BzcxDao{

    @Override
    public String getNamespace() {
        return Bzcx.class.getName();
    }
}



