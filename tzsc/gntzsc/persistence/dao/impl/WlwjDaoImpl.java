package com.sky.tzsc.gntzsc.persistence.dao.impl;
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.sky.tzsc.gntzsc.persistence.dao.WlwjDao;
import com.sky.tzsc.gntzsc.persistence.model.Wlwj;

/**
 * 
 * <pre> 
 * 描述：[tzsc_wlwj]数据处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-24 16:34:38
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Repository
public class WlwjDaoImpl extends MyBatisDaoImpl<String, Wlwj> implements WlwjDao{

    @Override
    public String getNamespace() {
        return Wlwj.class.getName();
    }
}



