package com.fr.diginamic.mapper;

import com.fr.diginamic.dto.CityDto;
import com.fr.diginamic.model.City;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class CityMapper {
    public CityDto toDto(City city) {
        CityDto dto = new CityDto();
        dto.setCityCode(city.getId().toString()); // Conversion Long ou Integer en String
        dto.setNbrInhabitants(city.getNbInhabitants());
        dto.setDepartmentCode(city.getDepartment().getCode());
        dto.setDepartmentName(city.getDepartment().getName());
        return dto;

    }
}
