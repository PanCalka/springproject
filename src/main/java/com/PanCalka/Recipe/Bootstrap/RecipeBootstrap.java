package com.PanCalka.Recipe.Bootstrap;

import com.PanCalka.Recipe.domain.*;
import com.PanCalka.Recipe.repositories.CategoryRepository;
import com.PanCalka.Recipe.repositories.RecipeRepository;
import com.PanCalka.Recipe.repositories.UnitOfMeasureRepository;
import lombok.Getter;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import sun.applet.AppletEvent;
import sun.applet.AppletListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipe());
    }

    private List<Recipe> getRecipe() {

        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUnit = unitOfMeasureRepository.findByDescription("Each");

        if (!eachUnit.isPresent()) {
            throw new RuntimeException("EachUnit uom not found");
        }

        Optional<UnitOfMeasure> tablespoonUnit = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tablespoonUnit.isPresent()) {
            throw new RuntimeException("Tablespoon uom not found");
        }

        Optional<UnitOfMeasure> teaSpoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaSpoon.isPresent()) {
            throw new RuntimeException("teaspoon uom not found");
        }

        Optional<UnitOfMeasure> dashuom = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashuom.isPresent()) {
            throw new RuntimeException("Dash uom not found");
        }
        Optional<UnitOfMeasure> pint = unitOfMeasureRepository.findByDescription("Pint");
        if (!pint.isPresent()) {
            throw new RuntimeException("Pint uom not found");
        }

        UnitOfMeasure teaspoon = teaSpoon.get();
        UnitOfMeasure eachUom = eachUnit.get();
        UnitOfMeasure tablespUnitOfMeasure = tablespoonUnit.get();
        UnitOfMeasure dashUnit = dashuom.get();
        UnitOfMeasure pintUom = pint.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("American category not found");
        }
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Mexican category not found");
        }


        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        //some random Recipe
        Recipe random = new Recipe();
        random.setCookTime(10);
        random.setPrepTime(10);
        random.setServings(5);
        random.setDifficulty(Difficulty.EASY);
        random.setDescription("Some random description to fill");
        Notes randomNotes = new Notes();
        randomNotes.setRecipeNotes("Futher random text");

        random.setNotes(randomNotes);

        random.getIngredients().add(new Ingredient("jummy recipe", new BigDecimal(2), eachUom, random));
        random.getIngredients().add(new Ingredient("jummy recipe1", new BigDecimal(3), pintUom, random));
        random.getIngredients().add(new Ingredient("jummy recipe2", new BigDecimal(4), teaspoon, random));

        random.getCategories().add(americanCategory);
        recipes.add(random);
        return recipes;

    }

}

