package com.fr.diginamic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.diginamic.model.City;
import com.fr.diginamic.service.CityService;

@RestController
@RequestMapping("/city")
public class CityController {
	@Autowired
	private CityService cityService;
	

	@GetMapping
	public List<City> getTowns(){
		return cityService.getAllTowns();
	}
	@GetMapping("/{id}")
	public City getTown(@PathVariable("id") Long id) {
		return cityService.getTown(id);
	}
	@GetMapping("/name/{name}")
	public City getTownByName(@PathVariable("name") String name) {
		return cityService.getTownByName(name);
	}

	@PostMapping
	public ResponseEntity<String> createTown(@RequestBody City town) {
		if (cityService.addTown(town)) {
			return new ResponseEntity<String>("Succès !",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("La ville existe déjà !",HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping
	public ResponseEntity<String> updateTown(@RequestBody City town) {
		if (cityService.updateTown(town)) {
			return new ResponseEntity<String>("Succès !",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("La mise à jour a échouée !",HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTown(@PathVariable Long id) {
		if (cityService.deleteTown(id)) {
			return new ResponseEntity<String>("Succès !",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("La supression a échouée !",HttpStatus.BAD_REQUEST);
		}
	}
	/**
	 * Les n plus grandes villes d'un dep
	 */
	@GetMapping("/findByDepartmentCodeOrderByNbInhabitantsDesc/{codeDep}/{n}")
	public List<City> findByDepartmentCodeOrderByNbInhabitantsDesc(@PathVariable("codeDep")String codeDep, @PathVariable("n") Integer n) {
		return cityService.findByDepartmentCodeOrderByNbInhabitantsDesc(codeDep,n);
	}
	/**
	 * Les villes ayant un nb d'habitants entre min et max pour un dep	 * 
	 */
	@GetMapping("/findByDepartmentCodeAndNbInhabitantsBetween/{codeDep}/{min}/{max}")
	public List<City> findByDepartmentCodeAndNbInhabitantsBetween(@PathVariable("codeDep")String codeDep, @PathVariable("min") Integer min, @PathVariable("max") Integer max) {
		return cityService.findByDepartmentCodeAndNbInhabitantsBetween(codeDep, min,max);
	}

}
