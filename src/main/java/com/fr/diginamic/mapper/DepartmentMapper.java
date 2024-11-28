package com.fr.diginamic.mapper;

import com.fr.diginamic.dto.DepartmentDto;
import com.fr.diginamic.model.City;

public class DepartmentMapper {
    public DepartmentDto toDto(City city) {
        DepartmentDto dto = new DepartmentDto();
        dto.setDepartmentCode(city.getDepartment().getCode());
        dto.setDepartmentName(city.getDepartment().getName());
        return dto;
    }
}
