package com.fr.diginamic.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.diginamic.model.City;


public interface CityRepository extends JpaRepository<City, Long> {
	// Recherche une ville par son nom exact
	public City findByName(String name);

	// Recherche une liste de villes dont le nom commence par une chaîne spécifique
	public List<City> findByNameStartingWith(String name);

	// Recherche une liste de villes ayant un nombre d'habitants supérieur à un nombre spécifié
	public List<City> findByNbInhabitantsGreaterThan(Integer nb);

	// Recherche une liste de villes dont le nombre d'habitants est compris entre deux valeurs
	public List<City> findByNbInhabitantsBetween(Integer nbMin, Integer nbMax);

	// Recherche une liste de villes dans un département donné et ayant un nombre d'habitants supérieur à une valeur spécifiée
	public List<City> findByDepartmentCodeAndNbInhabitantsGreaterThan(String departmentCode, Integer nb);

	// Recherche une liste de villes dans un département donné et dont le nombre d'habitants est compris entre deux valeurs
	public List<City> findByDepartmentCodeAndNbInhabitantsBetween(String departmentCode, Integer nbMin, Integer nbMax);

	// Recherche une page de villes dans un département donné, triées par nombre d'habitants de manière décroissante
	public Page<City> findByDepartmentCodeOrderByNbInhabitantsDesc(String departmentCode, Pageable pageable);

}
