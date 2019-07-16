package br.mg.customertracker.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Set;

import javax.swing.Timer;

import org.mapsforge.core.graphics.Canvas;
import org.mapsforge.core.model.BoundingBox;
import org.mapsforge.core.model.Point;
import org.mapsforge.core.util.MercatorProjection;
import org.mapsforge.map.layer.Layer;

import javafx.animation.AnimationTimer;

public class BubblesLayer extends Layer {

	BubbleTimeLine timeLine;
	private Date actualDate;
	private int tileSize;


	public BubblesLayer(BubbleTimeLine timeLine, int tileSize) {
		this.timeLine = timeLine;
		actualDate = timeLine.getMap().firstKey();
		this.tileSize = tileSize;
	}

	public synchronized void dateForward() {
		actualDate = timeLine.getDateAfterDate(actualDate);

	}

	public synchronized void dateBack() {
		actualDate = timeLine.getDateBeforeDate(actualDate);
	}

	public Set<Date> getDateSet() {
		return timeLine.getMap().keySet();

	}

	@Override
	public void draw(BoundingBox boundingBox, byte zoomLevel, Canvas canvas, Point topLeftPoint) {

		for (Bubble b : timeLine.getBubblesForDate(actualDate)) {

			int x = (int) (MercatorProjection.longitudeToPixelX(b.getPosition().longitude, zoomLevel, tileSize)
					- topLeftPoint.x);
			int y = (int) (MercatorProjection.latitudeToPixelY(b.getPosition().latitude, zoomLevel, tileSize)
					- topLeftPoint.y);

			canvas.drawCircle(x, y, (int) (b.getRadius()), b.getColor());

		}

	}

}
