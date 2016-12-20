package com.hoaven.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class XMLUtil {

	private static final String XML_VERSION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

	@SuppressWarnings("unchecked")
	public static void toXML(Map<String, Object> map, StringBuilder xml) {
		Iterator<String> it = map.keySet().iterator();
		String key = null;
		Object value = null;
		while (it.hasNext()) {
			key = it.next();
			value = map.get(key);
			if (value instanceof Map) {
				xml.append("<").append(key).append(">");
				toXML((Map<String, Object>) value, xml);
				xml.append("</").append(key).append(">");
			} else if (value instanceof LinkedList) {
				LinkedList<Object> values = (LinkedList<Object>) value;
				Iterator<Object> valueIt = values.iterator();
				while (valueIt.hasNext()) {
					xml.append("<").append(key).append(">");
					Object obj = valueIt.next();
					if (obj instanceof Map) {
						toXML((Map<String, Object>) obj, xml);
					} else {
						xml.append(obj);
					}
					xml.append("</").append(key).append(">");
				}
			} else {
				xml.append("<").append(key).append(">");
				xml.append(value);
				xml.append("</").append(key).append(">");
			}
		}
	}

	public static Map<String, Object> fromXML(String xml) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		SAXBuilder builder = new SAXBuilder();
		Document document = null;
		try {
			document = builder.build(new StringReader(xml));
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		fromXML(root, map);
		return map;
	}

	@SuppressWarnings("unchecked")
	public static void fromXML(Element element, Map<String, Object> map) {
		String name = element.getName();
		Object value = null;
		Object existing = map.get(name);
		if (element.getChildren() == null || element.getChildren().isEmpty()) {
			value = element.getText();
		} else {
			Map<String, Object> subMap = new LinkedHashMap<String, Object>();
			Iterator<Element> it = element.getChildren().iterator();
			Element subElement = null;
			while (it.hasNext()) {
				subElement = it.next();
				fromXML(subElement, subMap);
			}
			value = subMap;
		}
		if (existing == null) {
			map.put(name, value);
		} else if (existing instanceof LinkedList) {
			LinkedList<Object> exists = (LinkedList<Object>) existing;
			exists.add(value);
			map.put(name, exists);
		} else {
			LinkedList<Object> mapList = new LinkedList<Object>();
			mapList.add(existing);
			mapList.add(value);
			map.put(name, mapList);
		}
	}

	public static String toXML(Map<String, Object> params, String parentName,
			boolean needVersion) {
		StringBuilder xml = new StringBuilder();
		if (needVersion) {
			xml.append(XML_VERSION);
		}
		if (parentName != null) {
			xml.append("<").append(parentName).append(">");
			toXML(params, xml);
			xml.append("</").append(parentName).append(">");
		} else {
			toXML(params, xml);
		}
		return xml.toString();
	}

	/**
	 * 获取子结点的xml
	 * 
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(XMLUtil.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}

	@SuppressWarnings("rawtypes")
	public static Map doXMLParse(String strxml) throws JDOMException, IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if (null == strxml || "".equals(strxml)) {
			return null;
		}

		Map m = new HashMap();
		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = XMLUtil.getChildrenText(children);
			}

			m.put(k, v);
		}

		// �ر���
		in.close();

		return m;
	}
	public static void main(String[] args) {
		String xml = "<Body>  <TimeStamp>20130516161422177</TimeStamp>  <Count>50</Count>  <Areas>    <Area>      <AreaId>3543</AreaId>      <AreaName>西城区</AreaName>    </Area>    <Area>      <AreaId>3544</AreaId>      <AreaName>崇文区</AreaName>    </Area>    <Area>      <AreaId>3545</AreaId>      <AreaName>宣武区</AreaName>    </Area>    <Area>      <AreaId>3546</AreaId>      <AreaName>朝阳区</AreaName>    </Area>    <Area>      <AreaId>3547</AreaId>      <AreaName>海淀区</AreaName>    </Area>    <Area>      <AreaId>3548</AreaId>      <AreaName>丰台区</AreaName>    </Area>    <Area>      <AreaId>3549</AreaId>      <AreaName>石景山区</AreaName>    </Area>    <Area>      <AreaId>3550</AreaId>      <AreaName>房山区</AreaName>    </Area>    <Area>      <AreaId>3551</AreaId>      <AreaName>通州区</AreaName>    </Area>    <Area>      <AreaId>3552</AreaId>      <AreaName>顺义区</AreaName>    </Area>    <Area>      <AreaId>3553</AreaId>      <AreaName>门头沟区</AreaName>    </Area>    <Area>      <AreaId>3554</AreaId>      <AreaName>昌平区</AreaName>    </Area>    <Area>      <AreaId>3555</AreaId>      <AreaName>大兴区</AreaName>    </Area>    <Area>      <AreaId>3556</AreaId>      <AreaName>怀柔区</AreaName>    </Area>    <Area>      <AreaId>3557</AreaId>      <AreaName>平谷区</AreaName>    </Area>    <Area>      <AreaId>3558</AreaId>      <AreaName>密云县</AreaName>    </Area>    <Area>      <AreaId>3559</AreaId>      <AreaName>延庆县</AreaName>    </Area>    <Area>      <AreaId>3560</AreaId>      <AreaName>东城区</AreaName>    </Area>    <Area>      <AreaId>3561</AreaId>      <AreaName>北京市</AreaName>    </Area>    <Area>      <AreaId>3569</AreaId>      <AreaName>河西区</AreaName>    </Area>    <Area>      <AreaId>3570</AreaId>      <AreaName>津南区</AreaName>    </Area>    <Area>      <AreaId>3580</AreaId>      <AreaName>蓟县</AreaName>    </Area>    <Area>      <AreaId>3655</AreaId>      <AreaName>石家庄市</AreaName>    </Area>    <Area>      <AreaId>3657</AreaId>      <AreaName>保定</AreaName>    </Area>    <Area>      <AreaId>3658</AreaId>      <AreaName>张家口 </AreaName>    </Area>    <Area>      <AreaId>3661</AreaId>      <AreaName>廊坊</AreaName>    </Area>    <Area>      <AreaId>3668</AreaId>      <AreaName>霸州</AreaName>    </Area>    <Area>      <AreaId>3754</AreaId>      <AreaName>南京市</AreaName>    </Area>    <Area>      <AreaId>3781</AreaId>      <AreaName>杭州</AreaName>    </Area>    <Area>      <AreaId>3797</AreaId>      <AreaName>舟山</AreaName>    </Area>    <Area>      <AreaId>3799</AreaId>      <AreaName>湖州</AreaName>    </Area>    <Area>      <AreaId>3800</AreaId>      <AreaName>安吉</AreaName>    </Area>    <Area>      <AreaId>3803</AreaId>      <AreaName>合肥市</AreaName>    </Area>    <Area>      <AreaId>3809</AreaId>      <AreaName>安庆</AreaName>    </Area>    <Area>      <AreaId>3812</AreaId>      <AreaName>黄山</AreaName>    </Area>    <Area>      <AreaId>3816</AreaId>      <AreaName>池州</AreaName>    </Area>    <Area>      <AreaId>3818</AreaId>      <AreaName>宣城市</AreaName>    </Area>    <Area>      <AreaId>3908</AreaId>      <AreaName>武汉市</AreaName>    </Area>    <Area>      <AreaId>3914</AreaId>      <AreaName>咸宁</AreaName>    </Area>    <Area>      <AreaId>3916</AreaId>      <AreaName>宜昌</AreaName>    </Area>    <Area>      <AreaId>3917</AreaId>      <AreaName>恩施</AreaName>    </Area>    <Area>      <AreaId>3918</AreaId>      <AreaName>十堰</AreaName>    </Area>    <Area>      <AreaId>3921</AreaId>      <AreaName>神农架</AreaName>    </Area>    <Area>      <AreaId>3980</AreaId>      <AreaName>海口市</AreaName>    </Area>    <Area>      <AreaId>3982</AreaId>      <AreaName>三亚</AreaName>    </Area>    <Area>      <AreaId>3984</AreaId>      <AreaName>琼海</AreaName>    </Area>    <Area>      <AreaId>3992</AreaId>      <AreaName>陵水</AreaName>    </Area>    <Area>      <AreaId>3996</AreaId>      <AreaName>定安</AreaName>    </Area>    <Area>      <AreaId>4000</AreaId>      <AreaName>万宁</AreaName>    </Area>    <Area>      <AreaId>4004</AreaId>      <AreaName>南宁市</AreaName>    </Area>  </Areas></Body>";
		Map<String, Object> map = fromXML(xml);
		System.out.println(fromXML(xml));
		StringBuilder sb = new StringBuilder();
		toXML(map, sb);
		System.out.println(sb);
	}
}
