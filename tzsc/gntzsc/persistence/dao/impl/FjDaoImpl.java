package com.sky.tzsc.gntzsc.persistence.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.sky.tzsc.gntzsc.persistence.dao.FjDao;
import com.sky.tzsc.gntzsc.persistence.model.Fj;

/**
 * 
 * <pre> 
 * 描述：[tzsc_fj]数据处理实现类
 * 构建组：x5-bpmx-platform
 * 作者:administrator
 * 邮箱:administrator
 * 日期:2017-11-28 16:18:18
 * 版权：内陆渔船开发项目组
 * </pre>
 */
@Repository
public class FjDaoImpl extends MyBatisDaoImpl<String, Fj> implements FjDao{

    @Override
    public String getNamespace() {
        return Fj.class.getName();
    }
    /**
     * 
     */
    @Override
    public Fj getCtbfBySqIdAndFjmlfl(String sqId, String fjmlfl) {
    	Map<String, Object> params = new  HashMap<String, Object>();
    	params.put("tzsq_id", sqId);
    	params.put("fjmlfl_yw", fjmlfl);
    	return  this.getUnique("", params);
    }
    @Override
    public List<Fj> getFjListByFlowKey(String flowKey){
    	Map<String, Object> params = new  HashMap<String, Object>();
    	params.put("flowKey", flowKey);
    	return  this.getList("getFjListByFlowKey", params);
    }
    		
}



