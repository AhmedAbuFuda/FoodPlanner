package com.example.foodplanner.Network;

import android.util.Log;

import com.example.foodplanner.Models.CategoryResponse;
import com.example.foodplanner.Models.IngredientResponse;
import com.example.foodplanner.Models.Meal;
import com.example.foodplanner.Models.MealResponses;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSourceImp implements  MealRemoteDataSource {

    private static final String url = "https://www.themealdb.com/";

    private MealServices services;
    private static MealRemoteDataSourceImp MealClient;

    public MealRemoteDataSourceImp() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url).build();
        services = retrofit.create(MealServices.class);
    }

    public static MealRemoteDataSourceImp getInstance() {
        if (MealClient == null) {
            MealClient = new MealRemoteDataSourceImp();
        }
        return MealClient;
    }


    @Override
    public void makeNetworkCall(NetworkCallBack networkCallback) {
        Call<MealResponses> getRandom = services.getMealsByRandom();
        getRandom.enqueue(new Callback<MealResponses>() {
            @Override
            public void onResponse(Call<MealResponses> call, Response<MealResponses> response) {
                networkCallback.onSuccessMeal(response.body().meals);
            }

            @Override
            public void onFailure(Call<MealResponses> call, Throwable t) {
                networkCallback.onFailure(t.getMessage());
                Log.i("TAG", "OnFailure: "+t.getMessage());
            }
        });

        Call<CategoryResponse> getCategory = services.getAllCategories();
        getCategory.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                networkCallback.onSuccessAllCategory(response.body().categories);
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                networkCallback.onFailure(t.getMessage());
                Log.i("TAG", "OnFailure: "+t.getMessage());
            }
        });

        Call<IngredientResponse> getIngredients = services.getAllIngredients();
        getIngredients.enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                networkCallback.onSuccessAllIngredients(response.body().meals);
                Log.i("TAG", "onResponse: "+response.body().meals);
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable t) {
                networkCallback.onFailure(t.getMessage());
                Log.i("TAG", "OnFailure: "+t.getMessage());
            }
        });

    }
}
