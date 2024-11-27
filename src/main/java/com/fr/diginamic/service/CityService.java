package com.fr.diginamic.service;

import java.util.List;

import com.fr.diginamic.dao.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.diginamic.model.City;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.NoResultException;


@Service
public class CityService {
	@Autowired
	CityDao cityDao;
	@Autowired
	DepartmentService departmentService;

	@PostConstruct
	public void init() {
			cityDao.create(new City("Paris",10000000,departmentService.findByCode("75")));
			cityDao.create(new City("Marseille", 500000,departmentService.findByCode("13")));
			cityDao.create(new City("Lyon", 300000,departmentService.findByCode("69")));
			cityDao.create(new City("Lille", 200000,departmentService.findByCode("59")));
			cityDao.create(new City("Bordeaux", 250000,departmentService.findByCode("33")));
			cityDao.create(new City("Toulouse", 400000,departmentService.findByCode("31")));
			cityDao.create(new City("Nice", 300000,departmentService.findByCode("06")));
			cityDao.create(new City("Strasbourg", 300000,departmentService.findByCode("67")));
			cityDao.create(new City("Nantes", 300000,departmentService.findByCode("44")));
	}

	public List<City> getAllTowns(){
		return cityDao.findAll();
	}
	
	public City getTown(Long id) {
		City result = null;
		try {
			result = cityDao.find(id);
		}catch(NoResultException nre) {
		}
		return result;
	}
	
	public City getTownByName(String name) {
		City result = null;
		try {
			result = cityDao.findByName(name);
		}catch(NoResultException nre) {
		}
		return result;
	}
	
	public boolean addTown(City town) {
		try {
			City result = cityDao.findByName(town.getName());
			return false;
		}catch(NoResultException nre) {
			cityDao.create(town);
			return true;
		}		
	}
	public boolean updateTown(City town) {
		try {
			City result = cityDao.find(town.getId());
			result.setName(town.getName());
			result.setNbInhabitants(town.getNbInhabitants());
			cityDao.update(result);
			return true;
		}catch(NoResultException nre) {
			return false;
		}
	}
	public boolean deleteTown(Long id) {
		try {
			City result = cityDao.find(id);
			cityDao.deleteById(id);
			return true;
		}catch (NoResultException nre) {
			return false;			
		}
	}

	public List<City> findByDepartmentCodeOrderByNbInhabitantsDesc(String codeDep, Integer n) {
		return cityDao.findByDepartmentCodeOrderByNbInhabitantsDesc(codeDep,n);
	}

	public List<City> findByDepartmentCodeAndNbInhabitantsBetween(String codeDep, Integer min, Integer max) {
		return cityDao.findByDepartmentCodeAndNbInhabitantsBetween(codeDep,min,max);
	}

}
