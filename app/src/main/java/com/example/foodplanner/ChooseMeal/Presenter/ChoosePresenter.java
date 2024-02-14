package com.example.foodplanner.ChooseMeal.Presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Models.Meal;

import java.util.List;

public interface ChoosePresenter {
    LiveData<List<Meal>> getAllFavMeal();
    void insertToPlan(Meal meal, String day);
}
