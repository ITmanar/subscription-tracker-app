package com.example.subscriptionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SubscriptionAdapter adapter;
    private DatabaseHelper dbHelper;
    private TextView tvTotalMonthly, tvEmpty;
    private List<Subscription> subscriptionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        tvTotalMonthly = findViewById(R.id.tvTotalMonthly);
        tvEmpty = findViewById(R.id.tvEmpty);

        FloatingActionButton fab = findViewById(R.id.fabAddSubscription);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddSubscriptionActivity.class);
            startActivity(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadSubscriptions();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSubscriptions();
    }

    private void loadSubscriptions() {
        subscriptionList = dbHelper.getAllSubscriptions();

        if (subscriptionList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
        }

        adapter = new SubscriptionAdapter(
                subscriptionList,

                (subscription, position) -> {
                    dbHelper.deleteSubscription(subscription.getId());
                    adapter.removeItem(position);
                    updateTotal();

                    if (subscriptionList.isEmpty()) {
                        recyclerView.setVisibility(View.GONE);
                        tvEmpty.setVisibility(View.VISIBLE);
                    }
                },

                subscription -> {

                    Intent intent = new Intent(MainActivity.this, AddSubscriptionActivity.class);

                    intent.putExtra("id", subscription.getId());
                    intent.putExtra("name", subscription.getServiceName());
                    intent.putExtra("price", subscription.getPrice());
                    intent.putExtra("cycle", subscription.getBillingCycle());

                    startActivity(intent);
                }
        );

        recyclerView.setAdapter(adapter);
        updateTotal();
    }

    private void updateTotal() {
        double total = 0;
        for (Subscription s : subscriptionList) {
            total += s.getPrice();
        }
        tvTotalMonthly.setText(String.format("Total Monthly: SAR %.2f", total));
    }
}