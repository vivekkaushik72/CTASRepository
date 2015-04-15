package com.cox.ctas.vf.mx.adapters;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

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
public class SignatureComponent extends JComponent {
	private static final long serialVersionUID = 1L;
	private static final Dimension EMPTY_DIMENSION = new Dimension(0, 0);
	private Signature signature_;
	private short maxX_;
	private short maxY_;
	

	public Signature getSignature() {
		return this.signature_;
	}

	protected void setSignature(Signature paramSignature) {
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
			setMinimumSize(new Dimension(this.maxX_, this.maxY_));
			Dimension localDimension = new Dimension(this.maxX_ + 100,
					this.maxY_ + 100);
			setPreferredSize(null);
			setSize(localDimension);
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
		super.paintComponent(paramGraphics);
		if ((this.signature_ == null) || (this.maxX_ <= 0) || (this.maxY_ <= 0))
			return;
		Dimension localDimension = getSize();
		if (isOpaque()) {
			paramGraphics.setColor(Color.white);
			paramGraphics.fillRect(0, 0, localDimension.width,
					localDimension.height);
		}
		double d = getScale(localDimension);
		int i = (int) ((localDimension.getWidth() - (this.maxX_ * d)) / 2.0D);
		int j = (int) ((localDimension.getHeight() - (this.maxY_ * d)) / 2.0D)
				+ (int) (this.maxY_ * d);
		Point[] arrayOfPoint = this.signature_.getPoints();
		Point localPoint2 = null;
		paramGraphics.setColor(Color.black);
		for (int k = 1; k < arrayOfPoint.length; ++k) {
			Point localPoint1 = arrayOfPoint[(k - 1)];
			if (localPoint1.x < 0)
				continue;
			if (localPoint1.y < 0)
				continue;
			localPoint2 = arrayOfPoint[k];
			if (localPoint2.x < 0)
				continue;
			if (localPoint2.y < 0)
				continue;
			paramGraphics.drawLine((int) (i + localPoint1.x * d),
					(int) (j - (localPoint1.y * d)), (int) (i + localPoint2.x
							* d), (int) (j - (localPoint2.y * d)));
		}
	}

}
