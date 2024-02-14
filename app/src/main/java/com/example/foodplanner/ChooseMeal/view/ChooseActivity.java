package com.example.foodplanner.ChooseMeal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodplanner.R;

public class ChooseActivity extends AppCompatActivity {

    RecyclerView chooseRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
    }

    private void init(){
        chooseRV = findViewById(R.id.chooseRV);
    }
}