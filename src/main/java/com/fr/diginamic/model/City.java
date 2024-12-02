package com.fr.diginamic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer nbInhabitants;
	@ManyToOne
	Department department;
	
	public City() {}
	public City(String name, Integer nbInhabitants) {
		super();
		this.name = name;
		this.nbInhabitants = nbInhabitants;
	}
	public City(String name, Integer nbInhabitants, Department department) {
		super();
		this.name = name;
		this.nbInhabitants = nbInhabitants;
		this.department=department;
	}

	/**
	 * Getter
	 *
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter
	 *
	 * @param id id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter
	 *
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter
	 *
	 * @return nbInhabitants
	 */
	public Integer getNbInhabitants() {
		return nbInhabitants;
	}

	/**
	 * Setter
	 *
	 * @param nbInhabitants nbInhabitants
	 */
	public void setNbInhabitants(Integer nbInhabitants) {
		this.nbInhabitants = nbInhabitants;
	}

	/**
	 * Getter
	 *
	 * @return department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Setter
	 *
	 * @param department department
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("City{");
		sb.append("id=").append(id);
		sb.append(", name='").append(name).append('\'');
		sb.append(", nbInhabitants=").append(nbInhabitants);
		sb.append(", department=").append(department);
		sb.append('}');
		return sb.toString();
	}
}
