package com.example.foodplanner.ChooseMeal.Presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.ChooseMeal.view.ChooseView;
import com.example.foodplanner.Models.Meal;
import com.example.foodplanner.Repository.MealRepositoryImp;

import java.util.List;

public class ChoosePresenterImp implements ChoosePresenter {
    ChooseView view;
    MealRepositoryImp repositoryImp;

    public ChoosePresenterImp(ChooseView view, MealRepositoryImp repositoryImp){
        this.view = view;
        this.repositoryImp = repositoryImp;
    }
    @Override
    public LiveData<List<Meal>> getAllFavMeal() {
        return repositoryImp.getFavMeals();
    }

    @Override
    public void insertToPlan(Meal meal) {
        repositoryImp.insert(meal);
    }

    @Override
    public void delete(Meal meal) {
        repositoryImp.delete(meal);
    }
}
