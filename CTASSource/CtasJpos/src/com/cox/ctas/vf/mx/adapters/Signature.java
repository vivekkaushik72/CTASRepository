package com.cox.ctas.vf.mx.adapters;

import java.awt.Point;
import java.util.StringTokenizer;

/**
 * original class located in vfjpos jar in the jpos.test package.
 * duplicated and added to the Cox package because the constructors 
 * were protected and are inaccessible to the CoxVfMxSigCapAdapter class
 *
 * @author MSCM
 *
 */
public class Signature {
	private final short maximumX_;
	private final short maximumY_;
	private final Point[] points_;

	protected Signature(int paramInt1, int paramInt2,
			Point[] paramArrayOfPoint, boolean paramBoolean1,
			boolean paramBoolean2) {
		if ((paramArrayOfPoint == null) || (paramArrayOfPoint.length == 0)) {
			this.maximumX_ = 0;
			this.maximumY_ = 0;
			this.points_ = new Point[0];
		} else {
			int i = 2048;
			int j = 1536;
			this.maximumX_ = (short) i;
			this.maximumY_ = (short) j;
			this.points_ = flip(this.maximumX_, this.maximumY_,
					paramArrayOfPoint, paramBoolean1, paramBoolean2);
		}
	}

	protected Signature(Point[] paramArrayOfPoint, boolean paramBoolean1,
			boolean paramBoolean2) {
		if ((paramArrayOfPoint == null) || (paramArrayOfPoint.length == 0)) {
			this.maximumX_ = 0;
			this.maximumY_ = 0;
			this.points_ = new Point[0];
		} else {
			int i = 2048;
			int j = 1536;
			this.maximumX_ = (short) i;
			this.maximumY_ = (short) j;
			this.points_ = flip(this.maximumX_, this.maximumY_,
					paramArrayOfPoint, paramBoolean1, paramBoolean2);
		}
	}

	private int shiftY(Point[] paramArrayOfPoint) {
		short[] arrayOfShort = { 32767, -32768 };
		for (int i = 0; i < paramArrayOfPoint.length; ++i)
			evaluate(arrayOfShort, (short) paramArrayOfPoint[i].y);
		if (arrayOfShort[0] > 0) {
			for (int i = 0; i < paramArrayOfPoint.length; ++i) {
				if (paramArrayOfPoint[i].y <= 0)
					continue;
				paramArrayOfPoint[i].y -= arrayOfShort[0];
			}
			int tmp87_86 = 1;
			short[] tmp87_85 = arrayOfShort;
			tmp87_85[tmp87_86] = (short) (tmp87_85[tmp87_86] - arrayOfShort[0]);
		}
		return arrayOfShort[1];
	}

	private int shiftX(Point[] paramArrayOfPoint) {
		short[] arrayOfShort = { 32767, -32768 };
		for (int i = 0; i < paramArrayOfPoint.length; ++i)
			evaluate(arrayOfShort, (short) paramArrayOfPoint[i].x);
		if (arrayOfShort[0] > 0) {
			for (int i = 0; i < paramArrayOfPoint.length; ++i) {
				if (paramArrayOfPoint[i].x <= 0)
					continue;
				paramArrayOfPoint[i].x -= arrayOfShort[0];
			}
			int tmp87_86 = 1;
			short[] tmp87_85 = arrayOfShort;
			tmp87_85[tmp87_86] = (short) (tmp87_85[tmp87_86] - arrayOfShort[0]);
		}
		return arrayOfShort[1];
	}

	private void evaluate(short[] paramArrayOfShort, short paramShort) {
		if (paramShort < -1)
			return;
		if (paramShort > paramArrayOfShort[1])
			paramArrayOfShort[1] = paramShort;
		if (paramShort >= paramArrayOfShort[0])
			return;
		paramArrayOfShort[0] = paramShort;
	}

	private Point[] flip(int paramInt1, int paramInt2,
			Point[] paramArrayOfPoint, boolean paramBoolean1,
			boolean paramBoolean2) {
		Point[] arrayOfPoint = paramArrayOfPoint;
		if (paramBoolean1)
			arrayOfPoint = flipHorizontal(arrayOfPoint, paramInt2);
		if (paramBoolean2)
			arrayOfPoint = flipVertical(arrayOfPoint, paramInt1);
		return arrayOfPoint;
	}

	private Point[] flipHorizontal(Point[] paramArrayOfPoint, int paramInt) {
		Point[] arrayOfPoint = new Point[paramArrayOfPoint.length];
		for (int i = 0; i < paramArrayOfPoint.length; ++i) {
			int j = paramArrayOfPoint[i].x;
			int k = paramArrayOfPoint[i].y;
			if ((j >= 0) && (k >= 0))
				k = paramInt - k;
			arrayOfPoint[i] = new Point(j, k);
		}
		return arrayOfPoint;
	}

	private Point[] flipVertical(Point[] paramArrayOfPoint, int paramInt) {
		Point[] arrayOfPoint = new Point[paramArrayOfPoint.length];
		for (int i = 0; i < paramArrayOfPoint.length; ++i) {
			int j = paramArrayOfPoint[i].x;
			int k = paramArrayOfPoint[i].y;
			if ((j >= 0) && (k >= 0))
				j = paramInt - j;
			arrayOfPoint[i] = new Point(j, k);
		}
		return arrayOfPoint;
	}

	public Signature(String paramString) {
		try {
			String[] arrayOfString = split(paramString, ":");
			Point localPoint = parsePoint(arrayOfString[0]);
			this.maximumX_ = (short) localPoint.x;
			this.maximumY_ = (short) localPoint.y;
			this.points_ = new Point[arrayOfString.length - 1];
			for (int i = 1; i < arrayOfString.length; ++i)
				this.points_[(i - 1)] = parsePoint(arrayOfString[i]);
		} catch (NumberFormatException localNumberFormatException) {
			throw new IllegalArgumentException('"' + paramString + '"');
		}
	}

	private static Point parsePoint(String paramString) {
		String[] arrayOfString = split(paramString, ",");
		return new Point(Integer.parseInt(arrayOfString[0]),
				Integer.parseInt(arrayOfString[1]));
	}

	public String getData() {
		StringBuffer localStringBuffer = new StringBuffer(1000);
		localStringBuffer.append(this.maximumX_).append(",")
		.append(this.maximumY_).append(":");
		for (int i = 0; i < this.points_.length; ++i) {
			Point localPoint = this.points_[i];
			localStringBuffer.append(localPoint.x).append(",")
			.append(localPoint.y).append(":");
		}
		localStringBuffer.setLength(localStringBuffer.length() - 1);
		return localStringBuffer.toString();
	}

	public int getMaximumX() {
		return this.maximumX_;
	}

	public int getMaximumY() {
		return this.maximumY_;
	}

	public Point[] getPoints() {
		if (this.points_ == null)
			return new Point[0];
		Point[] arrayOfPoint = new Point[this.points_.length];
		System.arraycopy(this.points_, 0, arrayOfPoint, 0, this.points_.length);
		return arrayOfPoint;
	}

	public static String[] split(String paramString1, String paramString2) {
		int i = 0;
		StringBuffer[] arrayOfStringBuffer = new StringBuffer[10000];
		StringTokenizer localStringTokenizer = new StringTokenizer(
				paramString1, paramString2);
		while (localStringTokenizer.hasMoreTokens()) {
			Object localObject = localStringTokenizer.nextToken();
			arrayOfStringBuffer[(i++)].append((String) localObject);
		}
		Object localObject = new String[arrayOfStringBuffer.length];
		for (i = 0; i < arrayOfStringBuffer.length; ++i)
			localObject = (arrayOfStringBuffer[i].toString());
		return ((String[])localObject); 
	}

}
