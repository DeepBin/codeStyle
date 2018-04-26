package com.sky.tzsc.gntzsc.persistence.dao;

import java.util.List;

import com.hotent.base.db.api.Dao;
import com.sky.tzsc.gntzsc.persistence.model.Fj;

/**
 * 
 * <pre> 
 * 描述：[tzsc_fj]数据处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-28 16:18:18
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface FjDao extends Dao<String, Fj> {

	/**
	 * 根据申请表 id  和 附件目录分类 查询 附件 用于 申请表展示  
	 * @param id
	 * @param string
	 * @return Fj
	 * @author wjk 
	 * @Create Date 2017 下午7:03:44
	 */
	Fj getCtbfBySqIdAndFjmlfl(String sqid, String fjmlfl);

	/**
	 * TODO(这里用一句话描述这个方法的作用)    
	 * @param flowkey
	 * @return List
	 * @author wjk 
	 * @Create Date 2018 下午2:47:00
	 */
	List<Fj> getFjListByFlowKey(String flowkey);
}

