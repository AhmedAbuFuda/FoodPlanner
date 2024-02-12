package com.example.foodplanner.Favorite.Presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Favorite.view.FavoriteView;
import com.example.foodplanner.Models.Meal;
import com.example.foodplanner.Repository.MealRepositoryImp;

import java.util.List;

public class FavoritePresenterImp implements FavoritePresenter{
    FavoriteView view;
    MealRepositoryImp repositoryImp;

    public FavoritePresenterImp(FavoriteView view, MealRepositoryImp repositoryImp){
        this.view = view;
        this.repositoryImp = repositoryImp;
    }
    @Override
    public LiveData<List<Meal>> getAllFavMeal() {
        return repositoryImp.getFavMeals();
    }

    @Override
    public void deleteFromFav(Meal meal) {
        repositoryImp.delete(meal);
    }
}
