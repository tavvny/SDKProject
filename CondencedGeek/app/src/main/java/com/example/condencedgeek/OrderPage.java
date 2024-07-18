package com.example.condencedgeek;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.condencedgeek.Model.Courses;
import com.example.condencedgeek.Model.Order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

        //jqytoyssbrosquwh
public class OrderPage extends AppCompatActivity {

Button ordrButton;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        ordrButton = findViewById(R.id.Trash);

        SharedPreferences Prefo = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String value = Prefo.getString("pochta", "");

        ListView ordersList = findViewById(R.id.orders_list);

        List<String> coursesTitle = new ArrayList<>();
        for(Courses c : MainActivity.fullcoursesList) {
            if (Order.ItemsId.contains(c.getId()))
                coursesTitle.add(c.getTitle());
        }
        ordersList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, coursesTitle));

        List<String> coursesPrice = new ArrayList<>();
        for (Courses c: MainActivity.fullcoursesList) {
            if (Order.ItemsId.contains(c.getId()))
                coursesPrice.add(c.getprice());
        }

        final double[] totalPrice = {0.0};
        for (String price : coursesPrice) {
            double coursePrice = Double.parseDouble(price);
            totalPrice[0] += coursePrice;
        }
        TextView cenaTextView = findViewById(R.id.Cena);
        cenaTextView.setText(String.valueOf(totalPrice[0]));
        double ttlPrice = totalPrice[0];

    //Список покупок
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : coursesTitle ) {
            stringBuilder.append(item).append("\n");
        }
        String checkData = stringBuilder.toString();
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("checkData", checkData);
        editor.apply();




        ordrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if (coursesTitle.isEmpty()) {
                    Toast.makeText(OrderPage.this, "Корзина пуста", Toast.LENGTH_SHORT).show();
                }
                else {
                    OrdrPage order = new OrdrPage(value, checkData, ttlPrice);

                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    FirebaseUser currentUser = auth.getCurrentUser();
                    String userId = currentUser.getUid();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();

                    DatabaseReference ordersRef = database.getReference("Orders");

                    String orderId = ordersRef.push().getKey(); // Генерируйте уникальный идентификатор заказа
                    ordersRef.child("User").child(userId).child(orderId).setValue(order);




                    coursesTitle.clear();
                    ArrayAdapter<String> adapter = (ArrayAdapter<String>) ordersList.getAdapter();
                    adapter.notifyDataSetChanged();
                    totalPrice[0] = 0.0;
                    cenaTextView.setText(String.valueOf(totalPrice[0]));


                }




            }
        });



}
            public void openCart(View view) {

                Intent intent = new Intent(this, OrderPage.class);
                startActivity(intent);

            }

            public void OpenOrders(View view){

                Intent intent = new Intent(this, OrdersActivity.class);
                startActivity(intent);

            }

            public void openMain(View view){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
}
