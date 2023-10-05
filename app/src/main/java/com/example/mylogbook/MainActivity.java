package com.example.mylogbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine;
    Button btnClear, btnEqual, btnAddition, btnSubtraction, btnMultiplication, btnDivision;
    TextView tvInput;

    private int firstNumber = 0, secondNumber = 0, lastNumber = 0, midAnswer = 0;

    private boolean isTypingNumber = false;

    private  char operatorTapped;

    private  boolean isOperatorTapped = false;

    private  char previousOperator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        defineLayout();
        btnClickListener();
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
        btnZero = findViewById(R.id.btnZero);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnClear = findViewById(R.id.btnClear);
        btnEqual = findViewById(R.id.btnEqual);
        btnAddition = findViewById(R.id.btnAddition);
        btnSubtraction = findViewById(R.id.btnSubtraction);
        btnMultiplication = findViewById(R.id.btnMultiplication);
        btnDivision = findViewById(R.id.btnDivision);
        tvInput = findViewById(R.id.tvInputView);
    }

    private void setNumber(int number) {
        lastNumber = lastNumber * 10 + number;
        tvInput.setText(""+lastNumber);

    }

    private void btnClickListener() {

        btnZero.setOnClickListener(view -> {
           setNumber(0);
        });

        btnOne.setOnClickListener(view -> {
           setNumber(1);
        });

        btnTwo.setOnClickListener(view -> {
            setNumber(2);
        });

        btnThree.setOnClickListener(view -> {
            setNumber(3);
        });

        btnFour.setOnClickListener(view -> {
            setNumber(4);
        });

        btnFive.setOnClickListener(view -> {
            setNumber(5);
        });

        btnSix.setOnClickListener(view -> {
            setNumber(6);
        });

        btnSeven.setOnClickListener(view -> {
            setNumber(7);
        });

        btnEight.setOnClickListener(view -> {
            setNumber(8);
        });

        btnNine.setOnClickListener(view -> {
            setNumber(9);
        });

        btnClear.setOnClickListener(view -> {
            tvInput.setText("0");
            firstNumber = 0;
            secondNumber = 0;
            lastNumber = 0;
            isTypingNumber = false;
            isOperatorTapped = false;
        });

        btnEqual.setOnClickListener(view -> {
            int result = 0;

            secondNumber = lastNumber;

            if(operatorTapped == '+'){

                result = firstNumber + secondNumber;
                tvInput.setText(""+result);
            }
            else if(operatorTapped == '-'){
                result = firstNumber - secondNumber;
                tvInput.setText(""+result);
            }
            else if(operatorTapped == '*'){
                result = firstNumber * secondNumber;
                tvInput.setText(""+result);
            }
            else if(operatorTapped == '/'){
                if (lastNumber == 0) {
                    Toast.makeText(this, "Invalid Action!", Toast.LENGTH_LONG).show();
                }else{
                    result = firstNumber / secondNumber;
                    tvInput.setText(""+result);
                }
            }
        });

        btnAddition.setOnClickListener(view -> {
           calculate('+');
        });

        btnSubtraction.setOnClickListener(view -> {
            calculate('-');
        });

        btnMultiplication.setOnClickListener(view -> {
            calculate('*');
        });

        btnDivision.setOnClickListener(view -> {
            calculate('/');
        });

    }

    private void calculate(char operator) {
        if(!isOperatorTapped){
            firstNumber = lastNumber;
            lastNumber = 0;
            isTypingNumber = true;
            isOperatorTapped = true;
            operatorTapped = operator;
            previousOperator = operator;

        }
        else {
            if (previousOperator == '+') {
                midAnswer = firstNumber + lastNumber;
            } else if (previousOperator == '-') {
                midAnswer = firstNumber - lastNumber;
            } else if (previousOperator == '*') {
                midAnswer = firstNumber * lastNumber;
            } else if (previousOperator == '/') {
                if (lastNumber == 0) {
                    Toast.makeText(this, "Invalid Action!", Toast.LENGTH_LONG).show();
                } else {
                    midAnswer = firstNumber / lastNumber;
                }
            }

            firstNumber = midAnswer;
            previousOperator = operator;
            operatorTapped = operator;
            tvInput.setText("" + lastNumber);
            lastNumber = 0;
        }

    }
}