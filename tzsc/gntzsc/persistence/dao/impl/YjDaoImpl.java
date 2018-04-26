package com.sky.tzsc.gntzsc.persistence.dao.impl;
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.sky.tzsc.gntzsc.persistence.dao.YjDao;
import com.sky.tzsc.gntzsc.persistence.model.Yj;

/**
 * 
 * <pre> 
 * 描述：[tzsc_yj]数据处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-29 13:41:43
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Repository
public class YjDaoImpl extends MyBatisDaoImpl<String, Yj> implements YjDao{

    @Override
    public String getNamespace() {
        return Yj.class.getName();
    }
}



