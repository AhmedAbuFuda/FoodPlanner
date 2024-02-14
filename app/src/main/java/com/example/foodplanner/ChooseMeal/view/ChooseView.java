package com.example.foodplanner.ChooseMeal.view;

import com.example.foodplanner.Models.Meal;

public interface ChooseView {
    void getPlanMeal();
    void insertMeal(Meal meal);
    void delete(Meal meal);
}
