package com.example.foodplanner.Home.Presenter;

import com.example.foodplanner.Home.View.HomeMealsView;
import com.example.foodplanner.Models.Category;
import com.example.foodplanner.Models.Country;
import com.example.foodplanner.Models.Ingredient;
import com.example.foodplanner.Models.Meal;
import com.example.foodplanner.Network.NetworkCallBack;
import com.example.foodplanner.Repository.MealRepositoryImp;

import java.util.ArrayList;

public class HomePresenterImp implements HomePresenter, NetworkCallBack{

    private HomeMealsView view;
    private MealRepositoryImp repositoryImp;
    public HomePresenterImp(HomeMealsView view, MealRepositoryImp repositoryImp){
        this.view = view;
        this.repositoryImp = repositoryImp;
    }

    @Override
    public void getRandomMeal() {
        repositoryImp.getRandomMeal(this);
    }

    @Override
    public void addToFav(Meal meal) {
        repositoryImp.insert(meal);
    }

    @Override
    public void onSuccessMeal(ArrayList<Meal> meals) {
        view.showMeals(meals);
    }

    @Override
    public void onSuccessAllCategory(ArrayList<Category> categories) {
        view.showCategories(categories);
    }

    @Override
    public void onSuccessAllIngredients(ArrayList<Ingredient> ingredients) {
        view.showIngredient(ingredients);
    }

    @Override
    public void onSuccessAllCountries(ArrayList<Country> countries) {
        view.showCountries(countries);
    }

    @Override
    public void onFailure(String error) {
        view.showErrMsg(error);
    }
}
