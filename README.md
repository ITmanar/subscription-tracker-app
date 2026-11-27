SubscriptionApp
===============

An Android application developed in Java using Android Studio and SQLite for tracking and managing recurring monthly subscriptions.

Overview
--------
SubscriptionApp provides a straightforward interface to record active subscriptions, monitor billing cycles, and view total monthly spending in SAR. All subscription records are stored locally on the device using SQLite.

Features
--------
- Add new subscriptions with service name, price in SAR, and billing cycle in days.
- View all saved subscriptions in a scrollable list using RecyclerView.
- Update existing subscription details such as price or billing cycle.
- Delete subscriptions that are no longer needed.
- Automatically calculate and display the total monthly cost at the top of the main screen.

Technologies Used
-----------------
- Java
- Android Studio
- XML Layouts
- RecyclerView
- SQLite (SQLiteOpenHelper)

Project Structure
-----------------
- `app/src/main/java/com/example/subscriptionapp/DatabaseHelper.java`: Handles SQLite database setup and CRUD operations (insert, read, update, delete).
- `app/src/main/java/com/example/subscriptionapp/AddSubscriptionActivity.java`: Manages the form screen for adding new subscriptions.
- `app/src/main/res/layout/`: Contains the XML layout files for the app screens and list items.

Future Improvements
-------------------
- Renewal reminder notifications before billing dates
- Dark mode support
- Cloud backup and synchronization
