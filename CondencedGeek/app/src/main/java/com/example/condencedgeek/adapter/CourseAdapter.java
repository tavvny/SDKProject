package com.example.condencedgeek.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condencedgeek.CoursePage;
import com.example.condencedgeek.Model.Courses;
import com.example.condencedgeek.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    Context context;
    List<Courses> courses;

    public CourseAdapter(Context context, List<Courses> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseItems = LayoutInflater.from(context).inflate(R.layout.course_item, parent, false);
        return new CourseAdapter.CourseViewHolder(courseItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {

        int imageId = context.getResources().getIdentifier(courses.get(position).getImg(), "drawable", context.getPackageName());
        holder.courseImage.setImageResource(imageId);

        holder.CourseTitle.setText(courses.get(position).getTitle());
        holder.CourseDesc.setText(courses.get(position).getdescrip());
        holder.CoursePrice.setText(courses.get(position).getprice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CoursePage.class);

                intent.putExtra("courseImage", imageId);
                intent.putExtra("CourseTitle", courses.get(position).getTitle());
                intent.putExtra("CourseDesc",courses.get(position).getdescrip());
                intent.putExtra("CoursePrice", courses.get(position).getprice());
                intent.putExtra("courseText", courses.get(position).getText());
                intent.putExtra("courseId", courses.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static final class CourseViewHolder extends RecyclerView.ViewHolder{

        LinearLayout courseBG1;
        ImageView courseImage;
        TextView CourseTitle, CourseDesc, CoursePrice;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            courseBG1 = itemView.findViewById(R.id.courseBG1);
            courseImage = itemView.findViewById(R.id.courseimage);
            CourseTitle = itemView.findViewById(R.id.Kod);
            CourseDesc = itemView.findViewById(R.id.Spisok);
            CoursePrice = itemView.findViewById(R.id.price);
        }
    }

}
