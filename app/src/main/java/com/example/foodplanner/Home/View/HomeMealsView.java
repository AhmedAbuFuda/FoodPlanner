package com.example.foodplanner.Home.View;

import com.example.foodplanner.Models.Meal;

import java.util.ArrayList;

public interface HomeMealsView {
    public void showMeals(ArrayList<Meal> meals);
    public void showErrMsg(String error);
}
