package com.fr.diginamic.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String code;
	@OneToMany(mappedBy = "department")
	@JsonIgnore
	List<City> cities = new ArrayList<>();
	
	public Department() {}
	
	public Department(String code, String name) {
		this.code=code;
		this.name=name;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setTowns(List<City> cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Department{");
		sb.append("id=").append(id);
		sb.append(", name='").append(name).append('\'');
		sb.append(", code='").append(code).append('\'');
		sb.append(", cities=").append(cities);
		sb.append('}');
		return sb.toString();
	}
}
