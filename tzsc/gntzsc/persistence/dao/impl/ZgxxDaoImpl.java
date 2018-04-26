package com.sky.tzsc.gntzsc.persistence.dao.impl;
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.sky.tzsc.gntzsc.persistence.dao.ZgxxDao;
import com.sky.tzsc.gntzsc.persistence.model.Zgxx;

/**
 * 
 * <pre> 
 * 描述：[国内图纸审查整改信息]数据处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-01-08 10:10:23
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Repository
public class ZgxxDaoImpl extends MyBatisDaoImpl<String, Zgxx> implements ZgxxDao{

    @Override
    public String getNamespace() {
        return Zgxx.class.getName();
    }
}



