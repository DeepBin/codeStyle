package com.sky.tzsc.yytzsc.handler;

public class YytzscFz {
	public boolean zg(com.hotent.bpmx.api.cmd.ActionCmd processCmd, String instanceId_) {
		boolean flag = false;
		com.sky.tzsc.yytzsc.persistence.manager.TzscsqManager tzscsqManager = (com.sky.tzsc.yytzsc.persistence.manager.TzscsqManager) com.hotent.base.core.util.AppUtil.getBean("tzscsqManager"); 
		System.out.println(instanceId_);
		com.sky.tzsc.yytzsc.persistence.model.Tzscsq tzsq = tzscsqManager.get(instanceId_);
		if(tzsq.getSfzg().equals("1")) {
			flag = true;
		}
		return flag;
	}
	
	public  boolean sfjszg(com.hotent.bpmx.api.cmd.ActionCmd processCmd, String instanceId_) {
		boolean flag = false;
		com.sky.tzsc.yytzsc.persistence.manager.TzscsqManager tzscsqManager = (com.sky.tzsc.yytzsc.persistence.manager.TzscsqManager) com.hotent.base.core.util.AppUtil.getBean("tzscsqManager");
		com.sky.tzsc.yytzsc.persistence.model.Tzscsq tzsq = tzscsqManager.get(instanceId_);
		if(tzsq.getSfjszg().equals("1")) {
			flag = true;
		}
		return flag ;
	}
}
