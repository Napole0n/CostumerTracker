package br.mg.customertracker.model;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import br.mg.customertracker.exception.InvalidSizeException;

public class BubbleTimeLine {

	private TreeMap<Date, List<Bubble>> data;

	public BubbleTimeLine(List<Date> dates, List<List<Bubble>> bubbleCollection) throws InvalidSizeException {

		if (dates.size() != bubbleCollection.size()) {
			throw new InvalidSizeException("vetor de datas e bolhas de tamanhos diferentes!");
		}
		data = new TreeMap<>();
		int counter = 0;
		for (Date d : dates) {
			data.put(d, bubbleCollection.get(counter));
			counter++;
		}
	}

	public TreeMap<Date, List<Bubble>> getMap() {
		return data;
	}

	public Date getDateAfterDate(Date date) {
		if (data.higherKey(date) == null) {

			return date;
		}
		return data.higherKey(date);
	}

	public Date getDateBeforeDate(Date date) {
		if (data.lowerKey(date) == null) {

			return date;

		}
		return data.lowerKey(date);
	}

	public int getMapSize() {
		return data.size();
	}

	public List<Bubble> getBubblesForDate(Date date) {
		return data.get(date);
	}

	public void addData(Date date, List<Bubble> bubbles) {
		data.put(date, bubbles);
	}

}
