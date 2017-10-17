package com.PanCalka.Recipe.controllers;

import com.PanCalka.Recipe.domain.Category;
import com.PanCalka.Recipe.domain.UnitOfMeasure;
import com.PanCalka.Recipe.repositories.CategoryRepository;
import com.PanCalka.Recipe.repositories.UnitOfMeasureRepository;
import com.PanCalka.Recipe.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class indexController {

	private final RecipeService recipeService;

	@Autowired
	public indexController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping({"","/","/index"})
	public String getIndexPage(Model model) {
		model.addAttribute("recipes", recipeService.getRecipe());

		return "index";
	}
}
