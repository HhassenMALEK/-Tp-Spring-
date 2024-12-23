package com.fr.diginamic.restController;

import java.io.IOException;
import java.util.List;
import org.springframework.ui.Model;

import com.fr.diginamic.dto.CityDto;
import com.fr.diginamic.mapper.CityMapper;
import com.fr.diginamic.model.Department;
import com.fr.diginamic.service.DepartmentService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.fr.diginamic.service.CityService;


@RestController
@RequestMapping("/api/cities")
public class CityController {
	@Autowired
	private CityService cityService;
	private CityMapper cityMapper = new CityMapper();
    @Autowired
    private DepartmentService departmentService;

	/**
	 * Récupère la liste de toutes les villes.
	 * @return une liste de {@link CityDto} représentant toutes les villes
	 */
	@GetMapping("/list")
	public Iterable<CityDto> getCities(){
		return cityMapper.toDtos(cityService.getAllCities());
	}

	/**
	 * Récupère une ville par son ID.
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public CityDto getCity(@PathVariable("id") Long id) {
		return cityMapper.toDto(cityService.getCityId(id).get());
	}

	/**
	 * Récupère une ville par son nom.
	 *
	 * @param name le nom de la ville à récupérer.
	 * @return un objet {@link CityDto} représentant la ville correspondante.
	 */
	@GetMapping("/name/{name}")
	public CityDto getcityByName(@PathVariable("name") String name) {
		return cityMapper.toDto(cityService.getCityByName(name));
	}

	/**
	 * Récupère une liste de villes dont le nom commence par une chaîne spécifique.
	 *
	 * @param nameStart la chaîne de caractères par laquelle le nom de la ville commence.
	 * @return une liste de {@link CityDto} représentant les villes correspondantes.
	 */
	@GetMapping("/nameStartingWith/{nameStart}")
	public Iterable<CityDto> getCityByNameStart(@PathVariable("nameStart") String nameStart) {
		return  cityMapper.toDtos(cityService.getCityByNameStart(nameStart));
	}

	/**
	 * Récupère une liste de villes ayant un nombre d'habitants supérieur à une valeur donnée.
	 *
	 * @param min le nombre minimum d'habitants.
	 * @return une liste de {@link CityDto} représentant les villes correspondantes.
	 */
	@GetMapping("/findByNbInhabitantsGreaterThan/{min}")
	public Iterable<CityDto> getCityByNbInhabitantsGreaterThan(@PathVariable("min") Integer min) {
		return  cityMapper.toDtos(cityService.findByNbInhabitantsGreaterThan(min));
	}

	/**
	 * Récupère une liste de villes dont le nombre d'habitants est compris entre deux valeurs.
	 *
	 * @param min la valeur minimale du nombre d'habitants.
	 * @param max la valeur maximale du nombre d'habitants.
	 * @return une liste de {@link CityDto} représentant les villes correspondantes.
	 */
	@GetMapping("/findByNbInhabitantsBetween/{min}/{max}")
	public Iterable<CityDto> findByNbInhabitantsBetween(@PathVariable("min") Integer min, @PathVariable("max") Integer max) {
		return  cityMapper.toDtos(cityService.findByNbInhabitantsBetween(min,max));
	}

	/**
	 * Récupère une liste de villes dans un département donné et ayant un nombre d'habitants supérieur à une valeur donnée.
	 *
	 * @param codeDep le code du département.
	 * @param min le nombre minimum d'habitants.
	 * @return une liste de {@link CityDto} représentant les villes correspondantes.
	 */
	@GetMapping("/findByDepartmentCodeAndNbInhabitantsGreaterThan/{codeDep}/{min}")
	public Iterable<CityDto> findByDepartmentCodeAndNbInhabitantsGreaterThan(@PathVariable("codeDep")String codeDep, @PathVariable("min") Integer min) {
		return  cityMapper.toDtos(cityService.findByDepartmentCodeAndNbInhabitantsGreaterThan(codeDep,min));
	}
	/**
	 * Récupère une liste de villes dans un département donné et dont le nombre d'habitants est compris entre deux valeurs.
	 *
	 * @param codeDep le code du département.
	 * @param min la valeur minimale du nombre d'habitants.
	 * @param max la valeur maximale du nombre d'habitants.
	 * @return une liste de {@link CityDto} représentant les villes correspondantes.
	 */
	@GetMapping("/findByDepartmentCodeAndNbInhabitantsBetween/{codeDep}/{min}/{max}")
	public Iterable<CityDto> findByDepartmentCodeAndNbInhabitantsBetween(@PathVariable("codeDep")String codeDep, @PathVariable("min") Integer min, @PathVariable("max") Integer max) {
		return  cityMapper.toDtos(cityService.findByDepartmentCodeAndNbInhabitantsBetween(codeDep, min,max));
	}

	/**
	 * Récupère une liste de villes dans un département donné et les trie par nombre d'habitants de manière décroissante.
	 *
	 * @param codeDep le code du département.
	 * @param size le nombre de villes à récupérer.
	 * @return une liste de {@link CityDto} représentant les villes correspondantes.
	 */
	@GetMapping("/findByDepartmentCodeOrderByNbInhabitantsDesc/{codeDep}/{size}")
	public Iterable<CityDto> findByDepartmentCodeOrderByNbInhabitantsDesc(@PathVariable("codeDep")String codeDep, @PathVariable("size") Integer size) {
		return  cityMapper.toDtos(cityService.findByDepartmentCodeOrderByNbInhabitantsDesc(codeDep,size));
	}

	/**
	 * Crée une nouvelle ville.
	 *
	 * @param cityDto l'objet {@link CityDto} représentant la ville à créer.
	 * @return un objet {@link ResponseEntity} contenant un message de succès ou d'erreur.
	 */
	@Operation(summary = "Création d'une ville")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Ajoute une nouvelle ville",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = CityDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Erreur dans les paramètres donnés par le client",
					content = @Content)})
	@PostMapping
	public ResponseEntity<String> createCity(@RequestBody CityDto cityDto) {
		com.fr.diginamic.model.City city = cityMapper.fromDto(cityDto);
		Department department = departmentService.findByCode(cityDto.getDepartmentCode());
		if (department == null) {
			return new ResponseEntity<String>("Impossible de créer la ville, pas de département "
					+ cityDto.getDepartmentCode(),HttpStatus.BAD_REQUEST);
		}
		city.setDepartment(department);
		if (this.cityService.addCity(city)) {
			return new ResponseEntity<String>("Succès !",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Impossible de créer la ville, elle existe déjà ou il manque le département : "
					+ city.toString(),HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Met à jour une ville existante.
	 *
	 * @param cityDto l'objet {@link CityDto} représentant la ville à mettre à jour.
	 * @return un objet {@link ResponseEntity} contenant un message de succès ou d'erreur.
	 */
	@PutMapping
	public ResponseEntity<String> updateCity(@RequestBody CityDto cityDto) {
		com.fr.diginamic.model.City city = cityMapper.fromDto(cityDto);
		Department department = departmentService.findByCode(cityDto.getDepartmentCode());
		if (department == null) {
			return new ResponseEntity<String>("Impossible d'update la ville, pas de département "
					+ cityDto.getDepartmentCode(),HttpStatus.BAD_REQUEST);
		}

		if (this.cityService.updateCity(city)) {
			return new ResponseEntity<String>("Succès !",HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("La mise à jour a échouée !",HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Supprime une ville existante.
	 *
	 * @param id l'identifiant de la ville à supprimer.
	 * @return un objet {@link ResponseEntity} contenant un message de succès ou d'erreur.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCity(@PathVariable Long id) {
		if (cityService.deleteCity(id)) {
			return new ResponseEntity<String>("Succès !",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("La supression a échouée !",HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Récupère une liste de villes ayant un nombre d'habitants supérieur à une valeur donnée et les exporte au format CSV.
	 *
	 * @param min le nombre minimum d'habitants.
	 * @param response la réponse HTTP.
	 * @throws IOException
	 */
	@GetMapping("/findByNbInhabitantsGreaterThan/{min}/pdf")
	public void getCityByNbInhabitantsGreaterThanCsv(@PathVariable("min") Integer min,
																  HttpServletResponse response) throws IOException, DocumentException {
		response.setHeader("Content-Disposition", "attachment; filename=cities.pdf");
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document,response.getOutputStream());
		document.open();
		document.newPage();


		// Configuration de la police pour le texte
		BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
		Font headerFont = new Font(baseFont, 14.0f, Font.BOLD, BaseColor.BLACK);
		Font contentFont = new Font(baseFont, 12.0f, Font.NORMAL, BaseColor.DARK_GRAY);

		// Ajouter un titre au document
		document.add(new Paragraph("Liste des villes ayant plus de " + min + " habitants", headerFont));
		document.add(new Paragraph("\n"));

		List<CityDto> cityDtos = (List<CityDto>) cityMapper.toDtos(cityService.findByNbInhabitantsGreaterThan(min));

		// Parcourir la liste des villes et ajouter leurs informations au document
		for (CityDto city : cityDtos) {
			Phrase phrase = new Phrase(
					"Nom de la ville : " + city.getName() + "\n" +
							"Nombre d'habitants : " + city.getInhabitantsNb() + "\n" +
							"Code du département : " + city.getDepartmentCode() + "\n" +
							"Nom du département : " + city.getDepartmentName() + "\n\n",
					contentFont
			);
			document.add(phrase);
		}

		document.close();
		response.flushBuffer();

	}

}
