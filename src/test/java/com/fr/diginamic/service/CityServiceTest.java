package com.fr.diginamic.service;


import com.fr.diginamic.exception.InvalidResourceException;
import com.fr.diginamic.exception.ResourceNotFound;
import com.fr.diginamic.model.City;
import com.fr.diginamic.model.Department;
import com.fr.diginamic.repository.CityRepository;
import com.fr.diginamic.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CityServiceTest {
    @Autowired
    private CityService cityService;
    @MockitoBean
    private CityRepository cityRepository;
    @MockitoBean
    private DepartmentRepository departmentRepository;

    private final List<Department> departments = List.of(
            new Department(1L, "Nord", "59"),
            new Department(2L, "Pas-de-Calais", "62")
    );

    private final List<City> cities = List.of(
            new City(10001L, "Lille", 232440, departments.get(0)),
            new City(10002L, "Roubaix", 98239, departments.get(0)),
            new City(10003L, "Calais", 74530, departments.get(1))
    );
    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void testGetAllCitiesOk() throws ResourceNotFound {
        // ARRANGE
        Mockito.when(cityRepository.findAll()).thenReturn(cities);
        // ACT
        List<City> actualCities = cityService.getAllCities();
        // ASSERT
        assertEquals(cities, actualCities);
    }

    @Test
    public void testGetCitiesRessourceNotFound() {
        // Renvoie une liste vide
        Mockito.when(cityRepository.findAll()).thenReturn(List.of());
        assertThrows(ResourceNotFound.class, () -> cityService.getAllCities());
    }



    @Test
    public void testGetCityIdOk() throws ResourceNotFound, InvalidResourceException {
        Long cityId = 13721L;
        Mockito.when(cityRepository.findById(cityId)).thenReturn(Optional.of(cities.getFirst()));
        Optional<City> actualVilleOptional = cityService.getCityId(cityId);
        assertTrue(actualVilleOptional.isPresent());
        assertEquals(cities.getFirst(), actualVilleOptional.get());

    }

//
//    @Test
//    public void testGetCityIdRessourceNotFound() {
//        Long cityId = 13721L;
//        Mockito.when(cityRepository.findById(cityId)).thenReturn(Optional.empty());
//        assertThrows(ResourceNotFound.class, () -> cityService.getCityId(cityId));
//    }

}