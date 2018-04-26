package com.sky.tzsc.tzscStzjgl.persistence.dao.impl;
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.sky.tzsc.tzscStzjgl.persistence.dao.TzscStzjglDao;
import com.sky.tzsc.tzscStzjgl.persistence.model.TzscStzjgl;

/**
 * 
 * <pre> 
 * 描述：[审图专家管理]数据处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-01-16 17:07:42
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Repository
public class TzscStzjglDaoImpl extends MyBatisDaoImpl<String, TzscStzjgl> implements TzscStzjglDao{

    @Override
    public String getNamespace() {
        return TzscStzjgl.class.getName();
    }
}



