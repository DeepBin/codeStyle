package com.sky.tzsc.gntzsc.persistence.dao;

import java.util.List;
import java.util.Map;

import com.hotent.base.db.api.Dao;
import com.hotent.base.db.mybatis.domain.PageList;
import com.sky.tzsc.gntzsc.persistence.model.Tzsq;

/**
 * 
 * <pre> 
 * 描述：[tzsc_sq]数据处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-22 14:04:09
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface TzsqDao extends Dao<String, Tzsq> {
	public List getDzgList(Map<String ,Object> paramMap);
}

