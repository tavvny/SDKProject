package com.example.condencedgeek;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.condencedgeek.Model.Order;

public class CoursePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);

        ImageView courseImage = findViewById(R.id.imageView3);
        TextView courseTitle = findViewById(R.id.textView);
        TextView courseDesc = findViewById(R.id.Spisok);
        TextView price = findViewById(R.id.Price);
        TextView Main = findViewById(R.id.Main);


        courseImage.setImageResource(getIntent().getIntExtra("courseImage", 0));
        courseTitle.setText(getIntent().getStringExtra("CourseTitle"));
        courseDesc.setText(getIntent().getStringExtra("CourseDesc"));
        price.setText(getIntent().getStringExtra("CoursePrice"));


    }

    public void AddToCart(View view){
        int id = getIntent().getIntExtra("courseId", 0);
        Order.ItemsId.add(id);
        Toast.makeText(this, "Добавлено в корзину", Toast.LENGTH_LONG).show();
    }

    public void openCart(View view) {

        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);

    }

    public void openMain(View view) {

        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);

    }

    public void OpenOrders(View view){

        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);

    }
}