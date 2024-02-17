package com.example.foodplanner.Account.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Account.Presenter.AccountPresenter;
import com.example.foodplanner.Account.Presenter.AccountPresenterImp;
import com.example.foodplanner.ChooseMeal.Presenter.ChoosePresenter;
import com.example.foodplanner.ChooseMeal.Presenter.ChoosePresenterImp;
import com.example.foodplanner.Models.Meal;
import com.example.foodplanner.Models.SaveMeals;
import com.example.foodplanner.Network.MealRemoteDataSourceImp;
import com.example.foodplanner.R;
import com.example.foodplanner.Repository.MealRepositoryImp;
import com.example.foodplanner.Sign_LoginActivity.View.MainActivity;
import com.example.foodplanner.db.MealLocalDataSourceImp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AccountFragment extends Fragment implements AccountView {
    TextView username;
    TextInputEditText emailField;
    ImageView userProfile;
    Button button;
    AccountPresenter presenter;
    public static final String PREFERENCE_FILE = "file";
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    SaveMeals meal = new SaveMeals();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        presenter = new AccountPresenterImp(this, MealRepositoryImp.getInstance(MealRemoteDataSourceImp.getInstance(),
                MealLocalDataSourceImp.getInstance(getContext())));
        presenter.getAllFavMeal();

        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences(PREFERENCE_FILE, 0);
        String email = sharedPreferences1.getString("email","");
        databaseReference = database.getReference("SavedMeals").child(email.replaceAll("[\\.#$\\[\\]]", ""));
        getUserDetails(email);



        button.setOnClickListener(v -> {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    databaseReference.setValue(meal);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFERENCE_FILE, 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email","gust");
            editor.apply();

            databaseReference = database.getReference("SavedMeals");
            databaseReference.removeValue();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
    }

    @Override
    public void getAllMeal(List<Meal> meals) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFERENCE_FILE, 0);
        String email = sharedPreferences.getString("email","");
        meal.setEmail(email);
        meal.setMeals((ArrayList<Meal>) meals);
    }

    private void init(View view){
        button = view.findViewById(R.id.logoutBtn);
        database =  FirebaseDatabase.getInstance();
        username = view.findViewById(R.id.username);
        emailField = view.findViewById(R.id.useremail);
        userProfile = view.findViewById(R.id.profile_image);
    }

    void getUserDetails(String email){
        databaseReference = database.getReference("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    if (dataSnapshot.getKey().equals(email.replaceAll("[\\.#$\\[\\]]", ""))){
                        //userEmail = dataSnapshot.child("email").getValue().toString();
                        emailField.setText(dataSnapshot.child("email").getValue().toString());

                        Log.i("userAccount", "onDataChange: "+dataSnapshot.child("email").getValue().toString());
                        //userName = dataSnapshot.child("name").getValue().toString();
                        username.setText(dataSnapshot.child("name").getValue().toString());
                        Log.i("userAccount", "onDataChange: "+dataSnapshot.child("name").getValue().toString());

                       //userImage = dataSnapshot.child("profile").getValue().toString();
                        Glide.with(getContext()).load(dataSnapshot.child("profile").getValue().toString()).placeholder(R.drawable.loading).error(R.drawable.ic_launcher_foreground).into(userProfile);
                        Log.i("userAccount", "onDataChange: "+dataSnapshot.child("profile").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}