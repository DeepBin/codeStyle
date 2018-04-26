package com.sky.tzsc.gntzsc.persistence.dao.impl;
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.sky.tzsc.gntzsc.persistence.dao.SjdwDao;
import com.sky.tzsc.gntzsc.persistence.model.Sjdw;

/**
 * 
 * <pre> 
 * 描述：[图纸审查设计单位信息表]数据处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-04-08 14:32:58
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Repository
public class SjdwDaoImpl extends MyBatisDaoImpl<String, Sjdw> implements SjdwDao{

    @Override
    public String getNamespace() {
        return Sjdw.class.getName();
    }
}



