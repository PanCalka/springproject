package com.PanCalka.Recipe.controllers;

import com.PanCalka.Recipe.domain.Category;
import com.PanCalka.Recipe.domain.UnitOfMeasure;
import com.PanCalka.Recipe.repositories.CategoryRepository;
import com.PanCalka.Recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class indexController {

	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;

	public indexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@RequestMapping({"","/","/index"})
	public String getIndexPage() {
		Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
		Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");

		System.out.println("Cat id is " + categoryOptional.get().getId());

		return "index";
	}
}
