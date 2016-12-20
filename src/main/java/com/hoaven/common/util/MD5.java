package com.hoaven.common.util;

import java.security.MessageDigest;

public class MD5 {
	public static String md5(String string) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] bytes = string.getBytes();
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(bytes);
			byte[] updateBytes = messageDigest.digest();
			int len = updateBytes.length;
			char myChar[] = new char[len * 2];
			int k = 0;
			for (int i = 0; i < len; i++) {
				byte byte0 = updateBytes[i];
				myChar[k++] = hexDigits[byte0 >>> 4 & 0x0f];
				myChar[k++] = hexDigits[byte0 & 0x0f];
			}
			return new String(myChar);
		} catch (Exception e) {
			return "";
		}
	}


	public static void main(String[] args) {
		System.out
				.println(MD5
.md5(
				"attach=关税&bank_type=2011&discount=0&fee_type=CNY&input_charset=UTF-8&notify_id=6JEBXK7mK8NCbqV-dwfR-C9mTUWHe7RMFcT7tsgQvIztYjGGiY3566Nnko_1_cEqCk9cCFHD13nZ-fkCca_E_pLqVVcp5cfq&out_trade_no=111222114&partner=1223438901&sign_type=MD5&time_end=20141118173140&total_fee=1&trade_mode=1&trade_state=0&transaction_id=1223438901201411186035738096&key=05934e4c47400b51061d498152099e8b"));


		System.out.println(MD5.md5( "mTypemSysGoodsTimeStamp"
				+ "uCode" + "liuhe" ).toUpperCase());
	}


}
