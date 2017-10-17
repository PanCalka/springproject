package com.PanCalka.Recipe.repositories;

import com.PanCalka.Recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
