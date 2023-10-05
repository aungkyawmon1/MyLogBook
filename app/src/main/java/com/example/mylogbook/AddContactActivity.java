package com.example.mylogbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mylogbook.bottomSheet.ContactPhotoBottomSheet;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddContactActivity extends AppCompatActivity implements ImageClickListener {

    Button btnChooseImage, btnViewDetail, btnSaveContact;
    EditText etName, etDateOfBirth, etEmail;
    ImageView ivContactPhoto;

    DBHelper dbHelper;

    private boolean isChoosePhoto = false;
    private int selectedPhoto = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new DBHelper(this);


        defineLayout();

        setOnClickListener();
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

    private void defineLayout() {
        ivContactPhoto = findViewById(R.id.ivContactPhoto);
        btnChooseImage = findViewById(R.id.btnChoosePhoto);
        btnSaveContact = findViewById(R.id.btnSaveContact);
        btnViewDetail = findViewById(R.id.btnViewDetail);

        etName = findViewById(R.id.etName);
        etDateOfBirth = findViewById(R.id.etDateOfBirth);
        etEmail = findViewById(R.id.etEmail);
    }

    private void setOnClickListener() {
        btnChooseImage.setOnClickListener(view -> {
            ContactPhotoBottomSheet bottomSheet = new ContactPhotoBottomSheet(this);
            bottomSheet.show(getSupportFragmentManager(),
                    "ModalBottomSheet");
        });

        btnSaveContact.setOnClickListener(view -> {
            saveContact();
        });

        btnViewDetail.setOnClickListener(view -> {
            Intent myIntent = new Intent(AddContactActivity.this, ContactViewDetailActivity.class);
            startActivity(myIntent);
        });
    }

    private void saveContact() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String dateOfBirth = etDateOfBirth.getText().toString();

        dbHelper.insertContact(name, email, selectedPhoto, dateOfBirth);
    }

    @Override
    public void clickAtImage(int position) {
        ivContactPhoto.setImageResource(Constant.CONTACT_PHOTOS[position]);
        selectedPhoto = Constant.CONTACT_PHOTOS[position];
        ivContactPhoto.setVisibility(View.VISIBLE);
    }

}