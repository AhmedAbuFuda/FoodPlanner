package com.example.foodplanner.Network;

import com.example.foodplanner.Models.MealResponses;

import io.reactivex.rxjava3.core.Observable;

public interface MealRemoteDataSource {
    public void makeNetworkCall(NetworkCallBack networkCallback);
    void makeNetworkCall(FilterCallBack filterCallBack, String name, char c);
    Observable<MealResponses> makeNetworkCall( String id);
}
