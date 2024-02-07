package com.example.foodplanner.Home.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Models.Category;
import com.example.foodplanner.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.FriendsViewHolder> {

    private Context context;
    ArrayList<Category> categoryArrayList = new ArrayList<>();


    public CategoryAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public FriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_item, parent, false);
        FriendsViewHolder holder = new FriendsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsViewHolder holder, int position) {
        if(categoryArrayList !=null) {
            Category category = categoryArrayList.get(position);
            holder.categoryName.setText(category.getStrCategory());
            Glide.with(context).load(category.getStrCategoryThumb())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.categoryImage);
        }
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }


    class FriendsViewHolder extends RecyclerView.ViewHolder{
        ImageView categoryImage;
        TextView categoryName;
        public FriendsViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.categoryImage);
            categoryName = itemView.findViewById(R.id.categoryName);
        }
    }
    public void setDataSource(ArrayList<Category> categoryArrayList) {
        this.categoryArrayList = categoryArrayList;
        notifyDataSetChanged();
    }
}
