package com.dish.model;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = -2639944658538569230L;

	private final String street, city, country;

	public Address(String street, String city, String country) {

		this.street = street;
		this.city = city;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}
}
