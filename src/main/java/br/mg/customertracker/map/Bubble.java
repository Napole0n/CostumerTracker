package br.mg.customertracker.map;

import java.awt.Color;

import org.mapsforge.core.graphics.Paint;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.awt.graphics.AwtGraphicFactory;

public class Bubble {

	private LatLong position;
	private Paint color;
	private double radius;
	private String label;

	public Bubble(LatLong where, Color color, double radius, String label) {
		this.position = where;
		int paintInt = AwtGraphicFactory.INSTANCE.createColor(color.getAlpha(), color.getGreen(), color.getRed(),
				color.getBlue());
		Paint paint = AwtGraphicFactory.INSTANCE.createPaint();
		paint.setColor(paintInt);
		this.radius = radius;
		this.color = paint;
	}

	public LatLong getPosition() {
		return position;
	}

	public void setPosition(LatLong position) {
		this.position = position;
	}

	public Paint getColor() {
		return color;
	}

	public void setColor(Paint color) {
		this.color = color;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
