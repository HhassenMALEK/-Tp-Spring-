package com.fr.diginamic.controller;


import java.util.List;
import org.springframework.ui.Model;
import com.fr.diginamic.dto.CityDto;
import com.fr.diginamic.mapper.CityMapper;
import com.fr.diginamic.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fr.diginamic.service.CityService;


@Controller
@RequestMapping("/api/view")
public class CityControllerView {
    @Autowired
    private CityService cityService;
    private CityMapper cityMapper = new CityMapper();
    @Autowired
    private DepartmentService departmentService;

    // Méthode pour afficher les villes dans une page HTML via Thymeleaf
    @GetMapping("/list")
    public String viewCities(Model model) {
        List<CityDto> cities = (List<CityDto>) cityMapper.toDtos(cityService.getAllCities());
        model.addAttribute("cities", cities);  // Ajouter la liste des villes au modèle Thymeleaf
        return "index";  // Nom de la page HTML dans src/main/resources/templates
    }
}