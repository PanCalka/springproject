package com.PanCalka.Recipe.services;

import com.PanCalka.Recipe.commands.RecipeCommand;
import com.PanCalka.Recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipe();

    Recipe findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
