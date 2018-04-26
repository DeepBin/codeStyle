package com.sky.tzsc.gntzsc.persistence.manager;



import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;

import com.hotent.base.db.api.Dao;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.manager.api.Manager;
import com.sky.common.model.AbstractModel;
import com.sky.tzsc.gntzsc.persistence.model.Zslb;
import com.sky.zspz.persistence.model.ZspzYxzs;

/**
 * 
 * <pre> 
 * 描述：[tzsc_zslb]逻辑处理接口
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-03-27 15:36:11
 * 版权：内陆渔船开发项目组
 * </pre>
 */
public interface ZslbManager extends Manager<String, AbstractModel> {
	/**
	 * 根据主键得到[tzsc_zslb]信息
	 * @param id
	 * @return Zslb
	 * @author administrator 
	 * @Create 2018-03-27 15:36:11
	 */
	public Zslb getZslb(String id);
	
	/**
	 * 保存[tzsc_zslb]对象
	 * @param zslb
	 * @return Zslb
	 * @author administrator 
	 * @Create 2018-03-27 15:36:11
	 */
	public Zslb saveZslb(Zslb zslb);
	
	/**
	 * 执行删除[tzsc_zslb]对象
	 * @param aryIds
	 * 			<li>主键值，以逗号隔开 </li>
	 * @return Zslb
	 * 			<li>如果是删除一条[tzsc_zslb]数据，会返回删除的[tzsc_zslb]信息。批量删除，会返回null </li>
	 * @author administrator 
	 * @Create 2018-03-27 15:36:11
	 */
	public Zslb doRemove(String[] aryIds);
	
	/**
	 * 执行列表查询
	 * @param paramMaps
	 * @return PageList<Zslb>
	 * @author administrator 
	 * @Create 2018-03-27 15:36:11
	 */
	public PageList<Zslb>  zslbListJson(Map<String ,Object> paramMap);

	/**
	 * 根据 流程类型  查询 证书 列表   
	 * @param lclx
	 * @return List<Zslb>
	 * @author wjk 
	 * @Create Date 2018 下午4:29:34
	 */
	public List<Zslb> getZslbByLclx(String lclx,String sheetKey,String systemName);

	/**
	 * 添加证书    
	 * @param sheetKey 申请表主键
	 * @param fileId  证书表 主键
	 * @return void
	 * @author wjk 
	 * @Create Date 2018 上午9:33:03
	 */
	public void addTzscZs(String sheetKey, String fileId,String systemName);

	/**
	 * 删除图纸审查 已选择的证书    
	 * @param sheetKey 图纸审查申请表主键 
	 * @param fileId 
	 * @return void
	 * @author wjk 
	 * @Create Date 2018 上午9:46:31
	 */
	public void deleteTzscZs(String sheetKey, String fileId,String systemName);
	
	/**
	 * TODO(这里用一句话描述这个方法的作用)    
	 * @param listElement
	 * @param sheetKey
	 * @param string
	 * @return Map<String,Object>
	 * @author wjk 
	 * @Create Date 2018 上午10:42:39
	 */
	public Map<String, Object> getSelectResult(List<Map<String, Object>> listElement, String sheetKey, String string);

	/**
	 * 根据申请表主键查询 已经选择的证书 列表    
	 * @param sheetKey void
	 * @author wjk 
	 * @return 
	 * @Create Date 2018 下午1:47:23
	 */
	public List<Zslb> getYxzs(String sheetKey,String systemName);

	/**
	 * 保存证书信息    
	 * @param saveFlag
	 * @param map
	 * @param sheetKey
	 * @param string
	 * @param fileId void
	 * @author wjk 
	 * @Create Date 2018 下午3:27:38
	 */
	public void updateZs(String saveFlag, Map<String, Object> map, String sheetKey, String string, Integer fileId)throws DocumentException;
}
