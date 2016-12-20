package com.hoaven.common.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.codec.binary.Base64;

public class ImageUtils {

	public static byte[] imgBytes(String imgPath) {
		byte[] imgData = null;
		FileImageInputStream fis = null;
		try {
			imgPath = imgPath.replace("\"", "");
			imgPath = imgPath.replace("'", "");
			fis = new FileImageInputStream(new File(imgPath));
			imgData = new byte[(int) fis.length()];
			fis.read(imgData, 0, imgData.length);
		} catch (Exception e) {
			imgData = null;
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (Exception e) {
				imgData = null;
			}
		}
		return imgData;
	}

	public static String img2Base64(String imgFile) {
		byte[] bytes = imgBytes(imgFile);
		return new String(Base64.encodeBase64(bytes));
	}

	public static void bytes2Img(byte[] imgByte, String destFile) {
		FileImageOutputStream fios = null;
		try {
			fios = new FileImageOutputStream(new File(destFile));
			fios.write(imgByte, 0, imgByte.length);
		} catch (Exception e) {
		} finally {
			if (fios != null) {
				try {
					fios.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static String img2Str(String imgFile) {
		String suffix = imgFile.substring(imgFile.lastIndexOf(".") + 1);
		StringBuilder sb = new StringBuilder("data:image/");
		sb.append(suffix).append(";base64,");
		sb.append(img2Base64(imgFile));
		return sb.toString();
	}

	public static String getThumbString(String data, String suffix,
			final float thumbWidth, final float thumbHeight) {
		ByteArrayInputStream bis = null;
		ByteArrayOutputStream bos = null;
		StringReader sr = null;
		BufferedReader br = null;
		try {
			bis = new ByteArrayInputStream(Base64.decodeBase64(data.getBytes()));
			BufferedImage srcImg = ImageIO.read(bis);
			float srcWidth = srcImg.getWidth();
			float srcHeight = srcImg.getHeight();
			BufferedImage dstImage = null;
			AffineTransform transform = AffineTransform.getScaleInstance(
					thumbWidth / srcWidth, thumbHeight / srcHeight);
			AffineTransformOp op = new AffineTransformOp(transform,
					AffineTransformOp.TYPE_BILINEAR);
			dstImage = op.filter(srcImg, null);
			bos = new ByteArrayOutputStream();
			ImageIO.write(dstImage, suffix, bos);
			String base64Str = new String(
					Base64.encodeBase64(bos.toByteArray()));
			sr = new StringReader(base64Str);
			br = new BufferedReader(sr);
			String line = null;
			String temp = "";
			while ((line = br.readLine()) != null) {
				temp += line;
			}
			base64Str = temp;
			StringBuilder sb = new StringBuilder("data:image/");
			sb.append(suffix).append(";base64,");
			sb.append(base64Str);
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
			if (sr != null) {
				sr.close();
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}

}