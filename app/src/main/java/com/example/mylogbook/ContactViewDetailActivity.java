package com.example.mylogbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;

import com.example.mylogbook.adapter.ContactAdapter;

import java.util.ArrayList;

public class ContactViewDetailActivity extends AppCompatActivity {

    RecyclerView rvContact;
    ContactAdapter contactAdapter;

    ArrayList<ContactModel> contactModels;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new DBHelper(this);

        rvContact = findViewById(R.id.rvContact);

        contactModels = dbHelper.getAllContacts();

        Log.i("TTG", contactModels.size() + "");
       setUpRecyclerView();



    }

    private void setUpRecyclerView() {
        contactAdapter = new ContactAdapter(contactModels);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager( this, 1);
        rvContact.setLayoutManager(layoutManager);
        rvContact.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(16), true));
        rvContact.setItemAnimator(new DefaultItemAnimator());
        rvContact.setAdapter(contactAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed(); // or perform any custom action
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}