package com.example.foodplanner.MasterActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.foodplanner.Account.AccountFragment;
import com.example.foodplanner.Favorite.FavoriteFragment;
import com.example.foodplanner.Home.HomeFragment;
import com.example.foodplanner.Plan.PlanFragment;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MasterActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment;
    FavoriteFragment favoriteFragment;
    SearchFragment searchFragment;
    PlanFragment planFragment;
    AccountFragment accountFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        init();
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id == R.id.home){
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFrameLayout, homeFragment).commit();
                return true;
            } else if (id == R.id.favorite) {
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFrameLayout, favoriteFragment).commit();
                return true;
            }else if (id == R.id.search) {
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFrameLayout, searchFragment).commit();
                return true;
            }else if (id == R.id.plan) {
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFrameLayout, planFragment).commit();
                return true;
            }else if (id == R.id.account) {
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFrameLayout, accountFragment).commit();
                return true;
            }
            return false;
        });
    }

    private void init(){
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        homeFragment = new HomeFragment();
        favoriteFragment = new FavoriteFragment();
        searchFragment = new SearchFragment();
        planFragment = new PlanFragment();
        accountFragment = new AccountFragment();
    }

}