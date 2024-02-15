package com.example.foodplanner.Repository;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Models.Meal;
import com.example.foodplanner.Models.MealResponses;
import com.example.foodplanner.Network.FilterCallBack;
import com.example.foodplanner.Network.MealByIdCallBack;
import com.example.foodplanner.Network.MealRemoteDataSource;
import com.example.foodplanner.Network.NetworkCallBack;
import com.example.foodplanner.db.MealLocalDataSource;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

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
    public Observable<MealResponses> getMealsById( String id) {
        return remoteDataSource.makeNetworkCall(id);
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
    public Flowable<List<Meal>> getFavMeals() {
        return localDataSource.getFavMeals();
    }

    @Override
    public LiveData<List<Meal>> getPlanMeals(String day) {
        return  localDataSource.getPlanMeals(day);
    }


}
