package com.fr.diginamic.mapper;

import com.fr.diginamic.dto.CityDto;
import com.fr.diginamic.model.Department;
import com.fr.diginamic.model.City;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CityMapper {
    public CityDto toDto(City city) {
        CityDto dto = new CityDto();

        dto.setId(city.getId());
        dto.setName(city.getName());
        dto.setDepartmentCode(city.getDepartment().getCode());
        dto.setDepartmentName(city.getDepartment().getName());
        dto.setInhabitantsNb(city.getNbInhabitants());
        return dto;
    }

    public City fromDto(CityDto dto) {
        City city = new City();
        Department department = new Department();
        city.setId(dto.getId());
        city.setName(dto.getName());
        city.setNbInhabitants(dto.getInhabitantsNb());
        department.setCode(dto.getDepartmentCode());
        city.setDepartment(department);
        return city;
    }

    public Iterable<CityDto> toDtos(Iterable<City> towns) {
       ArrayList<CityDto> dtos = new ArrayList<>();
       for (City city : towns) {
           dtos.add(toDto(city));
       }
       return dtos;
    }
    }
