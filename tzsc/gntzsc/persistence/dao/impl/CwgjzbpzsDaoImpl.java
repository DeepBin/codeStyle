package com.sky.tzsc.gntzsc.persistence.dao.impl;
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.sky.tzsc.gntzsc.persistence.dao.CwgjzbpzsDao;
import com.sky.tzsc.gntzsc.persistence.model.Cwgjzbpzs;

/**
 * 
 * <pre> 
 * 描述：[tzsc_cwgjzbpzs]数据处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-24 14:39:22
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Repository
public class CwgjzbpzsDaoImpl extends MyBatisDaoImpl<String, Cwgjzbpzs> implements CwgjzbpzsDao{

    @Override
    public String getNamespace() {
        return Cwgjzbpzs.class.getName();
    }
}



