package br.mg.customertracker.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity(name = "companies")
public class Company implements Serializable {

	@Id
	private int id;
	
	private String name;

	public Company() {}
	
	public Company(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
