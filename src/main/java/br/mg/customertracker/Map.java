package br.mg.customertracker;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Date;
import java.util.UUID;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.mapsforge.core.graphics.GraphicFactory;
import org.mapsforge.core.model.BoundingBox;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.MapPosition;
import org.mapsforge.core.model.Point;
import org.mapsforge.core.util.LatLongUtils;
import org.mapsforge.core.util.MercatorProjection;
import org.mapsforge.core.util.Parameters;
import org.mapsforge.map.awt.graphics.AwtGraphicFactory;
import org.mapsforge.map.awt.util.AwtUtil;
import org.mapsforge.map.awt.util.JavaPreferences;
import org.mapsforge.map.awt.view.MapView;
import org.mapsforge.map.layer.Layers;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.debug.TileCoordinatesLayer;
import org.mapsforge.map.layer.debug.TileGridLayer;
import org.mapsforge.map.layer.download.TileDownloadLayer;
import org.mapsforge.map.layer.download.tilesource.OpenStreetMapMapnik;
import org.mapsforge.map.layer.download.tilesource.TileSource;
import org.mapsforge.map.model.IMapViewPosition;
import org.mapsforge.map.model.Model;
import org.mapsforge.map.model.common.PreferencesFacade;

import br.mg.customertracker.exception.InvalidSizeException;
import br.mg.customertracker.map.BallsLayer;
import br.mg.customertracker.map.BubblesLayer;
import br.mg.customertracker.map.TestDataGenerator;

public class Map {

	private static final GraphicFactory GRAPH_FACTORY = AwtGraphicFactory.INSTANCE;
	private static final int TILE_SIZE = 256;
	private static final int MAP_WIDTH = 1024;
	private static final int MAP_HEIGHT = 768;
	private static BubblesLayer bubbleLayer;

	public static void main(String[] args) throws InvalidSizeException {

		// define renderização multi-thread
		Parameters.NUMBER_OF_THREADS = 8;
		Parameters.SQUARE_FRAME_BUFFER = false;
		// instancia a mapView
		final MapView mapView = createMapView();
		final BoundingBox boundingBox = configure(mapView);
		setupWindow(mapView, boundingBox);

	}

	private static void setupWindow(MapView view, BoundingBox bb) {
		final PreferencesFacade preferencesFacade = new JavaPreferences(Preferences.userNodeForPackage(Map.class));
		JFrame frame = new JFrame();
		frame.setTitle("Mapa");
		frame.add(view);
		frame.pack();
		frame.setSize(MAP_WIDTH, MAP_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				view.getModel().save(preferencesFacade);
				view.destroyAll();
				AwtGraphicFactory.clearResourceMemoryCache();
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			}

			@Override
			public void windowOpened(WindowEvent e) {
				final Model model = view.getModel();
				model.init(preferencesFacade);
				if (model.mapViewPosition.getZoomLevel() == 0 || !bb.contains(model.mapViewPosition.getCenter())) {
					byte zoomLevel = LatLongUtils.zoomForBounds(model.mapViewDimension.getDimension(), bb,
							model.displayModel.getTileSize());
					model.mapViewPosition.setMapPosition(new MapPosition(bb.getCenterPoint(), zoomLevel));
				}
			}
		});

		frame.setVisible(true);
	}

	private static BoundingBox configure(MapView view) throws InvalidSizeException {

		Layers layers = view.getLayerManager().getLayers();
		
		 bubbleLayer = new BubblesLayer(TestDataGenerator.generate(), TILE_SIZE);



		TileCache tileCache = AwtUtil.createTileCache(TILE_SIZE, view.getModel().frameBufferModel.getOverdrawFactor(),
				MAP_WIDTH, new File(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString()));

		BoundingBox bb;
		view.getModel().displayModel.setFixedTileSize(TILE_SIZE);
		// mapa estatico
		OpenStreetMapMapnik tileSource = OpenStreetMapMapnik.INSTANCE;

		tileSource.setUserAgent("mapsforge-samples-awt");
		TileDownloadLayer tileDownloadLayer = createTileDownloadLayer(tileCache, view.getModel().mapViewPosition,
				tileSource);
		layers.add(tileDownloadLayer);
		tileDownloadLayer.start();
		view.setZoomLevelMin(tileSource.getZoomLevelMin());
		view.setZoomLevelMax(tileSource.getZoomLevelMax());
		bb = new BoundingBox(LatLongUtils.LATITUDE_MIN, LatLongUtils.LONGITUDE_MIN, LatLongUtils.LATITUDE_MAX,
				LatLongUtils.LONGITUDE_MAX);
		
		layers.add(bubbleLayer);
		return bb;
	}

	// cria a mapView
	private static MapView createMapView() {
		MapView mapView = new MapView();
		mapView.getMapScaleBar().setVisible(true);
		mapView.getFpsCounter().setVisible(true);
		return mapView;
	}

	// cria o updater de tiles
	private static TileDownloadLayer createTileDownloadLayer(TileCache tileCache, IMapViewPosition mapViewPosition,
			TileSource tileSource) {
		return new TileDownloadLayer(tileCache, mapViewPosition, tileSource, GRAPH_FACTORY) {
			@Override
			public boolean onTap(LatLong tapLatLong, Point layerXY, Point tapXY) {
				bubbleLayer.dateForward();
				return true;
			}
		    
			
			
		};
	}

}
