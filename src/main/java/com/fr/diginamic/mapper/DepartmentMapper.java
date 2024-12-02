package com.fr.diginamic.mapper;

import com.fr.diginamic.dto.DepartmentDto;
import com.fr.diginamic.model.Department;
import com.fr.diginamic.model.City;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public DepartmentDto toDto(Department department) {
        DepartmentDto dto = new DepartmentDto();
        dto.setDepartmentCode(department.getCode());
        dto.setDepartmentName(department.getName());
        dto.setInhabitantsNb(department.getCities().stream().mapToLong(City::getNbInhabitants).sum());
        return dto;
    }

    // Impossible en l'état il manque l'ID du département
    public Department fromDto(DepartmentDto dto) {
        Department department = new Department();
        department.setCode(dto.getDepartmentCode());
        department.setName(dto.getDepartmentName());
        return department;
    }
}
