package com.example.foodplanner.MealListActivity.Presenter;


import com.example.foodplanner.MealListActivity.view.MealListView;
import com.example.foodplanner.Models.Meal;
import com.example.foodplanner.Network.FilterCallBack;
import com.example.foodplanner.Repository.MealRepositoryImp;
import java.util.ArrayList;


public class MealListPresenterImp implements MealListPresenter, FilterCallBack {

    private MealListView view;
    private MealRepositoryImp repositoryImp;
    public MealListPresenterImp(MealListView view, MealRepositoryImp repositoryImp){
        this.view = view;
        this.repositoryImp = repositoryImp;
    }

    @Override
    public void onSuccessMealByFilter(ArrayList<Meal> meals) {
        view.showMeals(meals);
    }

    @Override
    public void onFailure(String error) {
        view.showErrMsg(error);
    }

    @Override
    public void getFilteredMeals(String name, char c) {
        repositoryImp.getFilteredMeals(this,name,c);
    }

}
