package com.example.mylogbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    Button btnExerciseOne, btnExerciseTwo, btnExerciseThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        defineLayout();
        btnClickListener();
    }

    private void defineLayout() {
        btnExerciseOne = findViewById(R.id.btnExerciseOne);
        btnExerciseTwo = findViewById(R.id.btnExerciseTwo);
        btnExerciseThree = findViewById(R.id.btnExerciseThree);
    }

    private void btnClickListener() {
        btnExerciseOne.setOnClickListener(view -> {
            Intent myIntent = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(myIntent);
        });

        btnExerciseTwo.setOnClickListener(view -> {
            Intent myIntent = new Intent(MainActivity2.this, Exercise2Activity.class);
            startActivity(myIntent);
        });

        btnExerciseThree.setOnClickListener(view -> {
            Intent myIntent = new Intent(MainActivity2.this, AddContactActivity.class);
            startActivity(myIntent);
        });
    }
}