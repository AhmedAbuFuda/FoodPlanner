package com.example.foodplanner.Sign_LoginActivity.View.Fregment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodplanner.MasterActivity.MasterActivity;
import com.example.foodplanner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFragment extends Fragment {
    TextInputEditText fullNameFiled, emailFiled, passwordFiled;
    Button signIn;
    ImageView googleSignIn;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        signIn.setOnClickListener(v -> {
            createNewUser();
        });
    }

    private void createNewUser() {
        String email, password, fullName;
        email = emailFiled.getText().toString();
        password = passwordFiled.getText().toString();
        fullName = fullNameFiled.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getContext(), "Please enter the mail", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(), "Please enter the password", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(fullName)) {
            Toast.makeText(getContext(), "Please enter the name", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), MasterActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init(View view) {
        fullNameFiled = view.findViewById(R.id.fullName);
        emailFiled = view.findViewById(R.id.email);
        passwordFiled = view.findViewById(R.id.password);
        signIn = view.findViewById(R.id.createAccountBtn);
        googleSignIn = view.findViewById(R.id.googleImage);
        mAuth = FirebaseAuth.getInstance();
    }
}