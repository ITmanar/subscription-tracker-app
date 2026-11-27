package com.example.subscriptionapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddSubscriptionActivity extends AppCompatActivity {

    private EditText etServiceName, etPrice;
    private EditText etBillingCycle;
    private Button btnSave;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);

        etServiceName = findViewById(R.id.etServiceName);
        etPrice = findViewById(R.id.etPrice);
        etBillingCycle = findViewById(R.id.etBillingCycle);
        btnSave = findViewById(R.id.btnSave);
        dbHelper = new DatabaseHelper(this);


        String id = getIntent().getStringExtra("id");

        if (id != null) {

            etServiceName.setText(getIntent().getStringExtra("name"));

            etPrice.setText(String.valueOf(
                    getIntent().getDoubleExtra("price", 0)
            ));

            String cycle = getIntent().getStringExtra("cycle");

            etBillingCycle.setText(cycle);

            btnSave.setText("Update Subscription");
        }

        btnSave.setOnClickListener(v -> {

            String serviceName = etServiceName.getText().toString().trim();
            String priceText = etPrice.getText().toString().trim();
            String billingCycle = etBillingCycle.getText().toString().trim();

            if (serviceName.isEmpty() || priceText.isEmpty()) {

                Toast.makeText(
                        this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            double price = Double.parseDouble(priceText);

            if (id != null) {

                Subscription updatedSub = new Subscription(
                        Integer.parseInt(id),
                        serviceName,
                        price,
                        billingCycle
                );

                dbHelper.updateSubscription(updatedSub);

                Toast.makeText(
                        this,
                        "Subscription Updated",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                dbHelper.insertSubscription(
                        serviceName,
                        price,
                        billingCycle
                );

                Toast.makeText(
                        this,
                        "Subscription Added",
                        Toast.LENGTH_SHORT
                ).show();
            }

            finish();
        });
    }
}
