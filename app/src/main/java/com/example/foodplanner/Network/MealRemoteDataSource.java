package com.example.foodplanner.Network;

public interface MealRemoteDataSource {
    public void makeNetworkCall(NetworkCallBack networkCallback);
    void makeNetworkCall(FilterCallBack filterCallBack, String name, char c);
    void makeNetworkCall(MealByIdCallBack mealByIdCallBack, String id);
}
