
package com.sky.tzsc.gntzsc.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;

import com.sky.common.util.StaticMethod;

/**
 * 
 * 项目名称：x5web    
 * 类名称：ZspzXmlHelper    
 * 类描述：    
 * 创建时间：2017年12月8日 下午3:08:54       
 * @version   1.0
 * @author wjg
 *
 */
public class TzscZsXmlHelper {
	private static String rootPath = StaticMethod.getClassPath() + "tzsczs" + System.getProperty("file.separator") + "xml" + System.getProperty("file.separator");
	
	/**
	 * 初始化 Document对象
	 * 
	 * @param file
	 *            <li>xml文件 格式如下</li>
	 * @return Document
	 * @throws DocumentException
	 * @author wjg
	 * @Create Date 2015 上午11:13:39
	 */
	public static Document newDocument(File file) throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(file);
		return document;
	}
	
	/**
	 * 初始化 Document对象
	 * 
	 * @param xml
	 *            <li>xml的字符串</li>
	 * @return Document
	 * @throws DocumentException
	 * @author wjg
	 * @Create Date 2015 上午11:16:04
	 */
	public static Document newDocument(String xml) throws DocumentException {
		// 将字符串转化dom4j对象
		Document document = DocumentHelper.parseText(xml);
		return document;
	}
	
	/**
	 * 解析xml，文件。
	 * 
	 * @param xmlPath
	 * @throws DocumentException
	 *             void
	 * @author wjg
	 * @Create Date 2017 下午2:43:04
	 */
	public static List<Map<String, Object>> getElementMap(String xmlPath) throws DocumentException {
		File file = new File(rootPath+xmlPath);
		System.out.println(file.getPath());
		Document root = TzscZsXmlHelper.newDocument(file);
		Element rootElement = root.getRootElement();
		List<Map<String, Object>> list = castElementToMap(rootElement);
		
		System.out.println(list);
		return list;
	}
	
	/**
	 * 将xml解析成Map对象，并返回元素集合
	 * 
	 * @param element
	 * @return List<Map<String,Object>>
	 * @author wjg
	 * @Create Date 2017 下午2:43:22
	 */
	public static List<Map<String, Object>> castElementToMap(Element element) {
		// 获取所有子元素
		List<Element> childList = element.elements();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Element _element : childList) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<DefaultAttribute> listAttributes = _element.attributes();// 所有属性
			List<DefaultElement> listElement = _element.elements();// 所有子元素
			if (StaticMethod.isNotEmpty(listAttributes)) {//
				map.put("tables", getAttributes(listAttributes));
			}
			if (StaticMethod.isNotEmpty(listElement)) {//
				map.put("propertys", getElement(listElement));
			}
			
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 解析属性，并转化成map
	 * 
	 * @param listAttributes
	 * @return Map<String,String>
	 * @author wjg
	 * @Create Date 2017 下午2:44:02
	 */
	public static Map<String, String> getAttributes(List<DefaultAttribute> listAttributes) {
		Map<String, String> map = new HashMap<String, String>();
		for (DefaultAttribute defaultAttribute : listAttributes) {
			String name = defaultAttribute.getName();
			String value = defaultAttribute.getValue();
			map.put(name, value);
		}
		return map;
	}
	
	/**
	 * 解析元素，包含对应的子元素的属性
	 * 
	 * @param listElement
	 * @return List<Map<String,String>>
	 * @author wjg
	 * @Create Date 2017 下午2:44:21
	 */
	public static List<Map<String, String>> getElement(List<DefaultElement> listElement) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (DefaultElement defaultElement : listElement) {
			List<DefaultAttribute> listAttributes = defaultElement.attributes();// 所有属性
			Map<String, String> map = getAttributes(listAttributes);
			list.add(map);
		}
		return list;
	}
}
