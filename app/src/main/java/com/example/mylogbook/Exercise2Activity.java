package com.example.mylogbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

public class Exercise2Activity extends AppCompatActivity {

    Button btnPrevious, btnNext;
    ImageView ivGym;

    private int[] imageIds = {
            R.drawable.gym1,
            R.drawable.gym2,
            R.drawable.gym3,
            R.drawable.gym4,
            R.drawable.gym5,
            R.drawable.gym6};
    private int currentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        defineLayout();
        btnClickListener();
        updateImageView();
    }

    private void defineLayout() {
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        ivGym = findViewById(R.id.ivGym);
    }

    private void btnClickListener() {

        btnPrevious.setOnClickListener(view -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateImageView();
            } else {
                currentIndex = imageIds.length - 1;
                updateImageView();
            }
        });

        btnNext.setOnClickListener(view -> {
            if (currentIndex < imageIds.length - 1) {
                currentIndex++;
                updateImageView();
            } else {
                currentIndex = 0;
                updateImageView();
            }
        });
    }

    private void updateImageView() {
        ivGym.setImageResource(imageIds[currentIndex]);
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
}