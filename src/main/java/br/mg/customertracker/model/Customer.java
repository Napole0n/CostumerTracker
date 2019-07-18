package br.mg.customertracker.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity(name = "customers")
public class Customer {
	
	@Id
	private long cnpj;
	@OneToOne(cascade = CascadeType.ALL)
	private Adress adress;
	private String name;
	
	public Customer(long cnpj, Adress adress, String name) {
		super();
		this.cnpj = cnpj;
		this.adress = adress;
		this.name = name;
	}
	public long getCnpj() {
		return cnpj;
	}
	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}
	public Adress getAdress() {
		return adress;
	}
	public void setAdress(Adress adress) {
		this.adress = adress;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
