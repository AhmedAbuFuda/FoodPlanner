package com.example.foodplanner.Network;

import com.example.foodplanner.Models.Meal;
import com.example.foodplanner.Models.MealResponses;

import java.util.ArrayList;

public interface NetworkCallBack {
    public void onSuccessMeal(ArrayList<Meal> meals);
    public void onFailure(String error);
}
