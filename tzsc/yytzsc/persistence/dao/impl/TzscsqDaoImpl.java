package com.sky.tzsc.yytzsc.persistence.dao.impl;
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.sky.tzsc.yytzsc.persistence.dao.TzscsqDao;
import com.sky.tzsc.yytzsc.persistence.model.Tzscsq;

/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查申请表]数据处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-07 10:28:56
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Repository
public class TzscsqDaoImpl extends MyBatisDaoImpl<String, Tzscsq> implements TzscsqDao{

    @Override
    public String getNamespace() {
        return Tzscsq.class.getName();
    }
}



