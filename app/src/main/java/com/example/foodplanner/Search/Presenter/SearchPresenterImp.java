package com.example.foodplanner.Search.Presenter;

import com.example.foodplanner.Home.View.HomeMealsView;
import com.example.foodplanner.Models.Category;
import com.example.foodplanner.Models.Country;
import com.example.foodplanner.Models.Ingredient;
import com.example.foodplanner.Models.Meal;
import com.example.foodplanner.Network.NetworkCallBack;
import com.example.foodplanner.Repository.MealRepositoryImp;
import com.example.foodplanner.Search.view.SearchView;

import java.util.ArrayList;

public class SearchPresenterImp implements SearchPresenter, NetworkCallBack {
    private SearchView view;
    private MealRepositoryImp repositoryImp;

    public SearchPresenterImp(SearchView view, MealRepositoryImp repositoryImp){
        this.view = view;
        this.repositoryImp = repositoryImp;
    }
    @Override
    public void getSearchItem() { repositoryImp.getRandomMeal(this);}

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

    @Override
    public void onSuccessMeal(ArrayList<Meal> meals) {

    }
}
