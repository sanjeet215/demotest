package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.configuration.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "states")
public class State extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = -508902670922690882L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "country_id", nullable = false)
	//@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Country country;

	public State() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", name=" + name + ", country=" + country + "]";
	}

}
