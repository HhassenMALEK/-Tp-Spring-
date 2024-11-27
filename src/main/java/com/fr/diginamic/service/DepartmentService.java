package com.fr.diginamic.service;

import java.util.List;

import com.fr.diginamic.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.diginamic.dao.DepartmentDao;
import com.fr.diginamic.model.Department;

import jakarta.annotation.PostConstruct;


@Service
public class DepartmentService {
	@Autowired
	DepartmentDao departmentDAO;
	
	@PostConstruct
	public void init() {	
		create(new Department("75", "Paris"));
		create(new Department("13", "Marseille"));
		create(new Department("69","Lyon"));
		create(new Department("59","Lile"));
		create(new Department("33","Bordeau"));
		create(new Department("31","Toulouse"));
		create(new Department("06","Nice"));
		create(new Department("67","Strasbourg"));
		create(new Department("44","Nantes"));
	}

	public boolean create(Department department) {
		try {
			departmentDAO.create(department);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	public List<Department> findAll() {
		 return departmentDAO.findAll();
	}

	public Department findByCode(String code) {
		return departmentDAO.findByCode(code);
	}

	public Department findById(Long id) {
		return departmentDAO.findById(id);
	}
	
	public boolean update(Department department) {
		if(department.getId()==null||departmentDAO.findById(department.getId())==null) {
			return false;
		}else {
			departmentDAO.update(department);
			return true;
		}
	}
	public boolean delete(Long id) {
		if(id==null||departmentDAO.findById(id)==null) {
			return false;
		}else {
			departmentDAO.deleteById(id);
			return true;
		}
	}
	
}
