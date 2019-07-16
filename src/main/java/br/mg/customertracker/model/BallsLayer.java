package br.mg.customertracker.model;

import org.mapsforge.core.graphics.Canvas;
import org.mapsforge.core.graphics.Paint;
import org.mapsforge.core.model.BoundingBox;
import org.mapsforge.core.model.Point;
import org.mapsforge.core.util.MercatorProjection;
import org.mapsforge.map.awt.graphics.AwtGraphicFactory;
import org.mapsforge.map.layer.Layer;

public class BallsLayer extends Layer {

	private int tileSize;

	public BallsLayer(int tileSize) {
		this.tileSize = tileSize;
	}

	@Override
	public void draw(BoundingBox boundingBox, byte zoomLevel, Canvas canvas, Point topLeftPoint) {

		int paintBall = AwtGraphicFactory.INSTANCE.createColor(180, 0, 255, 0);
		Paint paintAwt = AwtGraphicFactory.INSTANCE.createPaint();
		paintAwt.setColor(paintBall);
		
		int x = (int) (MercatorProjection.longitudeToPixelX(-47.9292, zoomLevel, tileSize) - topLeftPoint.x);
		int y = (int) (MercatorProjection.latitudeToPixelY(-15.7801, zoomLevel, tileSize) - topLeftPoint.y);
		
		if(zoomLevel >= 8) {
			int paintText = AwtGraphicFactory.INSTANCE.createColor(255,0,0,0);
			Paint paintToText = AwtGraphicFactory.INSTANCE.createPaint();
			paintToText.setColor(paintText);
			canvas.drawText("Teste", x , (int)(y-50), paintToText);
		}

		canvas.drawCircle(x, y, 50, paintAwt);
	}

}
