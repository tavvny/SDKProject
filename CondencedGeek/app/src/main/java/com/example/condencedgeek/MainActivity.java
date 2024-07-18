package com.example.condencedgeek;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condencedgeek.Model.Caterogiya;
import com.example.condencedgeek.Model.Courses;
import com.example.condencedgeek.adapter.CourseAdapter;
import com.example.condencedgeek.adapter.categoriyaadapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, CourseRecycler;
    categoriyaadapter CategoryAdapter;
    static CourseAdapter courseAdapter;
    static List<Courses> coursesList = new ArrayList<>();
    static List<Courses> fullcoursesList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Caterogiya> categoryList = new ArrayList<>();
        categoryList.add(new Caterogiya(1, "Сборки"));
        categoryList.add(new Caterogiya(2, "Видеокарты"));
        categoryList.add(new Caterogiya(3, "Процессоры"));
        categoryList.add(new Caterogiya(4, "Перефирия"));
        categoryList.add(new Caterogiya(5, "Прочее"));

        setCategoryRecycler(categoryList);


        coursesList.add(new Courses(1, "komp", "HYPERPC LUMEN", "Компьютер HYPERPC LUMEN — это синоним надежности, потому что при его производстве используются только проверенные и тщательно подобранные компоненты от известных мировых брендов. Мы годами выводили формулу идеального компьютера и по итогу смогли учесть все ключевые моменты.","87000", "TestText", 1 ));
        coursesList.add(new Courses(2, "proc", "proc", "adsasda","30000" , "TestText", 3));
        coursesList.add(new Courses(3, "vidahu", "vidyaha", "shik","130000", "TestText", 2 ));
        coursesList.add(new Courses(4, "proc", "proc", "adsasda","30000" , "TestText",3));
        coursesList.add(new Courses(5, "komp", "pc", "adsasda","30000", "TestText", 1 ));
        coursesList.add(new Courses(6, "proc", "proc", "adsasda","30000" , "TestText", 3));

        fullcoursesList.addAll(coursesList);

        setCourseRecycler(coursesList);
    }


    public void openCart(View view) {

        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);

    }

    public void OpenOrders(View view){

        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);

    }



    private void setCourseRecycler(List<Courses> coursesList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        CourseRecycler = findViewById(R.id.ItemRecycler);
        CourseRecycler.setLayoutManager(layoutManager);
        courseAdapter = new CourseAdapter(this, coursesList);
        CourseRecycler.setAdapter(courseAdapter);

    }


    private void setCategoryRecycler(List<Caterogiya> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);
        CategoryAdapter = new categoriyaadapter(this, categoryList);
        categoryRecycler.setAdapter(CategoryAdapter);
    }

    public static void showCoursesByCat (int Category){

        coursesList.clear();
        coursesList.addAll(fullcoursesList);

        List<Courses> filterCourses = new ArrayList<>();
        for(Courses c: coursesList){
            if(c.getCategory() == Category)
                filterCourses.add(c);
        }

        coursesList.clear();
        coursesList.addAll(filterCourses);

        courseAdapter.notifyDataSetChanged();

    }

}