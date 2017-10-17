package com.PanCalka.Recipe.domain;


import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	//todo
	//private Difficulty difficylty;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredient;

	@Lob
	private Byte[] image;
	
	@OneToOne(cascade= CascadeType.ALL)
	private Notes notes;
	
	
}
