
package com.boco.eoms.exelawtipstaffexam.vo;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksCheckControl;



public class ZfryzgksCheckControlVO extends ZfryzgksCheckControl{
	
	private String stateName ;
	
	private String flag ;

	public String getStateName() {
		String districtName = new com.boco.eoms.jbzl.bo.TawDistrictBO().getDistrictNameById(this.getDistrictId());
		if(this.getState().equals("1")){
			stateName =districtName+"已提交";
		}else if(this.getState().equals("2")){
			stateName = districtName+"待审核";
		}else if(this.getState().equals("3")){
			if(this.getDistrictId().equals("000000")){
				stateName = districtName+"待核定";
			}else{
				stateName = districtName+"待上报";
			}
		}else if(this.getState().equals("5")){
			stateName = districtName+"已核定";
		}
		return stateName;
	}

	public String getFlag() {
		flag = "false;";
		if(StaticMethod.nullObject2String(this.getState()).equals("5")&&StaticMethod.nullObject2String(this.getNextStep()).equals("-1")){
			flag = "true";
		}
		return flag;
	}

}