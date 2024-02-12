package com.example.foodplanner.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Models.Meal;

import java.util.List;

public class MealLocalDataSourceImp implements MealLocalDataSource{
    private Context context;
    private MealDAO dao;
    private LiveData<List<Meal>> data;
    private static MealLocalDataSourceImp dataSourceImp;

    private MealLocalDataSourceImp(Context _context){
        context = _context;
        AppDatabase dataBase = AppDatabase.getInstance(context.getApplicationContext());
        dao = dataBase.getFavMealDAO();
        data = dao.getAllFavMeal();
    }
    public static MealLocalDataSourceImp getInstance(Context context){
        if (dataSourceImp == null){
            dataSourceImp = new MealLocalDataSourceImp(context);
        }
        return dataSourceImp;
    }

    @Override
    public LiveData<List<Meal>> getFavMeals() {
        return data;
    }

    @Override
    public void insert(Meal meal) {
        new Thread() {
            @Override
            public void run() {
                dao.insertMeal(meal);
            }
        }.start();
    }

    @Override
    public void delete(Meal meal) {
        new Thread() {
            @Override
            public void run() {
                dao.deleteMeal(meal);
            }
        }.start();
    }
}
