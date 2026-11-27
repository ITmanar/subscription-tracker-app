package com.example.subscriptionapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "subscriptions.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "subscriptions";

    private static final String COL_ID = "id";
    private static final String COL_SERVICE_NAME = "service_name";
    private static final String COL_PRICE = "price";
    private static final String COL_BILLING_CYCLE = "billing_cycle";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_SERVICE_NAME + " TEXT NOT NULL, "
                + COL_PRICE + " REAL NOT NULL, "
                + COL_BILLING_CYCLE + " TEXT NOT NULL)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    // Add Subscription
    public boolean addSubscription(Subscription subscription) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_SERVICE_NAME, subscription.getServiceName());
        values.put(COL_PRICE, subscription.getPrice());
        values.put(COL_BILLING_CYCLE, subscription.getBillingCycle());

        long result = db.insert(TABLE_NAME, null, values);

        db.close();

        return result != -1;
    }

    // Insert Subscription
    public void insertSubscription(String serviceName,
                                   double price,
                                   String billingCycle) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_SERVICE_NAME, serviceName);
        values.put(COL_PRICE, price);
        values.put(COL_BILLING_CYCLE, billingCycle);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    // Get All Subscriptions
    public List<Subscription> getAllSubscriptions() {

        List<Subscription> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME,
                null
        );

        if (cursor.moveToFirst()) {

            do {

                Subscription s = new Subscription();

                s.setId(cursor.getInt(0));
                s.setServiceName(cursor.getString(1));
                s.setPrice(cursor.getDouble(2));
                s.setBillingCycle(cursor.getString(3));

                list.add(s);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }

    // Delete Subscription
    public boolean deleteSubscription(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(
                TABLE_NAME,
                COL_ID + "=?",
                new String[]{String.valueOf(id)}
        );

        db.close();

        return result > 0;
    }

    // Update Subscription
    public int updateSubscription(Subscription subscription) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_SERVICE_NAME, subscription.getServiceName());
        values.put(COL_PRICE, subscription.getPrice());
        values.put(COL_BILLING_CYCLE, subscription.getBillingCycle());

        return db.update(
                TABLE_NAME,
                values,
                COL_ID + "=?",
                new String[]{String.valueOf(subscription.getId())}
        );
    }
}