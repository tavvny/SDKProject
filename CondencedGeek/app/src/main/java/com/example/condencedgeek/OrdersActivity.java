package com.example.condencedgeek;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condencedgeek.Model.Items;
import com.example.condencedgeek.adapter.OrderAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<Items> orderList;
    private DatabaseReference ordersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);


        // Получение ссылки на базу данных "Orders"
        ordersRef = FirebaseDatabase.getInstance().getReference("Orders");

        // Инициализация RecyclerView
        recyclerView = findViewById(R.id.RecyclerOrders);
        recyclerView.setHasFixedSize(true);

        // Инициализация менеджера макета
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Инициализация списка заказов
        orderList = new ArrayList<>();

        // Инициализация адаптера
        orderAdapter = new OrderAdapter(orderList);
        recyclerView.setAdapter(orderAdapter);

        // Получение данных из Firebase Realtime Database
        fetchOrders();
    }

    private void fetchOrders() {


        // Создание слушателя для получения данных из базы данных
        ValueEventListener ordersListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Очистка списка заказов перед загрузкой новых данных
                orderList.clear();

                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                String currentUserId = currentUser.getUid();
                DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference("Orders").child("Users");
                String curUser = currentUserId;

                TextView textView = findViewById(R.id.curUID);
                textView.setText(curUser);

                // Обход всех дочерних узлов "Orders" и добавление их в список
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot orderSnapshot : userSnapshot.child(currentUserId).getChildren()) {
                        Items order = orderSnapshot.getValue(Items.class);
                        orderList.add(order);
                    }
                }

                // Обновление адаптера
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Обработка ошибок при чтении данных из базы данных
                Log.e("OrdersActivity", "Failed to fetch orders: " + databaseError.getMessage());
            }
        };

        // Добавление слушателя к базе данных "Orders"
        ordersRef.addListenerForSingleValueEvent(ordersListener);
    }
    public void openCart(View view) {

        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);

    }

    public void openMain(View view) {

        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);

    }
}
