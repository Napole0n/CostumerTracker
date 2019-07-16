package br.mg.customertracker.map;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.mapsforge.core.model.LatLong;

import br.mg.customertracker.exception.InvalidSizeException;

public class TestDataGenerator {

	@SuppressWarnings("deprecation")
	public static BubbleTimeLine generate() throws InvalidSizeException {

		LatLong saoPaulo = new LatLong(-23.5489, -46.6388);
		LatLong floripa = new LatLong(-27.5969, -48.5495);
		LatLong recife = new LatLong(-8.05428, -34.8813);

		Color color = new Color(0, 255, 0, 100);


		List<List<Bubble>> bubbles = new ArrayList<>();

		Date dateA = new Date(2019, 06, 01);
		Date dateB = new Date(2018, 05, 01);
		Date dateC = new Date(2017, 04, 01);

		List<Bubble> bubbleA = new ArrayList<>();
		List<Bubble> bubbleB = new ArrayList<>();
		List<Bubble> bubbleC = new ArrayList<>();

		Bubble a1 = new Bubble(saoPaulo, color, 20, "test");
		Bubble a2 = new Bubble(floripa, color, 30, "test");
		Bubble a3 = new Bubble(recife, color, 60, "test");

		bubbleA.addAll(Arrays.asList(new Bubble[] { a1, a2, a3 }));

		Bubble b1 = new Bubble(saoPaulo, color, 80, "test");
		Bubble b2 = new Bubble(floripa, color, 10, "test");
		Bubble b3 = new Bubble(recife, color, 25, "test");

		bubbleB.addAll(Arrays.asList(new Bubble[] { b1, b2, b3 }));

		Bubble c1 = new Bubble(saoPaulo, color, 10, "test");
		Bubble c2 = new Bubble(floripa, color, 80, "test");
		Bubble c3 = new Bubble(recife, color, 50, "test");

		bubbleC.addAll(Arrays.asList(new Bubble[] { c1, c2, c3 }));

		bubbles.add(bubbleA);
		bubbles.add(bubbleB);
		bubbles.add(bubbleC);

		return new BubbleTimeLine(Arrays.asList(new Date[] { dateA, dateB, dateC }), bubbles);
	}

}
