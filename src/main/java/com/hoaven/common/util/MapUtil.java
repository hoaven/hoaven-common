package com.hoaven.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class MapUtil {

	public static Map<String, String> fromUrlString(String value) {
		if (!UtilTools.isEmpty(value)) {
			String[] paras = value.split("&");
			Map<String, String> map = new HashMap<String, String>();
			try {
				for (int i = 0; i < paras.length; i++) {
					String[] keys = paras[i].split("=");
					if (keys != null && keys.length > 0) {
						if (keys.length == 1) {
							map.put(keys[0], "");
						} else {
							map.put(keys[0],
									URLDecoder.decode(keys[1], "UTF-8"));
						}
					}
				}
			} catch (UnsupportedEncodingException e1) {
				throw new RuntimeException("Invaild response.");
			}
			return map;
		} else {
			return null;
		}
	}

	public static Map<String, Object> objfromUrlString(String value) {
		if (!UtilTools.isEmpty(value)) {
			String[] paras = value.split("&");
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				for (int i = 0; i < paras.length; i++) {
					String[] keys = paras[i].split("=");
					if (keys != null && keys.length > 0) {
						if (keys.length == 1) {
							map.put(keys[0], "");
						} else {
							map.put(keys[0],
									URLDecoder.decode(keys[1], "UTF-8"));
						}
					}
				}
			} catch (UnsupportedEncodingException e1) {
				throw new RuntimeException("Invaild response.");
			}
			return map;
		} else {
			return null;
		}
	}

}
