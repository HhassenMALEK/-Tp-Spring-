package com.fr.diginamic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fr.diginamic.model.City;
import com.fr.diginamic.service.CityService;

@RestController
@RequestMapping("api/cities")
public class CityController {
	@Autowired
	private CityService cityService;

	@GetMapping("/all")
	public List<City> getAllCities() {
		return cityService.findAll();
	}

	@GetMapping("/{id}")
	public City getCity(@PathVariable("id") Long id) {
		return cityService.getCity(id).get();
	}

	@GetMapping("/name/{name}")
	public City getCityByName(@PathVariable("name") String name) {
		return cityService.getCityByName(name);
	}

	@GetMapping("/prefix/{prefix}")
	public List<City> getCityByPrefix(@PathVariable("prefix") String prefix) {
		return cityService.trouverParPrefixeNom(prefix);
	}

	@GetMapping("/population/{min}")
	public List<City> getCitiesByPopulationMin(@PathVariable("min") int min) {
		return cityService.trouverParPopulationMin(min);
	}

	@GetMapping("/population/{min}/{max}")
	public List<City> getCitiesByPopulationInterval(
			@PathVariable("min") int min,
			@PathVariable("max") int max) {
		return cityService.trouverParPopulationIntervalle(min, max);
	}

	@GetMapping("/department/{department}/{min}")
	public List<City> getCitiesByDepartmentAndPopulationMin(
			@PathVariable("department") String department,
			@PathVariable("min") int min) {
		return cityService.trouverParDepartementEtPopulationMin(department, min);
	}

	@GetMapping("/department/{department}/{min}/{max}")
	public List<City> getCitiesByDepartmentAndPopulationRange(
			@PathVariable("department") String department,
			@PathVariable("min") int min,
			@PathVariable("max")  int max) {
		return cityService.trouverParDepartementEtPopulationIntervalle(department, min, max);
	}

	@GetMapping("/department/{department}/top/{n}")
	public Page<City> getTopCitiesByDepartment(
			@PathVariable("department") String department,
			@PathVariable("n") int n) {
		return cityService.trouverNVillesParDepartement(department, n);
	}

	@PostMapping
	public ResponseEntity<String> createTown(@RequestBody City city) {
		if (cityService.addCity(city)) {
			return new ResponseEntity<String>("Succès !",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Impossible de créer la ville, elle existe déjà ou il manque le département : "+city.toString(),HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	public ResponseEntity<String> updateTown(@RequestBody City city) {
		if (cityService.updateCity(city)) {
			return new ResponseEntity<String>("Succès !",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("La mise à jour a échouée !",HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTown(@PathVariable Long id) {
		if (cityService.deleteCity(id)) {
			return new ResponseEntity<String>("Succès !",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("La supression a échouée !",HttpStatus.BAD_REQUEST);
		}
	}

}
