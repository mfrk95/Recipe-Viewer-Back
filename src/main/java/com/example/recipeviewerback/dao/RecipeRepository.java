package com.example.recipeviewerback.dao;

import com.example.recipeviewerback.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository  extends JpaRepository<Recipe,Integer> {
}
