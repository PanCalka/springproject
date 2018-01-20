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
        random.setSource("internet");
        random.setDifficulty(Difficulty.EASY);
        random.setUrl("www.some-url.com");
        random.setDescription("Jummy pancake");
        Notes randomNotes = new Notes();
        randomNotes.setRecipeNotes("Eat it fast");
        random.setDirections(
                "    In a large bowl, sift together the flour, baking powder, salt and sugar. Make a well in the center " +
                        "and pour in the milk, egg and melted butter; mix until smooth.\n" +
                        "  \n  Heat a lightly oiled griddle or frying pan over medium high heat. Pour or scoop the batter onto the griddle, " +
                        "using approximately 1/4 cup for each pancake. Brown on both sides and serve hot.\n");

        random.setNotes(randomNotes);

        random.getIngredients().add(new Ingredient("banana", new BigDecimal(2), eachUom, random));
        random.getIngredients().add(new Ingredient("chocolate", new BigDecimal(3), pintUom, random));
        random.getIngredients().add(new Ingredient("pancake", new BigDecimal(4), eachUom , random));

        random.getCategories().add(americanCategory);
        recipes.add(random);
        return recipes;

    }

}

