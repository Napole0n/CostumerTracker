package br.mg.customertracker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@SuppressWarnings("serial")
@Entity(name = "customers")
public class Customer implements Serializable{
	
	@Id
	private long cnpj;
	@OneToOne(fetch = FetchType.EAGER)
	private Adress adress;
	private String name;
	
	public Customer() {}
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
