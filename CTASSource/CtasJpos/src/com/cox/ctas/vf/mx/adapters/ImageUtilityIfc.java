package com.cox.ctas.vf.mx.adapters;

import java.awt.Point;
import java.awt.image.BufferedImage;

public interface ImageUtilityIfc {
	public static final int WHITE = -1;
	public static final String SERVICE_IMAGE = "service_ImageUtility";

	public abstract Point[] convertTiff2PointArrray(byte[] paramArrayOfByte)
			throws Exception;

	public abstract Point[] convertBufferedImage2PointArray(
			BufferedImage paramBufferedImage);

	public abstract void convertPoints2JpegAndSave(String paramString,
			int paramInt1, int paramInt2, Point[] paramArrayOfPoint)
			throws Exception;

	public abstract BufferedImage convertPoints2BufferedImage(int paramInt1,
			int paramInt2, Point[] paramArrayOfPoint) throws Exception;

}
