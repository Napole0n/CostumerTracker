package br.mg.customertracker.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.mg.customertracker.model.Customer;

public class CustomerBundle {
	private Map<Long, Customer> groupMap;

	public CustomerBundle(List<Customer> groups) {
		groupMap = new HashMap<Long, Customer>();
		groups.forEach(g -> {
			groupMap.put(g.getCnpj(), g);
		});
	}

	public Customer get(long id) {
		return groupMap.get(id);
	}

	public Set<Long> getAllKeys() {
		return groupMap.keySet();
	}

	public Customer[] getAll(long... ids) {
		int count = 0;
		for (long i : ids) {
			if (i != 0) {
				count++;
			}
		}

		Customer[] groups = new Customer[count];
		int k = 0;
		for (long i : ids) {
			if (i != 0) {
				groups[k] = groupMap.get(i);
				k++;
			}
		}
		return groups;
	}
}
