package com.example.subscriptionapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.ViewHolder> {

    private List<Subscription> subscriptions;
    private OnDeleteClickListener deleteListener;
    private OnUpdateClickListener updateListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(Subscription subscription, int position);
    }

    public interface OnUpdateClickListener {
        void onUpdateClick(Subscription subscription);
    }
    public SubscriptionAdapter(List<Subscription> subscriptions,
                               OnDeleteClickListener deleteListener,
                               OnUpdateClickListener updateListener) {

        this.subscriptions = subscriptions;
        this.deleteListener = deleteListener;
        this.updateListener = updateListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subscription, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Subscription sub = subscriptions.get(position);

        holder.tvServiceName.setText(sub.getServiceName());
        holder.tvPrice.setText(String.format("SAR %.2f", sub.getPrice()));
        holder.tvBillingCycle.setText(sub.getBillingCycle());
        holder.btnDelete.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onDeleteClick(sub, holder.getAdapterPosition());
            }
        });

        holder.btnUpdate.setOnClickListener(v -> {
            if (updateListener != null) {
                updateListener.onUpdateClick(sub);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subscriptions.size();
    }

    public void removeItem(int position) {
        subscriptions.remove(position);
        notifyItemRemoved(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvServiceName, tvPrice, tvBillingCycle;
        Button btnDelete, btnUpdate;
        ViewHolder(View itemView) {
            super(itemView);

            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvBillingCycle = itemView.findViewById(R.id.tvBillingCycle);

            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }
    }
}