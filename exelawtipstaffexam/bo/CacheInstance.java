
package com.boco.eoms.exelawtipstaffexam.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.codehaus.jettison.json.JSONArray;

import com.boco.eoms.exelawtipstaffexam.model.ZfryzgksDwgl;

public class CacheInstance {
	private static CacheInstance instance = new CacheInstance();
	
	private Map mapDwgl = new ConcurrentHashMap();
	
	private Map mapShengDwgl = new ConcurrentHashMap();
	
	private Map mapShengJson = new ConcurrentHashMap();
	
	private CacheInstance() {
	}
	
	public boolean firstLoad() {
		return this.mapDwgl.size() > 0 ? false : true;
	}
	
	public static CacheInstance getInstance() {
		return instance;
	}
	
	public void addDwgl(ZfryzgksDwgl zfryzgksDwgl) {
		String id = zfryzgksDwgl.getId();
		if (null != id) {
			mapDwgl.put(id, zfryzgksDwgl);
		}
	}
	
	public void deleteFromCacheById(String id) {
	}
	
	public void deleteFromCacheByDwmc(String dwmc) {
	}
	
	public void deleteFromCacheById(ZfryzgksDwgl zfryzgksDwgl) {
	}
	
	public void clearCache() {
		mapDwgl.clear();
		mapShengDwgl.clear();
		mapShengJson.clear();
	}
	
	public void initCacheJsonString() {
		
		Iterator iteratorDwgl = mapDwgl.keySet().iterator();
		while (iteratorDwgl.hasNext()) {
			String key = (String) iteratorDwgl.next();
			
			ZfryzgksDwgl zfryzgksDwgl = (ZfryzgksDwgl) mapDwgl.get(key);
			String shengfenId = zfryzgksDwgl.getShenfenId();
			if (null == mapShengDwgl.get(shengfenId)) {
				List list = new ArrayList();
				mapShengDwgl.put(shengfenId, list);
			}
			List list = (List) mapShengDwgl.get(shengfenId);
			list.add(zfryzgksDwgl);
		}
		
		Iterator iteratorShengDwgl = mapShengDwgl.keySet().iterator();
		while (iteratorShengDwgl.hasNext()) {
			String shengfenId = (String) iteratorShengDwgl.next();
			List list = (List) mapShengDwgl.get(shengfenId);
			JSONArray jsonArray = new JSONArray();
			for (int index = 0; index < list.size(); index++) {
				Map map = new HashMap();
				map.put("dwmc", ((ZfryzgksDwgl) list.get(index)).getDwmc());
				jsonArray.put(map);
			}
			mapShengJson.put(shengfenId, jsonArray.toString());
		}
	}
	
	private void reInitJsonArray(String shengfenId) {
		List list = (List) mapShengDwgl.get(shengfenId);
		JSONArray jsonArray = new JSONArray();
		for (int index = 0; index < list.size(); index++) {
			Map map = new HashMap();
			map.put("dwmc", ((ZfryzgksDwgl) list.get(index)).getDwmc());
			jsonArray.put(map);
		}
		mapShengJson.put(shengfenId, jsonArray.toString());
	}
	
	public String getCacheJsonString(String sqdu) {
		return (String) mapShengJson.get(sqdu);
	}
	
	/**
	 * 更新单位信息后需调用此方法<br>
	 * 插入需要在保存至数据库以后获取主键ID，才能调用该方法 更新在保存前后都可以<br>
	 * 主键id、shengfenId都是重要的信息，需注意
	 * 
	 * @param zfryzgksDwgl
	 */
	public void addOrUpdateDwgl(ZfryzgksDwgl zfryzgksDwgl) {
		String id = zfryzgksDwgl.getId();
		String shengfenId = zfryzgksDwgl.getShenfenId();
		if (mapDwgl.get(id) == null) {
			mapDwgl.put(id, zfryzgksDwgl);
			List list = (List) mapShengDwgl.get(shengfenId);
			list.add(zfryzgksDwgl);
			reInitJsonArray(shengfenId);
		} else {
			mapDwgl.put(id, zfryzgksDwgl);
			List list = (List) mapShengDwgl.get(shengfenId);
			for (int index = 0; index < list.size(); index++) {
				ZfryzgksDwgl tempZfryzgksDwgl = (ZfryzgksDwgl) list.get(index);
				if (tempZfryzgksDwgl.getId().equals(id)) {
					list.remove(index);
					break;
				}
			}
			list.add(zfryzgksDwgl);
			reInitJsonArray(shengfenId);
		}
	}
}
