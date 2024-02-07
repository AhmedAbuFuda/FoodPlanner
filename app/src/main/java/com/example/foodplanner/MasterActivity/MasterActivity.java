package com.example.foodplanner.MasterActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.foodplanner.Account.AccountFragment;
import com.example.foodplanner.Favorite.FavoriteFragment;
import com.example.foodplanner.Home.View.HomeFragment;
import com.example.foodplanner.Plan.PlanFragment;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MasterActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        init();
        changeFragment(new HomeFragment());
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id == R.id.home){
                changeFragment(new HomeFragment());
                return true;
            } else if (id == R.id.favorite) {
                changeFragment(new FavoriteFragment());
                return true;
            }else if (id == R.id.search) {
                changeFragment(new SearchFragment());
                return true;
            }else if (id == R.id.plan) {
                changeFragment(new PlanFragment());
                return true;
            }else if (id == R.id.account) {
                changeFragment(new AccountFragment());
                return true;
            }
            return false;
        });
    }

    private void init(){
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    private void changeFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.containerFrameLayout,fragment);
        transaction.commit();
    }

}