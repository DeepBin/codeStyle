package com.sky.tzsc.yytzsc.persistence.dao.impl;
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.sky.tzsc.yytzsc.persistence.dao.YyClshyjDao;
import com.sky.tzsc.yytzsc.persistence.model.Clshyj;

/**
 * 
 * <pre> 
 * 描述：[远洋图纸审查材料审核意见]数据处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-07 10:34:07
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Repository
public class YyClshyjDaoImpl extends MyBatisDaoImpl<String, Clshyj> implements YyClshyjDao{

    @Override
    public String getNamespace() {
        return Clshyj.class.getName();
    }
}



