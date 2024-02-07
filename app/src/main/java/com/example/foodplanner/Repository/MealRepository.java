package com.example.foodplanner.Repository;

import com.example.foodplanner.Network.NetworkCallBack;

public interface MealRepository {
    void getRandomMeal(NetworkCallBack networkCallBack);


}
