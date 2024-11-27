package com.fr.diginamic.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fr.diginamic.model.City;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class CityDao {
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public void create(City town) {
		em.persist(town);
	}
	
	public List<City> findAll(){
		TypedQuery<City> query = em.createQuery("SELECT T FROM City T", City.class);
		return query.getResultList();
	}
	
	public City find(Long id) {
		return em.find(City.class, id);
	}
	
	public City findByName(String name){
		TypedQuery<City> query = em.createQuery("SELECT T FROM City T where T.name=\""+name+"\"", City.class);
		return query.getSingleResult();
	}

	@Transactional
	public City update(City town) {
		return em.merge(town);
	}
	
	@Transactional
	public void delete(City town) {
		em.remove(town);
	}
	@Transactional
	public void deleteById(Long id) {
		City t = em.find(City.class, id);
		em.remove(t);
	}

	public List<City> findByDepartmentCodeOrderByNbInhabitantsDesc(String codeDep, Integer n) {
		TypedQuery<City> query = em.createQuery("SELECT T FROM City T where T.department.code=\""+codeDep+"\" order by nbInhabitants desc LIMIT "+n, City.class);
		return query.getResultList();
	}

	public List<City> findByDepartmentCodeAndNbInhabitantsBetween(String codeDep, Integer min, Integer max) {
		TypedQuery<City> query = em.createQuery("SELECT T FROM City T where T.department.code=\""+codeDep+"\" and T.nbInhabitants > "+min+" and T.nbInhabitants < "+max, City.class);
		return query.getResultList();
	}
	

}

