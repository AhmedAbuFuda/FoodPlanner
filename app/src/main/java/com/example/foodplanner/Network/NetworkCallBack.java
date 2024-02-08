package com.example.foodplanner.Network;

import com.example.foodplanner.Models.Category;
import com.example.foodplanner.Models.Ingredient;
import com.example.foodplanner.Models.Meal;
import com.example.foodplanner.Models.MealResponses;

import java.util.ArrayList;

public interface NetworkCallBack {
    public void onSuccessMeal(ArrayList<Meal> meals);
    public void onSuccessAllCategory(ArrayList<Category> categories);
    public void onSuccessAllIngredients(ArrayList<Ingredient> ingredients);
    public void onFailure(String error);
}
