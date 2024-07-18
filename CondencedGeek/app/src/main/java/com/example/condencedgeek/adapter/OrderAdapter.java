package com.example.condencedgeek.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condencedgeek.Model.Items;
import com.example.condencedgeek.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<Items> orderList;

    public OrderAdapter(List<Items> orderList) {
        this.orderList = orderList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderCheckData;
        TextView orderTtlPrice;
        TextView orderValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderCheckData = itemView.findViewById(R.id.CheckData);
            orderTtlPrice = itemView.findViewById(R.id.TtlPrice);
            orderValue = itemView.findViewById(R.id.Value);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Items order = orderList.get(position);
        holder.orderCheckData.setText(order.getCheckData());
        holder.orderTtlPrice.setText(String.valueOf(order.getTtlPrice()));
        holder.orderValue.setText(order.getValue());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
