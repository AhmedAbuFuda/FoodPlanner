package com.example.foodplanner.Repository;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Models.Meal;
import com.example.foodplanner.Models.MealResponses;
import com.example.foodplanner.Network.FilterCallBack;
import com.example.foodplanner.Network.MealByIdCallBack;
import com.example.foodplanner.Network.NetworkCallBack;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface MealRepository {
    void getRandomMeal(NetworkCallBack networkCallBack);
    Observable<MealResponses> getFilteredMeals(String name, char c);
    Observable<MealResponses> getMealsById(String id);
    void insert(Meal meal);
    void delete(Meal meal);
    Flowable<List<Meal>> getFavMeals();
    LiveData<List<Meal>> getPlanMeals(String day);
}
