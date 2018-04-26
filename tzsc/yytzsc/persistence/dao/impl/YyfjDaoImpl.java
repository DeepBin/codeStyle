package com.sky.tzsc.yytzsc.persistence.dao.impl;
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.sky.tzsc.yytzsc.persistence.dao.YyfjDao;
import com.sky.tzsc.yytzsc.persistence.model.Yyfj;

/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查附件表]数据处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-07 14:47:11
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Repository
public class YyfjDaoImpl extends MyBatisDaoImpl<String, Yyfj> implements YyfjDao{

    @Override
    public String getNamespace() {
        return Yyfj.class.getName();
    }
}



