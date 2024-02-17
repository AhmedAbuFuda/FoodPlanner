package com.example.foodplanner.Account.Presenter;

import android.annotation.SuppressLint;

import com.example.foodplanner.Account.view.AccountView;
import com.example.foodplanner.ChooseMeal.view.ChooseView;
import com.example.foodplanner.Models.Meal;
import com.example.foodplanner.Repository.MealRepositoryImp;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;

public class AccountPresenterImp implements AccountPresenter{
    AccountView view;
    MealRepositoryImp repositoryImp;

    public AccountPresenterImp(AccountView view, MealRepositoryImp repositoryImp){
        this.view = view;
        this.repositoryImp = repositoryImp;
    }
    @SuppressLint("CheckResult")
    @Override
    public void getAllFavMeal() {
        Flowable<List<Meal>> flowable = repositoryImp.getFavMeals();
        flowable.observeOn(AndroidSchedulers.mainThread()).subscribe(meals -> {
            view.getAllMeal(meals);
        });
    }
}
