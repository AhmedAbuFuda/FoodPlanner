package com.example.foodplanner.Plan.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodplanner.R;


public class PlanFragment extends Fragment implements PlanView{
    Button mondayBtn,tuesdayBtn,wednesdayBtn,thursdayBtn,fridayBtn,saturdayBtn,sundayBtn;
    RecyclerView mondayRV,tuesdayRV,wednesdayRV,thursdayRV,fridayRV,saturdayRV,sundayRV;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view){
        mondayBtn = view.findViewById(R.id.mondayBtn);
        tuesdayBtn = view.findViewById(R.id.tuesdayBtn);
        wednesdayBtn = view.findViewById(R.id.wednesdayBtn);
        thursdayBtn = view.findViewById(R.id.thursdayBtn);
        fridayBtn = view.findViewById(R.id.fridayBtn);
        saturdayBtn = view.findViewById(R.id.saturdayBtn);
        sundayBtn = view.findViewById(R.id.sundayBtn);
        mondayRV = view.findViewById(R.id.mondayRV);
        tuesdayRV = view.findViewById(R.id.tuesdayRV);
        wednesdayRV = view.findViewById(R.id.wednesdayRV);
        thursdayRV = view.findViewById(R.id.thursdayRV);
        fridayRV = view.findViewById(R.id.fridayRV);
        saturdayRV = view.findViewById(R.id.saturdayRV);
        sundayRV = view.findViewById(R.id.sundayRV);
    }
}