package com.example.foodplanner.Network;

import com.example.foodplanner.Models.CategoryResponse;
import com.example.foodplanner.Models.Meal;
import com.example.foodplanner.Models.MealResponses;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealServices {
    @GET("api/json/v1/1/random.php")
    Call<MealResponses> getMealsByRandom();
    @GET("api/json/v1/1/categories.php")
    Call<CategoryResponse> getAllCategories();

}
