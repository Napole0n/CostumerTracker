package br.mg.customertracker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity(name = "sales")
public class Sale implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Company company;
	private Customer customer;
	private Date saleDate;
	private int NfNumber;
	private int nfSerie;
	private double value;
	private SaleItem[] items;
	private double totalWeight;

	public Sale() {}
	public Sale(Company company, Customer customer, Date saleDate, int nfNumber, int nfSerie, double value,
			SaleItem[] items) {
		super();
		this.company = company;
		this.customer = customer;
		this.saleDate = saleDate;
		NfNumber = nfNumber;
		this.nfSerie = nfSerie;
		this.value = value;
		this.items = items;
		calculateWeight();
	}

	private void calculateWeight() {
		this.totalWeight = 0;
		for (SaleItem i : items) {
			totalWeight += i.getWeight();
		}
	}

	public long getId() {
		return id;
	}

	public Company getCompany() {
		return company;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public int getNfNumber() {
		return NfNumber;
	}

	public int getNfSerie() {
		return nfSerie;
	}

	public double getValue() {
		return value;
	}

	public SaleItem[] getItems() {
		return items;
	}

	public double getTotalWeight() {
		return totalWeight;
	}

}
