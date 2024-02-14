package com.example.foodplanner.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.foodplanner.Models.Meal;
import java.util.List;
@Dao
public interface MealDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal (Meal meal);
    @Delete
    void deleteMeal (Meal meal);
    @Query("SELECT * FROM FavoriteMeals")
    LiveData<List<Meal>> getAllFavMeal();
    @Query("SELECT * FROM FavoriteMeals where day = :day")
    LiveData<List<Meal>> getMealsByDay(String day);
}
