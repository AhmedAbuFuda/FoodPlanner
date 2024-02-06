package com.example.foodplanner.Sign_LoginActivity.View.Fregment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodplanner.MasterActivity.MasterActivity;
import com.example.foodplanner.R;


public class LogInFragment extends Fragment {
    Button guest;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        guest = view.findViewById(R.id.guestBtn);
        guest.setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(), MasterActivity.class);
            startActivity(intent);
        });
    }
}