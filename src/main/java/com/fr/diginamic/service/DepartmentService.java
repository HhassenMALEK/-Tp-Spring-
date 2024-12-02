package com.fr.diginamic.service;

import java.util.List;
import java.util.Optional;

import com.fr.diginamic.exception.InvalidResourceException;
import com.fr.diginamic.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fr.diginamic.model.Department;
import com.fr.diginamic.repository.DepartmentRepository;


@Service
public class DepartmentService {
	@Autowired
	DepartmentRepository departmentRepository;

	public boolean create(Department department) throws InvalidResourceException {
		if (department != null) {
			throw new InvalidResourceException("Un département avec ce code existe déjà.!");
		}
		if(department.getCode().length()<2 || department.getCode().length()>3){
			throw new InvalidResourceException("Le nom du département doit contenir deux ou trois caractères");
		}
		if(department.getName().length()< 3){
			throw new InvalidResourceException("Le nom du département doit contenir au moins 3 caractères");
		}
		try {
			departmentRepository.save(department);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	public List<Department> getAllDepartments() throws ResourceNotFound {
		List<Department> departments = departmentRepository.findAll();
		if(departments.isEmpty()){
			throw new ResourceNotFound("Aucun département trouvé");
		}
		return departments;
	}
	public Department getDepartementById(Long id) throws ResourceNotFound, InvalidResourceException {
		if (id == null) {
			throw new InvalidResourceException("Il faut renseigner un id.");
		}

		Optional<Department> optDept = departmentRepository.findById(id);

		if (optDept.isPresent()) {
			return optDept.get();
		}
		else {
			throw new ResourceNotFound(String.format("Aucun département dont l'Id est %d n'a été trouvé.", id));
		}
	}

	public Department findByCode(String code) throws InvalidResourceException {
		if(code == null || code.isEmpty()) {
			throw new InvalidResourceException("Le code du département doit être renseigné.");
		}
		return departmentRepository.findByCode(code);
	}

	public boolean update(Department department) throws InvalidResourceException, ResourceNotFound {
		if(department.getId() ==null){
			throw new InvalidResourceException("Il faut renseigner un id.");
		}
		if(departmentRepository.findById(department.getId()).isEmpty()) {
			throw new ResourceNotFound("Le département n'existe pas.");
		}else {
			departmentRepository.save(department);
			return true;
		}
	}
	public boolean delete(Long id) throws InvalidResourceException , ResourceNotFound{
		if (id == null) {
			throw new InvalidResourceException("Il faut renseigner un id.");
		}

		if(departmentRepository.findById(id).isEmpty()) {
		throw new ResourceNotFound("Le département n'existe pas.");
		}else {
			departmentRepository.deleteById(id);
			return true;
		}
	}
	
}
