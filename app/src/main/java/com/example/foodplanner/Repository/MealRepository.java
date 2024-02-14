package com.example.foodplanner.Repository;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Models.Meal;
import com.example.foodplanner.Network.FilterCallBack;
import com.example.foodplanner.Network.MealByIdCallBack;
import com.example.foodplanner.Network.NetworkCallBack;

import java.util.List;

public interface MealRepository {
    void getRandomMeal(NetworkCallBack networkCallBack);
    void getFilteredMeals(FilterCallBack filterCallBack, String name, char c);
    void getMealsById(MealByIdCallBack mealByIdCallBack, String id);
    void insert(Meal meal);
    void delete(Meal meal);
    LiveData<List<Meal>> getFavMeals();
    LiveData<List<Meal>> getPlanMeals(String day);
}
