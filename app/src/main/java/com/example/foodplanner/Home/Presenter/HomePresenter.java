package com.example.foodplanner.Home.Presenter;

import com.example.foodplanner.Models.Meal;

public interface HomePresenter {
    public void getRandomMeal();
    void addToFav(Meal meal);
}
