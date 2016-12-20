package com.hoaven.common.util;

import java.util.Random;

/**
 * 随机数、随即字符串工具
 */
public class RandomUtils {
	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String numberChar = "0123456789";
	public static final String upperCaseChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 返回一个定长的随机字符串(只包含大小写字母、数字)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机字符串(只包含大小写字母、数字)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateNumberUpperString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(upperCaseChar.charAt(random.nextInt(upperCaseChar
					.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机字符串(只包含小写字母、数字)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateStringLowerString(int length) {
		return generateString(length).toLowerCase();
	}

	/**
	 * 返回一个定长的随机纯字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateMixString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(letterChar.charAt(random.nextInt(letterChar.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机纯大写字母字符串(只包含小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateLowerString(int length) {
		return generateMixString(length).toLowerCase();
	}

	/**
	 * 返回一个定长的随机纯小写字母字符串(只包含大写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateUpperString(int length) {
		return generateMixString(length).toUpperCase();
	}

	/**
	 * 生成一个定长的纯0字符串
	 * 
	 * @param length
	 *            字符串长度
	 * @return 纯0字符串
	 */
	public static String generateZeroString(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append('0');
		}
		return sb.toString();
	}

	/**
	 * 根据数字生成一个定长的字符串，长度不够前面补0
	 * 
	 * @param num
	 *            数字
	 * @param fixdlenth
	 *            字符串长度
	 * @return 定长的字符串
	 */
	public static String toFixdLengthString(long num, int fixdlenth) {
		StringBuffer sb = new StringBuffer();
		String strNum = String.valueOf(num);
		if (fixdlenth - strNum.length() >= 0) {
			sb.append(generateZeroString(fixdlenth - strNum.length()));
		} else {
			throw new RuntimeException("number to string error: " + num + ", "
					+ fixdlenth);
		}
		sb.append(strNum);
		return sb.toString();
	}

	/**
	 * 根据数字生成一个定长的字符串，长度不够前面补0
	 * 
	 * @param num
	 *            数字
	 * @param fixdlenth
	 *            字符串长度
	 * @return 定长的字符串
	 */
	public static String toFixdLengthString(int num, int fixdlenth) {
		StringBuffer sb = new StringBuffer();
		String strNum = String.valueOf(num);
		if (fixdlenth - strNum.length() >= 0) {
			sb.append(generateZeroString(fixdlenth - strNum.length()));
		} else {
			throw new RuntimeException("number to string error: " + num + ", "
					+ fixdlenth);
		}
		sb.append(strNum);
		return sb.toString();
	}

	/**
	 * 生成一个随机的字符串
	 * 
	 * @param length
	 *            长度
	 * @param baseString
	 *            字符的范围
	 * @return
	 */
	public static String generateString(int length, String baseString) {
		StringBuffer b = new StringBuffer();
		// final String baseString = numberChar;
		final int bsLength = baseString.length();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int r = random.nextInt(bsLength);
			b.append(baseString.charAt(r));
		}
		return b.toString();
	}

	/**
	 * 生成一个随机的数字字符串
	 * 
	 * @param length
	 *            长度
	 * @return
	 */
	public static String generateNumber(int length) {
		return generateString(length, numberChar);
	}
	
	

	/**
	 * 返回一个指定范围内的随机字符串(只包含数字)
	 * 
	 * @param minLength
	 *            字符串最短长度
	 * @param maxLength
	 *            字符串最长长度
	 * @return 随机字符串
	 */
	public static String generateNumberString(long minLength, long maxLength) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		long length = (long) Math.round(Math.random() * (maxLength - minLength) + minLength);
		for (int i = 0; i < length; i++) {
			sb.append(numberChar.charAt(random.nextInt(numberChar
					.length())));
		}
		
		if ("000".equals(sb.toString())) {
			generateNumberString(minLength,maxLength);
		}
		return sb.toString();
	}

	/**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}

	public static void main(String[] args) {
		System.out.println(generateString(15));
		System.out.println(generateMixString(15));
		System.out.println(generateLowerString(15));
		System.out.println(generateUpperString(15));
		System.out.println(generateZeroString(15));
		System.out.println(toFixdLengthString(123, 15));
		System.out.println(toFixdLengthString(123L, 15));
	}
}