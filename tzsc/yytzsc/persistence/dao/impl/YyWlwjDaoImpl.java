package com.sky.tzsc.yytzsc.persistence.dao.impl;
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.sky.tzsc.yytzsc.persistence.dao.YyWlwjDao;
import com.sky.tzsc.yytzsc.persistence.model.YyWlwj;

/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查往来文件表]数据处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-08 10:08:08
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Repository
public class YyWlwjDaoImpl extends MyBatisDaoImpl<String, YyWlwj> implements YyWlwjDao{

    @Override
    public String getNamespace() {
        return YyWlwj.class.getName();
    }
}



