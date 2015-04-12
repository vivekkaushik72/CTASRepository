package com.cox.ctas.vf.mx.adapters;
import com.sun.media.jai.codec.ByteArraySeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.TIFFDecodeParam;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedImageAdapter;
import javax.media.jai.RenderedOp;


public class ImageUtility implements ImageUtilityIfc {
	public Point[] convertTiff2PointArrray(byte[] tiff) throws Exception {
		ByteArraySeekableStream stream = new ByteArraySeekableStream(tiff);
		TIFFDecodeParam decodeParam = new TIFFDecodeParam();
		decodeParam.setDecodePaletteAsShorts(true);
		ImageDecoder decoder = ImageCodec.createImageDecoder("tiff", stream,
				decodeParam);
		RenderedImageAdapter ria = new RenderedImageAdapter(
				decoder.decodeAsRenderedImage(0));
		return convertBufferedImage2PointArray(ria.getAsBufferedImage());
	}

	public Point[] convertBufferedImage2PointArray(BufferedImage image) {
		ArrayList points = new ArrayList();

		Point startPoint = findNextStartPoint(image, new Point(0, 0));

		while (startPoint != null) {
			addStrokeFromImage2Points(image, (Point) startPoint.clone(), points);
			startPoint = findNextStartPoint(image, startPoint);
		}

		Point[] pArray = new Point[points.size()];
		points.toArray(pArray);
		return pArray;
	}

	private Point findNextStartPoint(BufferedImage image, Point startPoint) {
		int width = image.getWidth();
		int height = image.getHeight();
		System.out.println("Width: " + width + "  Height: " + height);
		int xStart = startPoint.x;
		Point point = null;
		boolean found = false;

		for (int y = startPoint.y; (y < height) && (!(found)); ++y) {
			System.out.println("Startpoint: " + startPoint);
			for (int x = xStart; (x < width) && (!(found)); ++x) {
				System.out.println("Before: x: " + x + "  y: " + y);
				if (image.getRGB(x, y) != -1) {
					System.out.println("After: x: " + x + "  y: " + y);
					point = new Point(x, y);
					found = true;
				}

				xStart = 0;
			}
		}

		return point;
	}

	private void addStrokeFromImage2Points(BufferedImage image,
			Point startOfStroke, ArrayList<Point> points) {
		points.add(startOfStroke);

		image.setRGB(startOfStroke.x, startOfStroke.y, -1);
		Point startOfLine = (Point) startOfStroke.clone();

		boolean anotherLineIsAvailable = true;
		while (anotherLineIsAvailable) {
			Point contiguousPoint = findContiguousPoint(image, startOfLine);

			if (contiguousPoint != null) {
				int yDirection = contiguousPoint.y - startOfLine.y;
				int xDirection = contiguousPoint.x - startOfLine.x;

				Point endPoint = findEndOfLine(image, startOfLine, xDirection,
						yDirection);

				points.add((Point) endPoint.clone());
				startOfLine = (Point) endPoint.clone();
			} else {
				anotherLineIsAvailable = false;
			}

		}

		points.add(new Point(65535, 65535));
	}

	private Point findContiguousPoint(BufferedImage image, Point startPoint) {
		int width = image.getWidth();
		int height = image.getHeight();
		int x = 0;
		int y = 0;
		boolean found = false;

		if (startPoint.x > 0) {
			x = startPoint.x - 1;
			y = startPoint.y;
			if (image.getRGB(x, y) != -1) {
				found = true;
			}

		}

		if ((!(found)) && (startPoint.x > 0) && (startPoint.y < height)) {
			x = startPoint.x - 1;
			y = startPoint.y + 1;
			if (image.getRGB(x, y) != -1) {
				found = true;
			}

		}

		if ((!(found)) && (startPoint.y < height)) {
			x = startPoint.x;
			y = startPoint.y + 1;
			if (image.getRGB(x, y) != -1) {
				found = true;
			}

		}

		if ((!(found)) && (startPoint.x < width) && (startPoint.y < height)) {
			x = startPoint.x + 1;
			y = startPoint.y + 1;
			if (image.getRGB(x, y) != -1) {
				found = true;
			}

		}

		if ((!(found)) && (startPoint.x < width)) {
			x = startPoint.x + 1;
			y = startPoint.y;
			if (image.getRGB(x, y) != -1) {
				found = true;
			}

		}

		if ((!(found)) && (startPoint.x < width) && (startPoint.y > 0)) {
			x = startPoint.x + 1;
			y = startPoint.y - 1;
			if (image.getRGB(x, y) != -1) {
				found = true;
			}

		}

		if ((!(found)) && (startPoint.y > 0)) {
			x = startPoint.x;
			y = startPoint.y - 1;
			if (image.getRGB(x, y) != -1) {
				found = true;
			}

		}

		if ((!(found)) && (startPoint.x > 0) && (startPoint.y > 0)) {
			x = startPoint.x - 1;
			y = startPoint.y - 1;
			if (image.getRGB(x, y) != -1) {
				found = true;
			}

		}

		Point point = null;
		if (found) {
			point = new Point(x, y);
		}

		return point;
	}

	private Point findEndOfLine(BufferedImage image, Point startPoint,
			int xDirection, int yDirection) {
		Point endPoint = null;
		int x = startPoint.x;
		int y = startPoint.y;
		boolean end = false;

		image.setRGB(x, y, -1);

		while (!(end)) {
			x += xDirection;
			y += yDirection;
			if (image.getRGB(x, y) == -1) {
				end = true;
			}

			endPoint = new Point(x, y);

			image.setRGB(x, y, -1);
		}

		return endPoint;
	}

	public void convertPoints2JpegAndSave(String filename, int imageWidth,
			int imageHeight, Point[] points) throws Exception {
		BufferedImage image = convertPoints2BufferedImage(imageWidth,
				imageHeight, points);
		File file = new File(filename);
		ImageIO.write(image, "jpeg", file);
	}

	public BufferedImage convertPoints2BufferedImage(int imageWidth,
			int imageHeight, Point[] points) throws Exception {
		int minY = 65535;
		int minX = 65535;
		int maxY = 0;
		int maxX = 0;
		for (Point point : points) {
			if ((point.x != 65535) && (point.x < minX)) {
				minX = point.x;
			}
			if ((point.y != 65535) && (point.y < minY)) {
				minY = point.y;
			}
			if ((point.x != 65535) && (point.x > maxX)) {
				maxX = point.x;
			}
			if ((point.y == 65535) || (point.y <= maxY))
				continue;
			maxY = point.y;
		}

		int pointsWidth = maxX - minX + 1;
		int pointsHeight = maxY - minY + 1;

		float scaleX = imageWidth / pointsWidth;
		float scaleY = imageHeight / pointsHeight;

		double scale = 0.0D;
		int offsetX = 0;
		int offsetY = 0;

		if (scaleX < scaleY) {
			scale = scaleX;
			offsetY = (int) ((imageHeight - (pointsHeight * scale)) / 2.0D);
		} else {
			scale = scaleY;
			offsetX = (int) ((imageWidth - (pointsWidth * scale)) / 2.0D);
		}

		BufferedImage bufferedImage = new BufferedImage(imageWidth,
				imageHeight, 1);
        System.out.println("Buffered Image: Width: " + imageWidth + "  Height: " + imageHeight);
        
		Graphics2D graphics = bufferedImage.createGraphics();
		graphics.setPaint(Color.WHITE);
		graphics.fillRect(0, 0, bufferedImage.getWidth(),
				bufferedImage.getHeight());
		graphics.setColor(Color.BLACK);

		Point point1 = null;
		Point point2 = null;

		for (Point point : points) {
			if ((point.x == 65535) && (point.y == 65535)) {
				point1 = null;
				point2 = null;
			} else if (point1 == null) {
				point1 = point;
			} else {
				point2 = point;
				if ((point.x <= maxX) && (point.x > -1) && (point.y <= maxY)
						&& (point.y > -1)) {
					Point scaled1 = new Point(
							(int) ((point1.x - minX) * scale),
							(int) ((point1.y - minY) * scale));
					Point scaled2 = new Point(
							(int) ((point2.x - minX) * scale),
							(int) ((point2.y - minY) * scale));

					graphics.drawLine(scaled1.x + offsetX, scaled1.y + offsetY,
							scaled2.x + offsetX, scaled2.y + offsetY);
				}

				point1 = point2;
				point2 = null;
			}

		}

		return bufferedImage;
	}

	public void saveTiffImage(String filename, byte[] tiff) throws Exception {
		ByteArraySeekableStream stream = new ByteArraySeekableStream(tiff);
		TIFFDecodeParam decodeParam = new TIFFDecodeParam();
		decodeParam.setDecodePaletteAsShorts(true);
		ImageDecoder decoder = ImageCodec.createImageDecoder("tiff", stream,
				decodeParam);
		ParameterBlock pb = new ParameterBlock();
		pb.add(decoder.getInputStream());

		RenderedOp op = JAI.create("TIFF", pb);

		FileOutputStream osJpg = new FileOutputStream(filename);
		JAI.create("encode", op, osJpg, "TIFF", null);

		JAI.create("filestore", op, filename, "TIFF", null);
	}

	public static void main(String[] args) {
		System.out.println("testing ImageUtility...");

		ImageUtility imageUtil = new ImageUtility();

		Point[] starPolyline = { new Point(3000, 6000), new Point(4000, 2000),
				new Point(65535, 65535), new Point(4000, 2000),
				new Point(5000, 6000), new Point(65535, 65535),
				new Point(5000, 6000), new Point(2000, 4000),
				new Point(65535, 65535), new Point(2000, 4000),
				new Point(6000, 4000), new Point(65535, 65535),
				new Point(6000, 4000), new Point(3000, 6000),
				new Point(65535, 65535) };

		Point[] wideRectaglePolyline = { new Point(3000, 6000),
				new Point(9000, 6000), new Point(9000, 5000),
				new Point(3000, 5000), new Point(3000, 5900),
				new Point(65535, 65535) };

		Point[] diagnalPolyline = { new Point(3000, 6050),
				new Point(3000, 6000), new Point(4000, 7000),
				new Point(4000, 6950), new Point(65535, 65535) };
		try {
			System.out.println("saving star into 400x100 jpg...");
			imageUtil.convertPoints2JpegAndSave("star-400x100.jpg", 400, 100,
					starPolyline);

			System.out.println("saving star into 40x10 jpg...");
			imageUtil.convertPoints2JpegAndSave("star-40x10.jpg", 40, 10,
					starPolyline);

			System.out.println("saving wide rectangle into 400x100 jpg...");
			imageUtil.convertPoints2JpegAndSave("widerect-400x100.jpg", 400,
					100, wideRectaglePolyline);

			System.out.println("saving diagnal line into 400x100 jpg...");
			imageUtil.convertPoints2JpegAndSave("diagline-400x100.jpg", 400,
					100, diagnalPolyline);

			System.out.println("saving diagnal line into 400x400 jpg...");
			imageUtil.convertPoints2JpegAndSave("diagline-400x400.jpg", 400,
					400, diagnalPolyline);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("done.");
	}

}

