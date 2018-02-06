package com.PanCalka.Recipe.services;

import com.PanCalka.Recipe.commands.IngredientCommand;
import org.springframework.stereotype.Service;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteRecipeById(Long recipeId, Long id);
}
