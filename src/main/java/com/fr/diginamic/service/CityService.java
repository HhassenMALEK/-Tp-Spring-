package com.fr.diginamic.service;

import java.util.List;
import java.util.Optional;

import com.fr.diginamic.exception.InvalidResourceException;
import com.fr.diginamic.exception.ResourceNotFound;
import com.fr.diginamic.model.City;
import com.fr.diginamic.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fr.diginamic.repository.CityRepository;


@Service
public class CityService {
	@Autowired
	CityRepository cityRepository;
	@Autowired
	DepartmentService departmentService;

	public Iterable<City> getAllCities() throws ResourceNotFound {
		List<City> cities = cityRepository.findAll();
		if(cities.isEmpty()){
			throw new ResourceNotFound("Aucune ville trouvée");
		}
		return cities;
	}

	public Optional<City> getCityId(Long id) throws ResourceNotFound {
		if(id == null){
			throw new ResourceNotFound("Il faut indiquer un id de ville");
		}
		return cityRepository.findById(id);
	}

	public City getCityByName(String name) throws ResourceNotFound {
		if (name == null|| name.isEmpty()) {
			throw new ResourceNotFound("il faut indiquer un nom de ville");
		}
		return cityRepository.findByName(name);
	}



	public boolean addCity(City city) throws InvalidResourceException {
		if(city.getNbInhabitants()<10){
			throw new InvalidResourceException("La ville doit avoir au moins 10 habitants");
		}
		if(city.getName().length()<2){
			throw new InvalidResourceException("Le nom de la ville doit contenir au moins 2 caractères");
		}
		City result = cityRepository.findByName(city.getName());
		if (result!=null) {
		throw new InvalidResourceException("La ville existe déjà !");
		}else {
			cityRepository.save(city);
			return true;
		}
	}

	public boolean updateCity(City city) throws InvalidResourceException {
		Optional<City> result = cityRepository.findById(city.getId());
		if(result.isEmpty()) {
			throw new InvalidResourceException("La ville n'existe pas");
		}
		City cityToUpdate = result.get();
		cityToUpdate.setName(city.getName());
		cityToUpdate.setNbInhabitants(city.getNbInhabitants());

	 // Si le département change, ajoute le département à la ville
		if (!cityToUpdate.getDepartment().getCode().equals(city.getDepartment().getCode())) {
			Department department = departmentService.findByCode(city.getDepartment().getCode());
			cityToUpdate.setDepartment(department);
		}
		cityRepository.save(cityToUpdate);
		return true;
	}
	public boolean deleteCity(Long id) throws InvalidResourceException, ResourceNotFound {
		if(id == null){
			throw new InvalidResourceException("Il faut indiquer un id de ville");
		}
		Optional<City> result = cityRepository.findById(id);
		if (result.isEmpty()) {
			 throw new ResourceNotFound("La ville n'existe pas");
		}
		cityRepository.deleteById(id);
		return true;
	}

	public Iterable<City> getCityByNameStart(String nameStart) {
		List<City> cities = cityRepository.findByNameStartingWith(nameStart);
		if (cities.size() == 0) {
			throw new ResourceNotFound("acune ville ne commence par " + nameStart);
		}
		return cities;
	}

	public Iterable<City> findByNbInhabitantsGreaterThan(Integer min) {
		List<City> cities =  cityRepository.findByNbInhabitantsGreaterThan(min);
		if (cities.size() == 0) {
			throw new ResourceNotFound("Aucune ville trouvée avec plus de " + min + " habitants");
		}
		return cities;
	}

	public Iterable<City> findByNbInhabitantsBetween(Integer min, Integer max) throws ResourceNotFound {
		List<City> cities = cityRepository.findByNbInhabitantsBetween(min, max);
		if(cities.isEmpty()){
			throw new ResourceNotFound(String.format("Aucune ville trouvée avec un nombre d'habitants compris entre %d et %d",min,max));
		}
		return cities;
	}

	public Iterable<City> findByDepartmentCodeAndNbInhabitantsGreaterThan(String departmentCode, Integer min) throws ResourceNotFound {
		List<City> cities = cityRepository.findByDepartmentCodeAndNbInhabitantsGreaterThan(departmentCode,min);
		if(cities.isEmpty()){
			throw new ResourceNotFound(String.format("Aucune ville trouvée pour le département %s avec plus de %d habitants",departmentCode,min));
		}
		return cities;
	}

	public Iterable<City> findByDepartmentCodeAndNbInhabitantsBetween(String departmentCode, Integer min, Integer max) throws ResourceNotFound {
		List<City> cities = cityRepository.findByDepartmentCodeAndNbInhabitantsBetween(departmentCode,min, max);
		if(cities.isEmpty()){
			throw new ResourceNotFound(String.format("Aucune ville trouvée pour le département %s avec un nombre d'habitants compris entre %d et %d",departmentCode,min,max));
		}
		return cities;
	}

	public Iterable<City> findByDepartmentCodeOrderByNbInhabitantsDesc(String departmentCode, Integer size) throws ResourceNotFound {
		List<City> cities = cityRepository.findByDepartmentCodeOrderByNbInhabitantsDesc(departmentCode,Pageable.ofSize(size)).getContent();
		if(size<=0){
			throw new ResourceNotFound("le nombr de departements doit être supérieur à 0");
		}
		return cities;
	}

}
