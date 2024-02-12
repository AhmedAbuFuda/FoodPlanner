package com.example.foodplanner.db;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Models.Meal;

import java.util.List;

public interface MealLocalDataSource {
    LiveData<List<Meal>> getFavMeals();
    void insert(Meal meal);
    void delete(Meal meal);
}
