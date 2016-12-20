package com.hoaven.common.util;

import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author jason@wemart.cn
 * @created 2015/6/18
 */
public class StringUtils {
	private static final Pattern SpacePattern = Pattern.compile("(\\s|' ')+");

	public static String concat(Integer[] nums) {
		if (nums == null || nums.length == 0)
			return "";
		else {
			StringBuilder builder = new StringBuilder();
			for (Integer num : nums) {
				if (builder.length() > 0) {
					builder.append(',');
				}
				builder.append(num);
			}
			return builder.toString();
		}
	}

	public static String getParamFillSymbol(int size) {
		if (size > 0) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < size; i++) {
				if (i == 0)
					builder.append("(?");
				else
					builder.append(",?");
			}
			return builder.append(')').toString();
		} else {
			return "";
		}
	}

	public static String concatDefenseSqlInject(Collection<String> collection, String wrapper) {
		if (collection == null || collection.size() == 0)
			return "";
		else {
			StringBuilder builder = new StringBuilder();
			for (String element : collection) {
				if (builder.length() > 0) {
					builder.append(',');
				}
				builder.append(wrapper + element.replace(";", "").replace("'", "").replace("--", "") + wrapper);
			}
			return builder.toString();
		}
	}

	public static <T> String concat(Collection<T> collection) {
		if (collection == null || collection.size() == 0)
			return "";
		else {
			StringBuilder builder = new StringBuilder();
			for (T element : collection) {
				if (builder.length() > 0) {
					builder.append(',');
				}
				builder.append(element);
			}
			return builder.toString();
		}
	}

	public static int[] splitInts(String str, String splitter) {
		if (str == null || str.length() == 0)
			return new int[0];

		String[] splitStrs = str.split(splitter);
		int[] splitInts = new int[splitStrs.length];
		for (int i = 0; i < splitStrs.length; i++) {
			splitInts[i] = Integer.parseInt(splitStrs[i].trim());
		}
		return splitInts;
	}

	public static String[] splitStr(String input, Pattern pattern) {
		if (input == null || input.trim().length() == 0) {
			return null;
		} else {
			return pattern.split(input.trim());
		}
	}

	public static String[] splitStrBySpace(String input) {
		return splitStr(input, SpacePattern);
	}

	public static String addEndTimeForDateString(String dateString) {
		if (dateString != null && dateString.length() > 0 && dateString.indexOf(':') < 10) {
			return dateString + " 23:59:59";
		} else {
			return dateString;
		}
	}

	public static boolean isStringEmpty(String s) {
		return s == null || "".equals(s);
	}

	public static void main(String[] args) {
		System.out.println(splitInts(",,,", ",").length);

		String x = "1       2     三";
		int i = 0;
		for (String string : splitStrBySpace(x)) {
			System.out.println(++i + ":" + string);
		}

		String y = "12";
		i = 0;
		for (String string : splitStrBySpace(y)) {
			System.out.println(++i + ":" + string);
		}
	}

	/**
	 * 去除sql语句末尾的 ',' 号
	 * 
	 * @param sql
	 * @return
	 */
	public static String getStdSql(String sql) {

		if (sql.lastIndexOf(",") == sql.length() - 1) {

			return sql.substring(0, sql.lastIndexOf(","));
		}

		return sql;

	}

	/**
	 * 拼sql的IN语句
	 * 
	 * @param idList
	 * @return
	 */
	public static String getInStdSql(List idList) {

		StringBuffer inSql = new StringBuffer(" IN ( ");

		int length = idList.size();

		for (int i = 0; i < length; i++) {

			inSql.append("?");

			if (i != length - 1) {

				inSql.append(",");
			}

		}

		inSql.append(" )");
		return inSql.toString();

	}

	/**
	 * 字符串首字母大写
	 * 
	 * @param name
	 * @return
	 */
	public static String captureName(String name) {
		char[] cs = name.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}

	/**
	 * 字符串驼峰模式，并且首字母大写 字符串以 "." 分割
	 * 
	 * @param str
	 * @return
	 */
	public static String getCameCase(String str) {

		String strs[] = str.split("\\.");
		StringBuffer result = new StringBuffer("");

		for (String item : strs) {

			result.append(captureName(item));
		}

		return result.toString();
	}

	public static String getSubStrWithEllipsis(String str, int length) {
		if (str != null && str.length() > 12) {
			return str.substring(0, 10) + "…";
		}

		return str;

	}
}
