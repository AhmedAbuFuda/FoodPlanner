package com.example.foodplanner.Home.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Home.Presenter.HomePresenter;
import com.example.foodplanner.Home.Presenter.HomePresenterImp;
import com.example.foodplanner.Models.Meal;
import com.example.foodplanner.Network.MealRemoteDataSourceImp;
import com.example.foodplanner.R;
import com.example.foodplanner.Repository.MealRepositoryImp;
import com.example.foodplanner.db.MealLocalDataSourceImp;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements HomeMealsView {
    ImageView dailyMealImage, newMeal;
    TextView nameOfDailyMeal;
    private Meal randomMeal;
    HomePresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        presenter = new HomePresenterImp(this, MealRepositoryImp.getInstance(MealRemoteDataSourceImp.getInstance(),
                MealLocalDataSourceImp.getInstance(getContext())));
        presenter.getRandomMeal();

        newMeal.setOnClickListener(v -> {
            presenter.getRandomMeal();
        });
    }

    @Override
    public void showMeals(ArrayList<Meal> meals) {
        randomMeal = meals.get(0);
        Glide.with(getContext()).load(meals.get(0).getStrMealThumb()).placeholder(R.drawable.loading).error(R.drawable.ic_launcher_foreground).into(dailyMealImage);
        nameOfDailyMeal.setText(meals.get(0).getStrMeal());
    }

    @Override
    public void showErrMsg(String error) {
        Log.i("TAG", "showErrMsg: "+error);
    }

    private void init(View view){
        dailyMealImage = view.findViewById(R.id.dailyMealImage);
        nameOfDailyMeal = view.findViewById(R.id.nameOfDailyMeal);
        newMeal = view.findViewById(R.id.newMeal);
    }
}