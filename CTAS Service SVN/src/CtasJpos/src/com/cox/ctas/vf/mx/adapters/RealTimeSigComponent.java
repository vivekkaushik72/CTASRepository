package com.cox.ctas.vf.mx.adapters;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.RepaintManager;

/**
 * original class located in vfjpos jar in the jpos.test package.
 * duplicated and added to the Cox package because the constructors 
 * were protected and are inaccessible to the CoxVfMxSigCapAdapter class
 *
 * @author MSCM
 *
 */

public class RealTimeSigComponent extends JComponent{
private static final long serialVersionUID = 1L;
	
	private static final Dimension EMPTY_DIMENSION = new Dimension(0, 0);
	private Signature signature_;
	private int maxX_;
	private int maxY_;
	static int offsetX;
	static int offsetY;
	boolean firstTime = true;
	ArrayList pointList = new ArrayList();
	static int listIndex = 1;
	static int marker = 1;
	static int lastX = -1;
	static int lastY = -1;
	private Object oos = new Object();
	int sizeOfArray = 1;

	public Signature getSignature() {
		return this.signature_;
	}

	public void resetArray() {
		this.pointList.clear();
	}

	public void setSignature(Signature paramSignature) {
		if (this.signature_ == paramSignature)
			return;
		this.signature_ = paramSignature;
		if (this.signature_ == null) {
			setMinimumSize(EMPTY_DIMENSION);
			setPreferredSize(EMPTY_DIMENSION);
			setSize(EMPTY_DIMENSION);
		} else {
			this.maxX_ = 0;
			this.maxY_ = 0;
			Point[] arrayOfPoint = this.signature_.getPoints();
			this.maxX_ = 2048;
			this.maxY_ = 1536;
			System.out.println();
			setMinimumSize(new Dimension(2 * this.maxX_, 2 * this.maxY_));
			Dimension localDimension = new Dimension(this.maxX_ + 100,
					this.maxY_ + 100);
			setPreferredSize(null);
			setSize(localDimension);
			this.sizeOfArray = (this.pointList.size() + 1);
			for (int i = 0; i < arrayOfPoint.length; ++i)
				this.pointList.add(arrayOfPoint[i]);
		}
		if (null != getParent())
			return;
		RepaintManager.currentManager(this).markCompletelyDirty(this);
	}

	private double getScale(Dimension paramDimension) {
		if ((paramDimension.getWidth() < this.maxX_)
				|| (paramDimension.getHeight() < this.maxY_)) {
			double d1 = paramDimension.getWidth() / this.maxX_;
			double d2 = paramDimension.getHeight() / this.maxY_;
			return Math.min(d1, d2);
		}
		return 1.0D;
	}

	public void paintComponent(Graphics paramGraphics) {
		if ((this.signature_ == null) || (this.maxX_ <= 0) || (this.maxY_ <= 0))
			return;
		Dimension localDimension = getSize();
		if (isOpaque()) {
			paramGraphics.setColor(Color.white);
			paramGraphics.fillRect(0, 0, localDimension.width,
					localDimension.height);
		}
		double d = getScale(localDimension);
		offsetX = (int) ((localDimension.getWidth() - (this.maxX_ * d)) / 2.0D);
		offsetY = (int) ((localDimension.getHeight() - (this.maxY_ * d)) / 2.0D)
				+ (int) (this.maxY_ * d);
		Point[] arrayOfPoint = this.signature_.getPoints();
		paramGraphics.setColor(Color.black);
		int i = arrayOfPoint.length;
		for (int j = 1; j < this.pointList.size(); ++j) {
			Point localPoint1 = (Point) this.pointList.get(j - 1);
			if (localPoint1.x < 0)
				continue;
			if (localPoint1.y < 0)
				continue;
			Point localPoint2 = (Point) this.pointList.get(j);
			if (localPoint2.x < 0)
				continue;
			if (localPoint2.y < 0)
				continue;
			paramGraphics.drawLine((int) (offsetX + localPoint1.x * d),
					(int) (offsetY - (localPoint1.y * d)),
					(int) (offsetX + localPoint2.x * d),
					(int) (offsetY - (localPoint2.y * d)));
		}
	}

}
