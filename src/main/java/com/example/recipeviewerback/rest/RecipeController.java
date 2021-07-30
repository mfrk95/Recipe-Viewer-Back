package com.example.recipeviewerback.rest;

import com.example.recipeviewerback.dao.RecipeRepository;
import com.example.recipeviewerback.entity.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/recipes")
public class RecipeController {
    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/recipesAll")
    public List<Recipe> findAll(){
        List<Recipe> recipeList = recipeRepository.findAll();
        return recipeList;
    }

    @PostMapping("/addRecipe")
    public String addRecipe( MultipartFile file, @RequestParam String title, @RequestParam String description, @RequestParam String category) {

        String path = "uploads/";
        String fileName = file.getOriginalFilename();
        File dest = new File(new File(path).getAbsolutePath() + "/" + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest); // save document
        }catch(Exception e){
            System.out.println(e);
        }
        Recipe rcp = new Recipe(title, description,category,dest.getAbsolutePath());
        recipeRepository.save(rcp);
        return "Uploaded successfully!";
    }

}

