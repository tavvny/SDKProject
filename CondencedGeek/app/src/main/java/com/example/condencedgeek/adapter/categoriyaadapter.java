package com.example.condencedgeek.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condencedgeek.MainActivity;
import com.example.condencedgeek.Model.Caterogiya;
import com.example.condencedgeek.R;

import java.util.List;

public class categoriyaadapter extends RecyclerView.Adapter<categoriyaadapter.CategoryViewHolder> {

    Context context;
    List<Caterogiya> categories;



    public categoriyaadapter(Context context, List<Caterogiya> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItems = LayoutInflater.from(context).inflate(R.layout.categoryitem, parent, false);
        return new CategoryViewHolder(categoryItems);
    }

    @Override
    public void onBindViewHolder(@NonNull categoriyaadapter.CategoryViewHolder holder, int position) {
        holder.CategoryText.setText(categories.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.showCoursesByCat(categories.get(position).getId());
            }
        });




    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView CategoryText;


        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            CategoryText = itemView.findViewById(R.id.CategoryText);
        }
    }

}
