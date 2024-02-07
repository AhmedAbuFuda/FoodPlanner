package com.example.foodplanner.db;

import android.content.Context;

public class MealLocalDataSourceImp implements MealLocalDataSource{
    private Context context;
    private static MealLocalDataSourceImp dataSourceImp;

    private MealLocalDataSourceImp(Context _context){
        context = _context;
    }
    public static MealLocalDataSourceImp getInstance(Context context){
        if (dataSourceImp == null){
            dataSourceImp = new MealLocalDataSourceImp(context);
        }
        return dataSourceImp;
    }
}
