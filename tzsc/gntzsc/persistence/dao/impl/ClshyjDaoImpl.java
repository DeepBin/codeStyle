package com.sky.tzsc.gntzsc.persistence.dao.impl;
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.sky.tzsc.gntzsc.persistence.dao.ClshyjDao;
import com.sky.tzsc.gntzsc.persistence.model.Clshyj;

/**
 * 
 * <pre> 
 * 描述：[材料审核意见]数据处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-12-01 10:33:48
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Repository
public class ClshyjDaoImpl extends MyBatisDaoImpl<String, Clshyj> implements ClshyjDao{

    @Override
    public String getNamespace() {
        return Clshyj.class.getName();
    }
}



