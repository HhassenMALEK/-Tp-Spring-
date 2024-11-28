package com.fr.diginamic.controller;


import java.util.List;

import com.fr.diginamic.model.Department;
import com.fr.diginamic.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Department department){
        if(departmentService.create(department)) {
            return new ResponseEntity<String>("Succès !", HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("Impossible de créer le département envoyé : "+department.toString(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/all")
    public List<Department> findAll(){
        return departmentService.findAll();
    }

    @GetMapping("/{code}")
    public Department findByCode(@PathVariable String code){
        return departmentService.findByCode(code);
    }
    @PutMapping
    public ResponseEntity<String> update(@RequestBody Department department){
        if (!departmentService.update(department)) {
            return new ResponseEntity<String>("Impossible de mettre à jour le département envoyé, il n'est pas trouvé ou n'a pas d'id : "+department.toString(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Mise à jour réussie !",HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        if(departmentService.delete(id)) {
            return new ResponseEntity<String>("Suppression réussie !",HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("Impossible de supprimer le département avec l'id envoyé : "+id,HttpStatus.BAD_REQUEST);
        }
    }
}