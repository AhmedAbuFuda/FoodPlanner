package com.example.foodplanner.Repository;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Models.Meal;
import com.example.foodplanner.Network.FilterCallBack;
import com.example.foodplanner.Network.MealByIdCallBack;
import com.example.foodplanner.Network.MealRemoteDataSource;
import com.example.foodplanner.Network.NetworkCallBack;
import com.example.foodplanner.db.MealLocalDataSource;

import java.util.List;

public class MealRepositoryImp implements MealRepository{
    MealRemoteDataSource remoteDataSource;
    MealLocalDataSource localDataSource;
    private static MealRepositoryImp mealRepositoryImp = null;
    private MealRepositoryImp(MealRemoteDataSource remoteDataSource, MealLocalDataSource localDataSource ){
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }
    public static MealRepositoryImp getInstance(MealRemoteDataSource remoteDataSource , MealLocalDataSource localDataSource){
        if (mealRepositoryImp == null){
            mealRepositoryImp = new MealRepositoryImp(remoteDataSource,localDataSource);
        }
        return mealRepositoryImp;
    }

    @Override
    public void getRandomMeal(NetworkCallBack networkCallBack) {
        remoteDataSource.makeNetworkCall(networkCallBack);
    }

    @Override
    public void getFilteredMeals(FilterCallBack filterCallBack, String name, char c) {
        remoteDataSource.makeNetworkCall(filterCallBack,name,c);
    }

    @Override
    public void getMealsById(MealByIdCallBack mealByIdCallBack, String id) {
        remoteDataSource.makeNetworkCall(mealByIdCallBack,id);
    }

    @Override
    public void insert(Meal meal) {
        localDataSource.insert(meal);
    }

    @Override
    public void delete(Meal meal) {
        localDataSource.delete(meal);
    }

    @Override
    public LiveData<List<Meal>> getFavMeals() {
        return localDataSource.getFavMeals();
    }


}
