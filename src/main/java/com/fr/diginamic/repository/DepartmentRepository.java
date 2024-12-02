package com.fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.diginamic.model.Department;


public interface DepartmentRepository extends JpaRepository<Department, Long> {
	public Department findByCode(String code);
	public void deleteByCode(String code);
}
