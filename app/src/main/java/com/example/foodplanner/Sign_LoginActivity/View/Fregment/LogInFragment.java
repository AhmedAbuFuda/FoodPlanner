package com.example.foodplanner.Sign_LoginActivity.View.Fregment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodplanner.MasterActivity.MasterActivity;
import com.example.foodplanner.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;


public class LogInFragment extends Fragment {
    TextInputEditText emailFiled, passwordFiled;
    Button login, guest;
    ImageView googleImage;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    GoogleSignInClient googleSignInClient;
    int RC_SIGN_IN = 20;

    public static final String PREFERENCE_FILE = "file";
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
        SharedPreferences shared = getContext().getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        if (!shared.getString("email", "gust").equals("gust")) {
            Intent intent = new Intent(view.getContext(), MasterActivity.class);
            startActivity(intent);
        }

        super.onViewCreated(view, savedInstanceState);
        init(view);
        checkBox();
        login.setOnClickListener(v -> {
            loginUser();
        });
        guest.setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(), MasterActivity.class);
            SharedPreferences sharedPreferences = getContext().getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email","gust");
            editor.apply();
            startActivity(intent);
        });
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("573389882437-ah7dc15e0kmsqrc0g62pa8v9gflb8ic9.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(getContext(),signInOptions);
        googleImage.setOnClickListener(v -> {
            googleSignIn();
        });

        googleSignInClient = GoogleSignIn.getClient(getContext(), signInOptions);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());



    }

    private void loginUser() {
        String email, password;
        email = emailFiled.getText().toString();
        password = passwordFiled.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getContext(), "Please enter the email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(), "Please enter the password", Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        SharedPreferences sharedPreferences = getContext().getSharedPreferences(PREFERENCE_FILE,Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email",email);
                        editor.apply();
                        Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(), MasterActivity.class));
                        getActivity().finish();
                    } else {
                        Toast.makeText(getContext(), "Login failed", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void googleSignIn(){
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuth(account.getIdToken());
            }catch (Exception e){
                Log.i("whathappen", "onActivityResult: "+e.getMessage());
                Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuth(String idToken){
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        HashMap<String,Object> map = new HashMap<>();
                        map.put("email",user.getEmail());
                        map.put("name",user.getDisplayName());
                        map.put("profile","https://www.shutterstock.com/image-vector/young-smiling-man-avatar-brown-600nw-2261401207.jpg");
                        database.getReference().child("users").child(user.getEmail().replaceAll("[\\.#$\\[\\]]", "")).setValue(map);

                        SharedPreferences sharedPreferences = getContext().getSharedPreferences(PREFERENCE_FILE,Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email",user.getEmail());
                        editor.apply();
                        Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(), MasterActivity.class));
                        getActivity().finish();
                    }else {
                        Toast.makeText(getContext(),"Something wrong Try Again",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkBox() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(PREFERENCE_FILE,Context.MODE_PRIVATE);
        String check = sharedPreferences.getString("email","");
        if ((!check.equals("gust"))) {
            startActivity(new Intent(getContext(), MasterActivity.class));
            getActivity().finish();
        }
    }


    private void init(View view) {
        guest = view.findViewById(R.id.guestBtn);
        emailFiled = view.findViewById(R.id.email);
        passwordFiled = view.findViewById(R.id.password);
        login = view.findViewById(R.id.loginBtn);
        googleImage = view.findViewById(R.id.googleImage);
        mAuth = FirebaseAuth.getInstance();
        database =  FirebaseDatabase.getInstance();
    }
}