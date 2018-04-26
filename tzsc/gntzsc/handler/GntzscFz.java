package com.sky.tzsc.gntzsc.handler;


public class GntzscFz {
	public boolean zg(com.hotent.bpmx.api.cmd.ActionCmd processCmd, String instanceId_) {
		boolean flag = false;
		com.sky.tzsc.gntzsc.persistence.manager.TzsqManager tzsqManager = (com.sky.tzsc.gntzsc.persistence.manager.TzsqManager) com.hotent.base.core.util.AppUtil.getBean("tzsqManager"); 
		System.out.println(instanceId_);
		com.sky.tzsc.gntzsc.persistence.model.Tzsq tzsq = tzsqManager.get(instanceId_);
		if(tzsq.getSfzg().equals("1")) {
			flag = true;
		}
		return flag;
	}
	
	public  boolean sfjszg(com.hotent.bpmx.api.cmd.ActionCmd processCmd, String instanceId_) {
		boolean  flag = false;
		com.sky.tzsc.gntzsc.persistence.manager.TzsqManager tzsqManager = (com.sky.tzsc.gntzsc.persistence.manager.TzsqManager) com.hotent.base.core.util.AppUtil.getBean("tzsqManager");
		com.sky.tzsc.gntzsc.persistence.model.Tzsq tzsq = tzsqManager.get(instanceId_); //
		if(tzsq.getSfjszg().equals("1")) {
			flag = true;
		}
		return  flag;
	}
}
