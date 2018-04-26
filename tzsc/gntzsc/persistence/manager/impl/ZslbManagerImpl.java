package com.sky.tzsc.gntzsc.persistence.manager.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;


import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.db.mybatis.domain.PageList;

import com.sky.api.sys.log.constant.Globals;
import com.sky.api.sys.log.service.ISysLogService;

import com.hotent.base.db.api.Dao;
import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.base.manager.impl.AbstractManagerImpl;

import com.hotent.base.db.mybatis.CommonDao;
import com.hotent.base.db.mybatis.domain.DefaultPage;

import com.sky.common.constant.ConstSystem;
import com.sky.common.context.ContextUtil;
import com.sky.common.model.AbstractModel;
import com.sky.common.util.StaticMethod;
import com.sky.common.util.UUIDGenerator;
import com.sky.tzsc.gntzsc.persistence.dao.ZslbDao;
import com.sky.tzsc.gntzsc.persistence.manager.ZslbManager;
import com.sky.tzsc.gntzsc.persistence.model.Zslb;
import com.sky.tzsc.gntzsc.util.TzscZsXmlHelper;
import com.sky.zspz.constant.ConstZspz;
import com.sky.zspz.persistence.dao.ZspzDao;
import com.sky.zspz.persistence.model.ZspzYxzs;
import com.sky.zspz.persistence.model.ZspzZsb;
import com.sky.zspz.util.ZspzXmlHelper;

/**
 * 
 * <pre> 
 * 描述：[tzsc_zslb]逻辑处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-03-27 15:36:11
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Service("zslbManager")
public class ZslbManagerImpl extends MyBatisDaoImpl<String, AbstractModel> implements ZslbManager{
	@Resource
	ZslbDao zslbDao;
	@Resource
	CommonDao commonDao;
	@Resource
	ISysLogService sysLogService;
	/**
	 * 根据 流程 类型 查询 证书列表
	 */
	@Override
	public List<Zslb> getZslbByLclx(String lclx ,String sheetKey,String systemName){
		List<Zslb> list = new ArrayList<Zslb>();
		list = zslbDao.getZslbBylclx(lclx,sheetKey,systemName);
		return  list;
	}
	@Override
	public QueryFilter getQueryFilter(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Zslb getZslb(String id) {
		if (!StaticMethod.isNotEmpty(id)) { return null; }
		Zslb zslb = zslbDao.getZslb(id);
		return zslb;
	}
	@Override
	public Zslb saveZslb(Zslb zslb) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Zslb doRemove(String[] aryIds) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PageList<Zslb> zslbListJson(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getNamespace() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 *添加图纸审查证书
	 */
	@Override
	public void addTzscZs(String sheetKey , String fildId,String systemName) {
		zslbDao.addTzscZs(sheetKey,fildId,systemName);
	}
	
	/**
	 * 删除 已经选择的证书 
	 */
	@Override
	public void deleteTzscZs(String sheetKey , String fildId,String systemName) {
		zslbDao.deleteTzscZs(sheetKey,fildId,systemName);
	}
	
	/**
	 * 查询证书数据
	 */
	@Override
	public Map<String, Object> getSelectResult(List<Map<String, Object>> list, String sheetKey, String flowKeyName) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		for (Map<String, Object> map : list) {
			Map<String, String> tables = (Map<String, String>) map.get("tables");
			String tableName = StaticMethod.nullObject2String(tables.get("name"));
			String more = StaticMethod.nullObject2String(tables.get("more"));
			int maxmunber = StaticMethod.nullObject2int(tables.get("maxmunber"), 1);// 最大条数
			String sql = this.getSelectSql(map, sheetKey, flowKeyName);
			Map<String, Object> reslutMap = new HashMap<String, Object>();
			if ("true".equals(more)) {// 是多条的
				reslutMap = getMoreResult(tableName, sql, maxmunber);
			} else {
				reslutMap = getOneResult(tableName, sql, maxmunber);
			}
			retMap.putAll(reslutMap);
		}
		return retMap;
	}
	
	/**
	 * TODO(这里用一句话描述这个方法的作用)    
	 * @param map
	 * @param sheetKey
	 * @param flowKeyName
	 * @return String
	 * @author wjg
	 * @Create Date 2018 上午10:52:57
	 */
	private String getSelectSql(Map<String, Object> map, String sheetKey, String flowKeyName) {
		StringBuffer sb = new StringBuffer();
		Map<String, String> tables = (Map<String, String>) map.get("tables");
		String tableType = StaticMethod.nullObject2String(tables.get("type"));
		String tableName = StaticMethod.nullObject2String(tables.get("name"));
		String colkey = StaticMethod.nullObject2String(tables.get("colkey"));
		String more = StaticMethod.nullObject2String(tables.get("more"));
		sb.append("select * from " + tableName + " where ");
		if (ConstZspz.ZSPZ_TYPE.equals(tableType)) {// 主表
			sb.append("sheet_key='" + sheetKey + "'");
		} else {
			if (StaticMethod.isNotEmpty(colkey)) {// 配置文件中配置了，值就去，如果没有配置就使用 参数中的flowKeyName
				sb.append(colkey + "='" + sheetKey + "'");
			} else {
				sb.append(flowKeyName + "='" + sheetKey + "'");
			}
		}
		if ("true".equals(more)) {
//			sb.append("order by " + tableName + "_entry");
		}
		return sb.toString();
	}
	
	/**
	 * 按照配置文件的数据，查询 条数，如果数据结果集小于配置数据，直接 补全数据，避免页面写复杂的for和if else
	 * retMap的结果集就和配置是一样的，在页面上就可以直接取数据就可以了，不用判断大量的循环
	 * 默认都加上表名，避免字段名重复
	 * @param sql
	 * @param maxmunber
	 * @return Map<String,Object>
	 * @author wjg 
	 * @Create Date 2017 上午10:12:28
	 */
	private Map<String, Object> getMoreResult(String tableName, String sql, int maxmunber) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = commonDao.query(sql, new DefaultPage(1, maxmunber));
		int i = 0;
		List<String> colName = new ArrayList();
		String tableFix = tableName + "_";
		if (StaticMethod.isNotEmpty(list)) {
			for (Map<String, Object> map : list) {
				for (String key : map.keySet()) {
					String mapKey = (tableFix + key).toLowerCase();
					Object value = map.get(key);
					retMap.put(mapKey + i, value);
					if (i == 0) {// 把字段名称放到集合里存储，为了判断，数据库结果集小于了maxnumber时，手工 填充数据时使用
						colName.add(mapKey);
					}
				}
				i++;
				if (i == list.size()) {// 如果超出了，只取配置文件的 大小，不在读取多余部分
					break;
				}
			}
		}
		/**
		 * 数据库结果集小于了maxnumber时，填充数据时使用
		 */
		if (maxmunber > list.size()) {
			for (String key : colName) {
				retMap.put(tableFix + key + i, null);
			}
			i++;
		}
		return retMap;
	}
	
	/**
	 * 按照配置文件的数据，查询数据，默认都加上表名，避免字段名重复
	 * @param sql
	 * @param maxmunber
	 * @return Map<String,Object>
	 * @author wjg 
	 * @Create Date 2017 上午10:12:28
	 */
	private Map<String, Object> getOneResult(String tableName, String sql, int maxmunber) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = commonDao.query(sql, new DefaultPage(1, maxmunber));
		String tableFix = tableName + "_";
		if (StaticMethod.isNotEmpty(list)) {
			for (Map<String, Object> map : list) {
				for (String key : map.keySet()) {
					String mapKey = (tableFix + key).toLowerCase();// 名称全部转成小写
					Object value = map.get(key);
					retMap.put(mapKey, value);
				}
			}
		}
		return retMap;
	}
	
	/**
	 * 根据申请表主键查询 已选择的证书
	 * @param sheetKey 申请表主键
	 * @return List<Zslb>
	 * @author wjk 
	 * @Create Date 2017 上午10:12:28
	 */
	public List<Zslb> getYxzs(String sheetKey,String systemName){
		List<Zslb> returnList= zslbDao.getYxzs(sheetKey,systemName);
		return  returnList;
	}
	
	/**
	 * 保存证书信息
	 * @param sheetKey
	 * @return List<Zslb>
	 * @author wjk 
	 * @throws DocumentException 
	 * @Create Date 2017 上午10:12:28
	 */
	public void updateZs(String saveFlag, Map<String, Object> param, String sheetKey, String string, Integer fileId) throws DocumentException {
		Zslb zslb = this.getZslb(fileId.toString());
		List<Map<String, Object>> listElement = TzscZsXmlHelper.getElementMap(zslb.getPageName()+".xml");
		this.updateZsxx(listElement,param, sheetKey, "sheet_key");
		/**更新保存次数，更新次数，***/
		if(ConstZspz.ACITON_SAVE.equals(saveFlag)) {
			this.updateSaveCount(sheetKey, fileId);
		}
		/**更新提交次数，更新次数，***/
		else if(ConstZspz.ACITON_COMMIT.equals(saveFlag)) {
			this.updateCommitCount(sheetKey, fileId);
		}
	}
	
	/**
	 * 更新提交次数  
	 * @param sheetKey 申请表主键
	 * @param fileId void 证书表主键
	 * @author wjk 
	 * @Create Date 2018 下午3:36:26
	 */
	private void updateCommitCount(String sheetKey, Integer fileId) {
		zslbDao.updateCommitCount(sheetKey,fileId);
		
	}
	
	/**
	 * 更新保存次数   
	 * @param sheetKey 申请表主键
	 * @param fileId void 证书表主键
	 * @author wjk 
	 * @Create Date 2018 下午3:36:30
	 */
	private void updateSaveCount(String sheetKey, Integer fileId) {
		zslbDao.updateSaveCount(sheetKey,fileId);
	}
	
	/**
	 * 保存证书信息    
	 * @param listElement
	 * @param param 
	 * @param sheetKey 申请表主键
	 * @param string void
	 * @author wjk 
	 * @Create Date 2018 下午3:34:31
	 */
	private void updateZsxx(List<Map<String, Object>> list, Map<String, Object> param, String sheetKey,String flowKeyName) {
		for (Map<String, Object> map : list) {
			List<String> retList = new ArrayList<String>();
			Map<String, String> tables = (Map<String, String>) map.get("tables");
			String tableName = StaticMethod.nullObject2String(tables.get("name"));
			String more = StaticMethod.nullObject2String(tables.get("more"));
			int maxmunber = StaticMethod.nullObject2int(tables.get("maxmunber"), 1);// 最大条数
			retList.addAll(this.getOneInsertOrUpdateSql(map, param, sheetKey, flowKeyName));// 单条的 如果存在就更新，不存在就插入
			if (retList.size() > 0) {
				this.excuteSql(retList);// 执行语句
			}
		}
	}
	
	/**
	 * 得到 插入或者新增的sql    
	 * @param map
	 * @param param
	 * @param sheetKey
	 * @param flowKeyName
	 * @return Collection<? extends String>
	 * @author wjk 
	 * @Create Date 2018 下午3:59:59
	 */
	private Collection<? extends String> getOneInsertOrUpdateSql(Map<String, Object> colMap, Map<String, Object> param,String sheetKey, String flowKeyName) {
		List<String> retList = new ArrayList<String>();
		Map<String, String> tables = (Map<String, String>) colMap.get("tables");
		String tableName = StaticMethod.nullObject2String(tables.get("name"));
		String idValue = StaticMethod.nullObject2String(param.get(tableName + "_id"));
		if (idValue.equals("")) {// 插入
			retList.add(this.getOneInsertSql(colMap, param, sheetKey, flowKeyName));
		} else {// 更新
			retList.add(this.getOneUpdateSql(colMap, param, sheetKey, flowKeyName));
		}
		return retList;
	}
	
	/**
	 * 更新证书 sql  
	 * @param colMap
	 * @param param 界面form
	 * @param sheetKey 申请表主键
	 * @param flowKeyName 
	 * @return String sql
	 * @author wjk 
	 * @Create Date 2018 下午4:15:46
	 */
	private String getOneUpdateSql(Map<String, Object> colMap, Map<String, Object> param, String sheetKey,String flowKeyName) {
		StringBuffer sb = new StringBuffer();
		StringBuffer update = new StringBuffer();
		Map<String, String> tables = (Map<String, String>) colMap.get("tables");
		List<Map<String, String>> propertysList = (List<Map<String, String>>) colMap.get("propertys");
		String tableName = StaticMethod.nullObject2String(tables.get("name"));
		sb.append("update " + tableName + " set ");
		int i = 0;
		String tableFix = tableName + "_";
		for (Map<String, String> map : propertysList) {
			String name = map.get("name");// 字段名称
			String type = map.get("type");// 数据类型
			String mapKey = (tableFix + name).toLowerCase();
			update.append(name + "=" + getValue(param, type, mapKey));
			if (i < propertysList.size()-1) {
				update.append(",");
			}
			i++;
		}
		sb.append(update.toString());
		sb.append(" where id = '" + param.get(tableName + "_id")+"'");
		
		return sb.toString();
	}
	
	/**
	 * 1、插入时生成 编号    2、构造新增sql
	 * @param colMap 表字段
	 * @param param 界面form
	 * @param sheetKey 申请表主键
	 * @param flowKeyName
	 * @return String sql
	 * @author wjg
	 * @Create Date 2018 下午4:01:17
	 */
	private String getOneInsertSql(Map<String, Object> colMap, Map<String, Object> param, String sheetKey,String flowKeyName) {
		StringBuffer sb = new StringBuffer();
		StringBuffer values = new StringBuffer();
		Map<String, String> tables = (Map<String, String>) colMap.get("tables");
		List<Map<String, String>> propertysList = (List<Map<String, String>>) colMap.get("propertys");
		String tableName = StaticMethod.nullObject2String(tables.get("name"));
		//生成编号
		sb.append("insert into " + tableName + " ");
		values.append("'" + sheetKey + "'" + ", '" + UUIDGenerator.generate() + "', '"+ContextUtil.getCurrentLogin().getLoginUserId()+"','"+ StaticMethod.getLocalString() + "',");
		int j = 0;
		String tableFix = tableName + "_";
		for (Map<String, String> map : propertysList) {
			String name = map.get("name");// 字段名称
			String type = map.get("type");// 数据类型
			String mapKey = (tableFix + name).toLowerCase();
			values.append(getValue(param, type, mapKey));
			if (j < propertysList.size()-1) {
				values.append(",");
			}
			j++;
		}
		sb.append(" values( ");
		sb.append(values.toString());
		sb.append(" )");
		return sb.toString();
	}
	
	/**
	 *     
	 * @param param
	 * @param type
	 * @param mapKey
	 * @return Object
	 * @author wjk 
	 * @Create Date 2018 下午4:25:51
	 */
	private Object getValue(Map<String, Object> param, String type, String name) {
		// int、string、float、double、date，datetime
				Object objValue = param.get(name);
				if (objValue == null) {// 如果为空直接 返回
					return null;
				}
				Object value = null;
				if (type.equals("int")) {// 数字类型
					value = StaticMethod.nullObject2Integer(objValue);
					return value;
				} else if (type.equals("float")) {// 浮点类型
					value = StaticMethod.nullObject2Float(objValue);
					return value;
				} else if (type.equals("double")) {// 数字类型
					value = StaticMethod.nullObject2Double(objValue);
					return value;
				} else if (type.equals("date")) {// 数字类型
					value = StaticMethod.nullObject2String(objValue);
				} else if (type.equals("datetime")) {// 数字类型
					value = StaticMethod.nullObject2Date(StaticMethod.nullObject2String(objValue));
				} else {// 其他
					value = StaticMethod.nullObject2String(objValue);
				}
				if(value == null) {
					return null;
				}
				return "'" + value + "'";
	}
	
	/**
	 * 输出执行的sql语句   
	 * @param retList void
	 * @author wjk 
	 * @Create Date 2018 下午3:59:32
	 */
	private void excuteSql(List<String> retList) {
		for (String sql : retList) {
			if (StaticMethod.isNotEmpty(sql)) {
				System.out.println("sql======================================================="+sql);
				commonDao.execute(sql);
			}
		}
	}
}
