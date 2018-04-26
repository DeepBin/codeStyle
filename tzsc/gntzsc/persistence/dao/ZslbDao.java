package com.sky.tzsc.gntzsc.persistence.dao;


import java.util.List;

import com.hotent.base.db.api.Dao;
import com.sky.common.model.AbstractModel;
import com.sky.tzsc.gntzsc.persistence.model.Zslb;
import com.sky.zspz.persistence.model.ZspzZsb;

/**
 * 
 * <pre> 
 * 描述：[tzsc_zslb]数据处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-03-27 15:36:11
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface ZslbDao extends Dao<String, AbstractModel> {

	/**
	 * 根据图纸审查 的 流程 步骤 查询 可以制作的证书  
	 * @param lclx 流程类型
	 * @return List<Zslb>
	 * @author wjk 
	 * @Create Date 2018 上午9:36:48
	 */
	List<Zslb> getZslbBylclx(String lclx,String sheetKey,String systemName);

	/**
	 * 添加图纸审查证书     
	 * @param sheetKey 图纸审查申请表 主键
	 * @param fildId void 图纸审查 证书表主键
	 * @author wjk 
	 * @Create Date 2018 上午9:36:51
	 */
	void addTzscZs(String sheetKey, String fildId,String systemName);

	/**
	 * 删除 已选择 的图纸审查证书    
	 * @param sheetKey 图纸审查申请表主键 
	 * @param fildId 图纸审查 证书 表主键 
	 * @author wjk 
	 * @Create Date 2018 上午10:08:23
	 */
	void deleteTzscZs(String sheetKey, String fildId,String systemName);

	/**
	 * 根据主键 查询证书    
	 * @param id 图纸审查 证书表表主键
	 * @return Zslb 图纸审查 证书 
	 * @author wjk 
	 * @Create Date 2018 上午10:35:57
	 */
	Zslb getZslb(String id);

	/**
	 * 根据申请表主键查询 已经选择的证书    
	 * @param sheetKey
	 * @return List<Zslb>
	 * @author wjk 
	 * @Create Date 2018 下午1:52:17
	 */
	List<Zslb> getYxzs(String sheetKey,String systemName);

	/**
	 * 更新 提交次数   
	 * @param sheetKey
	 * @param fileId void
	 * @author wjk 
	 * @Create Date 2018 下午3:37:49
	 */
	void updateCommitCount(String sheetKey, Integer fileId);

	/**
	 * 更新 保存次数 
	 * @param sheetKey
	 * @param fileId void
	 * @author wjk 
	 * @Create Date 2018 下午3:38:00
	 */
	void updateSaveCount(String sheetKey, Integer fileId);
	
}

