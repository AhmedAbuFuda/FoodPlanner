package com.example.foodplanner.MealActivity.Presenter;

import com.example.foodplanner.MealActivity.view.MealView;
import com.example.foodplanner.MealListActivity.view.MealListView;
import com.example.foodplanner.Models.Meal;
import com.example.foodplanner.Network.MealByIdCallBack;
import com.example.foodplanner.Repository.MealRepositoryImp;

import java.util.ArrayList;

public class MealPresenterImp implements MealPresenter, MealByIdCallBack {
    private MealView view;
    private MealRepositoryImp repositoryImp;

    public MealPresenterImp(MealView view, MealRepositoryImp repositoryImp){
        this.view = view;
        this.repositoryImp = repositoryImp;
    }
    @Override
    public void getMealsById(String id) {
        repositoryImp.getMealsById(this,id);
    }

    @Override
    public void onSuccessMealById(ArrayList<Meal> meals) {
        view.showMeals(meals);
    }

    @Override
    public void onFailure(String error) {
        view.showErrMsg(error);
    }
}
