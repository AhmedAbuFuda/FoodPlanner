package com.example.foodplanner.Network;

import com.example.foodplanner.Models.Meal;

public interface MealRemoteDataSource {
    public void makeNetworkCall(NetworkCallBack networkCallback);
}
