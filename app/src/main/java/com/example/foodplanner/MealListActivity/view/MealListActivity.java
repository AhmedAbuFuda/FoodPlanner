package com.example.foodplanner.MealListActivity.view;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.MealListActivity.Presenter.MealListPresenter;
import com.example.foodplanner.MealListActivity.Presenter.MealListPresenterImp;
import com.example.foodplanner.Models.Category;
import com.example.foodplanner.Models.Country;
import com.example.foodplanner.Models.Ingredient;
import com.example.foodplanner.Models.Meal;
import com.example.foodplanner.Network.MealRemoteDataSourceImp;
import com.example.foodplanner.R;
import com.example.foodplanner.Repository.MealRepositoryImp;
import com.example.foodplanner.db.MealLocalDataSourceImp;

import java.util.ArrayList;

public class MealListActivity extends AppCompatActivity implements MealListView{
    private ImageButton back;
    private TextView nameText;
    private RecyclerView mealRv;
    private ImageView mealImage;
    private String type;
    private MealListAdapter adapter;
    GridLayoutManager gridLayoutManager;
    private MealListPresenter presenter;
    private ArrayList<Meal> meals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_list);
        init();
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        presenter = new MealListPresenterImp(this, MealRepositoryImp.getInstance(MealRemoteDataSourceImp.getInstance(),
                MealLocalDataSourceImp.getInstance(this)));

        if (type.equals("Category")) {
            Category category = (Category) intent.getSerializableExtra("model");
            nameText.setText(category.getStrCategory());
            Glide.with(this).load(category.getStrCategoryThumb())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(mealImage);
            presenter.getFilteredMeals(category.getStrCategory(), 'c');
        }else if (type.equals("Ingredient")) {
            Ingredient ingredient = (Ingredient) intent.getSerializableExtra("model");
            nameText.setText(ingredient.getStrIngredient());
            Glide.with(this).load("https://www.themealdb.com/images/ingredients/"+ingredient.getStrIngredient().replaceAll(" ", "%20") + ".png")
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(mealImage);
            presenter.getFilteredMeals(ingredient.getStrIngredient(), 'i');
        }else if (type.equals("Country")) {
            Country country = (Country) intent.getSerializableExtra("model");
            String image = intent.getStringExtra("image");
            nameText.setText(country.getStrArea());
            Glide.with(this).load(image)
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(mealImage);
            presenter.getFilteredMeals(country.getStrArea(), 'a');
        }
    }

    public void init() {
        meals = new ArrayList<>();
        mealRv = findViewById(R.id.mealRV);
        nameText = findViewById(R.id.nameText);
        mealImage = findViewById(R.id.typeImage);
        back = findViewById(R.id.backButton);

        adapter = new MealListAdapter(this);
        gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mealRv.setLayoutManager(gridLayoutManager);
        mealRv.setAdapter(adapter);
    }


    @Override
    public void showMeals(ArrayList<Meal> meals) {
        adapter.setDataSource(meals);
    }

    @Override
    public void showErrMsg(String error) {

    }
}