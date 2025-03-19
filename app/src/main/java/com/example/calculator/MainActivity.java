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

    private double firstVal;
    private double secondVal;
    private char currentOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.calculator_main);
        resulText = findViewById(R.id.result);
        setNumberBtnListeners();

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
                    firstVal = Double.parseDouble(input);
                }
                else
                {
                    secondVal = Double.parseDouble(input);
                    switch (currentOperation){
                        case '+': firstVal += secondVal; break;
                        case '-': firstVal -= secondVal; break;
                        case '*': firstVal *= secondVal; break;
                        case '/': firstVal = (secondVal != 0) ? firstVal / secondVal : 0;
                    }
                    input = "";
                }

                currentOperation = ((Button) v).getText().toString().charAt(0);
                input = "";
            }
        };

        for (int id : operators){
            findViewById(id).setOnClickListener(operationClickListener);
        }

        
    }

}