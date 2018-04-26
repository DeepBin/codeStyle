package com.sky.tzsc.gntzsc.persistence.dao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.base.db.mybatis.domain.PageList;
import com.sky.tzsc.gntzsc.persistence.dao.TzsqDao;
import com.sky.tzsc.gntzsc.persistence.model.Tzsq;

/**
 * 
 * <pre> 
 * 描述：[tzsc_sq]数据处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-22 14:04:09
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Repository
public class TzsqDaoImpl extends MyBatisDaoImpl<String, Tzsq> implements TzsqDao{

    @Override
    public String getNamespace() {
        return Tzsq.class.getName();
    }
    @Override
	public List getDzgList(Map<String ,Object> paramMap) {
		return  this.getByKey("getDzgList", paramMap);
	}
}



