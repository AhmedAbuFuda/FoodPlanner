package com.example.foodplanner.Favorite.Presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Models.Meal;

import java.util.List;

public interface FavoritePresenter {
    LiveData<List<Meal>> getAllFavMeal();
    void deleteFromFav(Meal meal);
}
