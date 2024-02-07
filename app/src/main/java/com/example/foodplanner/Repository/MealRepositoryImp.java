package com.example.foodplanner.Repository;

import com.example.foodplanner.Network.MealRemoteDataSource;
import com.example.foodplanner.Network.NetworkCallBack;
import com.example.foodplanner.db.MealLocalDataSource;

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


}
