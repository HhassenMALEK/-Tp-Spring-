package com.fr.diginamic.repository;



import com.fr.diginamic.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CityRepository  extends CrudRepository<City, Long> {

    List<City> findAll();

     public City findByName(String name);

    // Recherche des villes dont le nom commence par un préfixe :
    List<City> findByNameStartingWith(String prefix);

    // Méthode pour obtenir les villes avec une population supérieure à un minimum
    List<City> findByNbInhabitantsGreaterThan(int min);


    // Recherche des villes dont la population est comprise entre min et max
    List<City> findByNbInhabitantsBetween(int min, int max);

    //Recherche des villes dans un département avec une population supérieure à un minimum donné
    List<City> findByDepartmentCodeAndNbInhabitantsGreaterThan(String department, int min);

    //Recherche des villes dans un département avec une population entre min et max
    List<City> findByDepartmentCodeAndNbInhabitantsBetweenOrderByNbInhabitantsDesc(String departmentCode, int min, int max);

    // Recherche des n villes les plus peuplées d'un département
    Page<City>  findByDepartmentCodeOrderByNbInhabitantsDesc(String department, Pageable pageable);
}
