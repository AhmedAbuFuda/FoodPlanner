package com.example.foodplanner.Repository;

import com.example.foodplanner.Network.FilterCallBack;
import com.example.foodplanner.Network.MealByIdCallBack;
import com.example.foodplanner.Network.NetworkCallBack;

public interface MealRepository {
    void getRandomMeal(NetworkCallBack networkCallBack);
    void getFilteredMeals(FilterCallBack filterCallBack, String name, char c);
    void getMealsById(MealByIdCallBack mealByIdCallBack, String id);
}
