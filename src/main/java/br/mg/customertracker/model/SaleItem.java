package br.mg.customertracker.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity(name = "sale_items")
public class SaleItem implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Item item;
	private double weight;
	private double value;

	public SaleItem() {}
	public SaleItem(Item item, double weight, double value) {
		super();
		this.item = item;
		this.weight = weight;
		this.value = value;
	}

	public Item getItem() {
		return item;
	}

	public double getWeight() {
		return weight;
	}

	public double getValue() {
		return value;
	}

}
