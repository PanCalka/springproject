package com.PanCalka.Recipe.services;

import com.PanCalka.Recipe.commands.RecipeCommand;
import com.PanCalka.Recipe.domain.Recipe;
import javassist.NotFoundException;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipe();

    Recipe findById(Long l) throws NotFoundException;

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long aLong);

    void deleteById(Long deletedId);
}
