package br.mg.customertracker.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity(name = "adresses")
public class Adress implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String number;
	private String street;
	private String city;
	private String state;
	private String country;
	private double latitude;
	private double longitude;

	public Adress() {
	}

	public Adress(String number, String street, String city, String state, String country) {
		this.setNumber(number);
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.latitude = 0.0;
		this.longitude = 0.0;

	}

	public Adress(String number, String street, String city, String state, String country, double latitude,
			double longitude) {

		this.setNumber(id);
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
