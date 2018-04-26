package com.sky.tzsc.yytzsc.persistence.dao.impl;
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.sky.tzsc.yytzsc.persistence.dao.ZgxxYyDao;
import com.sky.tzsc.yytzsc.persistence.model.ZgxxYy;

/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查整改信息]数据处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-01-11 09:38:35
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Repository
public class ZgxxYyDaoImpl extends MyBatisDaoImpl<String, ZgxxYy> implements ZgxxYyDao{

    @Override
    public String getNamespace() {
        return ZgxxYy.class.getName();
    }
}



