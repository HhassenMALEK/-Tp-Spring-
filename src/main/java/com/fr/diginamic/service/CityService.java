package com.fr.diginamic.service;

import java.util.List;
import java.util.Optional;

import com.fr.diginamic.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.fr.diginamic.model.City;

@Service
public class CityService {

	@Autowired
	CityRepository cityRepository;

	public List<City> findAll(){
		return cityRepository.findAll();
	}
	public Optional<City> getCity(Long id) {
		return cityRepository.findById(id);
	}

	public City getCityByName(String name) {
		return cityRepository.findByName(name);
	}

	// Recherche des villes dont le nom commence par un préfi
	public List<City> trouverParPrefixeNom(String prefix){
		return cityRepository.findByNameStartingWith(prefix);
	}
	// Méthode pour obtenir les villes avec une population supérieure à un minimum
	public List<City> trouverParPopulationMin(int min) {
		return cityRepository.findByNbInhabitantsGreaterThan(min);
	}
	// Méthode pour obtenir les villes avec une population comprise entre min et max
	public List<City> trouverParPopulationIntervalle(int min, int max){
		return cityRepository.findByNbInhabitantsBetween(min, max);
	}
	// Recherche des villes dans un département avec une population spéruieure à un min
	public List<City> trouverParDepartementEtPopulationMin(String department, int min){
		return cityRepository.findByDepartmentCodeAndNbInhabitantsGreaterThan(department, min);
	}
	// Recherche des villes dans un département avec une population comprise entre min et max
	public List<City> trouverParDepartementEtPopulationIntervalle(String department, int min, int max){
		return cityRepository.findByDepartmentCodeAndNbInhabitantsBetweenOrderByNbInhabitantsDesc(department, min, max);
	}
	// Recherche des n villes les plus peuplées d'un département
	public Page<City> trouverNVillesParDepartement(String department, int n) {
		Pageable pageable = PageRequest.of(0,n );
		return cityRepository.findByDepartmentCodeOrderByNbInhabitantsDesc(department, pageable);
	}

	public boolean addCity(City city){
		Optional<City> result = cityRepository.findById(city.getId());
		if (result.isEmpty()) {
		return false;
		}
		cityRepository.save(city);
		return true;
	}

	public boolean updateCity(City city){
		Optional<City> result = cityRepository.findById(city.getId());
		if (result.isEmpty()) {
		return false;
		}
		cityRepository.save(city);
		return true;
	}

	public boolean deleteCity(Long id){
		Optional<City> result = cityRepository.findById(id);
		if (result.isEmpty()) {
		return false;
		}
		cityRepository.deleteById(id);
		return true;
	}
}




