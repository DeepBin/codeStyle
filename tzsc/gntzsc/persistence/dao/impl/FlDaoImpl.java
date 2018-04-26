package com.sky.tzsc.gntzsc.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.sky.tzsc.gntzsc.persistence.dao.FlDao;
import com.sky.tzsc.gntzsc.persistence.model.Fl;

/**
 * 
 * <pre> 
 * 描述：[tzsc_fl]数据处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-03-26 16:08:22
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Repository
public class FlDaoImpl extends MyBatisDaoImpl<String, Fl> implements FlDao{

    @Override
    public String getNamespace() {
        return Fl.class.getName();
    }
}



