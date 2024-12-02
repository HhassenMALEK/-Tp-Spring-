package com.fr.diginamic.controller;

import java.util.List;

import com.fr.diginamic.dto.DepartmentDto;
import com.fr.diginamic.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.diginamic.model.Department;
import com.fr.diginamic.service.DepartmentService;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;

	private DepartmentMapper departmentMapper = new DepartmentMapper();


	/**
	 * Récupère la liste de tous les départements.
	 * @return une liste de {@link DepartmentDto} représentant tous les départements
	 */
	@GetMapping("/list")
	public List<DepartmentDto> findAll(){
		return departmentService.getAllDepartments().stream()
				.map(d->departmentMapper
				.toDto(d)).toList();
	}

	/**
	 * Récupère un département par son code.
	 * @param code
	 * @return
	 */
	@GetMapping("/{code}")
	public Department findByCode(@PathVariable String code){
		return departmentService.findByCode(code);
	}

	/**
	 * Crée un département
	 * @param department
	 * @return
	 */
	@PostMapping
	public ResponseEntity<String> create(@RequestBody Department department){
		if(departmentService.create(department)) {
			return new ResponseEntity<String>("Succès !",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Impossible de créer le département envoyé : "+department.toString(),HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Met à jour un département
	 * @param dtoDepartment
	 * @return
	 */
	@PutMapping
	public ResponseEntity<String> update(@RequestBody DepartmentDto dtoDepartment){

		Department department = departmentService.findByCode(dtoDepartment.getDepartmentCode());

		if (!departmentService.update(department)) {
			return new ResponseEntity<String>("Impossible de mettre à jour le département envoyé, il n'est pas trouvé ou n'a pas d'id : "+department.toString(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Mise à jour réussie !",HttpStatus.OK);
	}

	/**
	 * Supprime un département par son id
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id){
		if(departmentService.delete(id)) {
			return new ResponseEntity<String>("Suppression réussie !",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Impossible de supprimer le département avec l'id envoyé : "+id,HttpStatus.BAD_REQUEST);
		}
	}
}
