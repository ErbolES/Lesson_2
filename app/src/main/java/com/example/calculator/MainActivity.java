package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private String input = "";
    private TextView resulText;

    private int firstVal;
    private int secondVal;
    private char currentOperation;
    private boolean isResultDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.calculator_main);
        resulText = findViewById(R.id.result);
        if (savedInstanceState != null) {
            input = savedInstanceState.getString("input", "");
            firstVal = savedInstanceState.getInt("firstVal", 0);
            secondVal = savedInstanceState.getInt("secondVal", 0);
            currentOperation = savedInstanceState.getChar("currentOperation", '\0');
            boolean isResultDisplayed = savedInstanceState.getBoolean("isResultDisplayed", false);
            if (isResultDisplayed) {
                resulText.setText(String.valueOf(firstVal));
                input = String.valueOf(firstVal);
            } else {
                resulText.setText(input.isEmpty() ? "0" : input);
            }
        }
        setNumberBtnListeners();
        setOperatorBtnListeners();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("input", input);
        outState.putInt("firstVal", firstVal);
        outState.putInt("secondVal", secondVal);
        outState.putChar("currentOperation", currentOperation);
        outState.putBoolean("isResultDisplayed", !input.isEmpty() ? false : true);
    }


    private void setNumberBtnListeners()
    {
        int[] numberBtnIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot

        };

        View.OnClickListener numberClickListeners = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resulText.getText().toString().equals(String.valueOf(firstVal))) {
                    input = "";
                }
                Button button = (Button)v;
                input+= button.getText().toString();
                resulText.setText(input);
            }
        };

        for (int id: numberBtnIds)
        {
            findViewById(id).setOnClickListener(numberClickListeners);
        }
    }

    private void setOperatorBtnListeners()
    {
        int[] operators = {
                R.id.btnAdd,
                R.id.btnSubtract,
                R.id.btnMultiply,
                R.id.btnDivide
        };

        View.OnClickListener operationClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!input.isEmpty())
                {
                    firstVal = Integer.parseInt(input);
                }
                else
                {
                    computeResult();
                }

                currentOperation = ((Button) v).getText().toString().charAt(0);
                input = "";
            }
        };

        for (int id : operators){
            findViewById(id).setOnClickListener(operationClickListener);
        }

        findViewById(R.id.btnEqual).setOnClickListener(view -> {
            computeResult();
            resulText.setText(String.valueOf(firstVal));
            input = String.valueOf(firstVal);
        });

        findViewById(R.id.btnClear).setOnClickListener(view -> {
            firstVal = 0;
            secondVal = 0;
            input = "";
            resulText.setText("0");
        });

        findViewById(R.id.btnBack).setOnClickListener(view -> {
            if (!input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
                resulText.setText(input.isEmpty() ? "0" : input);
            }
        });

    }

    private void computeResult() {
        if (!input.isEmpty()) {
            secondVal = Integer.parseInt(input);
            switch (currentOperation) {
                case '+': firstVal += secondVal; break;
                case '-': firstVal -= secondVal; break;
                case '*': firstVal *= secondVal; break;
                case '/': firstVal = (secondVal != 0) ? firstVal / secondVal : 0;
            }
            input = "";
        }
    }

}