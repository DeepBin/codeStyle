package com.sky.tzsc.gntzsc.persistence.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.base.db.mybatis.CommonDao;
import com.sky.common.model.AbstractModel;
import com.sky.common.util.MyBeanUtils;
import com.sky.common.util.StaticMethod;
import com.sky.common.util.UUIDGenerator;
import com.sky.tzsc.gntzsc.persistence.dao.ZslbDao;
import com.sky.tzsc.gntzsc.persistence.model.Zslb;
import com.sky.zspz.persistence.model.ZspzYxzs;

/**
 * 
 * <pre> 
 * 描述：[tzsc_zslb]数据处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2018-03-27 15:36:11
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Repository
public class ZslbDaoImpl extends MyBatisDaoImpl<String, AbstractModel> implements ZslbDao{
	@Resource
	CommonDao commonDao;
    @Override
    public String getNamespace() {
        return Zslb.class.getName();
    }

	@Override
	public List<Zslb> getZslbBylclx(String lclx,String sheetKey,String systemName) {
		String sql = "select * from tzsc_zslb where  (isnull(deleted,'') ='' or isnull(deleted,'') ='N') "
				+ " and lclx='"+lclx+"' and system_name ='"+systemName+"'";
		sql += " and file_id not in (select  file_id from tzsc_yxzs where sheet_key ='"+sheetKey+"' and (isnull(deleted,'') ='' or isnull(deleted,'') ='N'))";
		sql = sql + " order by order_id";
		List<Map<String, String>> list = commonDao.query(sql);
		List<Zslb> retList = new  ArrayList();
		for (Map<String, String> map : list) {
			retList.add(this.changeResultToZslb(map));
		}
		return retList;
	}

	/**
	 *     
	 * @param map
	 * @return Zslb
	 * @author wjk 
	 * @Create Date 2018 上午9:04:18
	 */
	private Zslb changeResultToZslb(Map<String, String> map) {
		Zslb zslb = new  Zslb();
		try {
			MyBeanUtils.copyDbMap2Bean(zslb, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return zslb;
	}
	
	/**
	 * 添加 图纸审查证书
	 * @param sheetKey fildId
	 * @return void
	 * @author wjk 
	 * @Create Date 2018 上午9:44:18
	 */
	@Override
	public void addTzscZs(String sheetKey ,String fildId,String  systemName) {
		String sql = "INSERT INTO tzsc_yxzs(id,file_id,sheet_key,deleted,modify_count,print_count,is_over,order_id,commit_count,print_count_status,system_name) VALUES('"+UUIDGenerator.generate()+"',"+fildId+",'"+sheetKey+"','N',0,0,'N',"+fildId+",0,0,'"+systemName+"')"; 
		commonDao.execute(sql);
	}
	
	/**
	 * 删除  图纸审查证书
	 * @param sheetKey  图纸审查申请表主键
	 * @param fildId 图纸审查 证书表主键
	 * @return void
	 * @author wjk 
	 * @Create Date 2018 上午9:44:18
	 */
	@Override 
	public void deleteTzscZs(String sheetKey , String fildId,String systemName) {
		String sql = "delete from tzsc_yxzs where sheet_key ='"+sheetKey+"' and file_id ="+fildId+"and system_name ='"+systemName+"'";
		commonDao.execute(sql);
	}
	
	/**
	 * 根据主键 查询 证书信息
	 * @param id 图纸审查 证书表主键
	 * @return void
	 * @author wjk 
	 * @Create Date 2018 上午9:44:18
	 */
	@Override 
	public Zslb getZslb(String id) {
		String sql = "select * from tzsc_zslb ct where (isnull(deleted,'') ='' or isnull(deleted,'') ='N') ";
		sql = sql + " and file_id = '" + id + "'";
		sql = sql + " order by ct.order_id";
		List<Map<String, String>> list = commonDao.query(sql);
		for (Map<String, String> map : list) {
			return this.changeResultToZslb(map);
		}
		return  null;
	}
	
	/**
	 * 根据主键 查询 已经选择的证书
	 * @param id 图纸审查 证书表主键
	 * @return void
	 * @author wjk 
	 * @Create Date 2018 上午9:44:18
	 */
	@Override 
	public List<Zslb> getYxzs(String sheetKey,String systemName) {
		String sql = "select distinct ct.*,sheet_key,modify_count,print_count,is_over,commit_count,print_count_status from tzsc_yxzs cf ,tzsc_zslb ct where cf.sheet_key='"+sheetKey+"' and cf.system_name ='"+systemName+"' and cf.file_id=ct.file_id ";
		sql +=  "and (isnull(ct.deleted,'') ='' or isnull(ct.deleted,'') ='N') and(isnull(cf.deleted,'')='' or  isnull(cf.deleted,'')='N') ";
		List<Map<String, String>> list = commonDao.query(sql);
		List<Zslb> returnList = new ArrayList<Zslb>();
		for (Map<String, String> map : list) {
			returnList.add(this.changeResultToZslb(map));
		}
		return returnList;
	}
	
	/**
	 * 更新 提交 次数   
	 *  void
	 * @param sheetKey fileId
	 * @author wjk 
	 * @Create Date 2018 下午3:39:38
	 */
	@Override
	public void updateCommitCount(String sheetKey, Integer fileId){
		String sql = "UPDATE tzsc_yxzs SET modify_count = isnull(modify_count,0)+1 where sheet_key ='"+sheetKey+"' and file_id ="+fileId;
		commonDao.execute(sql);
	}
	
	/**
	 * 更新 保存  次数   
	 *  void
	 * @param sheetKey fileId
	 * @author wjk 
	 * @Create Date 2018 下午3:39:38
	 */
	@Override
	public void updateSaveCount(String sheetKey, Integer fileId) {
		String sql = "UPDATE tzsc_yxzs SET modify_count = isnull(modify_count,0)+1 where sheet_key ='"+sheetKey+"' and file_id ="+fileId;
		commonDao.execute(sql);
	}
}



