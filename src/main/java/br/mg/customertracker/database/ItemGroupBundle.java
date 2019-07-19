package br.mg.customertracker.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.mg.customertracker.model.ItemGroup;

public class ItemGroupBundle {

	private Map<Integer, ItemGroup> groupMap;

	public ItemGroupBundle(List<ItemGroup> groups) {
		groupMap = new HashMap<Integer, ItemGroup>();
		groups.forEach(g -> {
			groupMap.put(g.getGroupId(), g);
		});
	}

	public ItemGroup get(int id) {
		return groupMap.get(id);
	}

	public ItemGroup[] getAll(int... ids) {
		int count = 0;
		for (int i : ids) {
			if (i != 0) {
				count++;
			}
		}
		ItemGroup[] groups = new ItemGroup[count];
		int k = 0;
		for (int i : ids) {
			if (i != 0) {
				groups[k] = groupMap.get(i);
				k++;
			}
		}
		return groups;
	}

}
