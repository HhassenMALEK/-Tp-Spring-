package com.fr.diginamic.repository;

import com.fr.diginamic.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Department findByCode(String code);
    public void deleteByCode(String code);
}

