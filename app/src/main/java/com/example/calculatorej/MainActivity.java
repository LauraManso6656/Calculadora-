package com.example.calculatorej;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0;
    private double secondNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        // Configurar botones
        Button buttonDIV = findViewById(R.id.buttonDIV);
        Button buttonMULT = findViewById(R.id.buttonMULT);
        Button buttonMinus = findViewById(R.id.buttonMINUS);
        Button buttonADD = findViewById(R.id.buttonADD);
        Button buttonEQUAL = findViewById(R.id.buttonEQUAL);
        Button buttonPORCENTAJE = findViewById(R.id.buttonPORCENTAJE);
        Button buttonDELETE = findViewById(R.id.buttonDEL);
        Button buttonCOMA = findViewById(R.id.buttonCOMA);

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);

        // Acciones de los botones
        buttonDELETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput = "";
                operator = "";
                firstNumber = 0;
                secondNumber = 0;
                textView.setText("");
            }
        });

        buttonDIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("÷");
            }
        });

        buttonMULT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("x");
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("-");
            }
        });

        buttonADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("+");
            }
        });

        buttonEQUAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty() && !operator.isEmpty()) {
                    secondNumber = Double.parseDouble(currentInput);

                    double result = calculateResult();
                    textView.setText(String.valueOf(result));

                    // Reset para permitir más cálculos
                    firstNumber = result;
                    currentInput = "";
                    operator = "";
                } else {
                    textView.setText("Error");
                }
            }
        });

        buttonCOMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.contains(".")) {
                    currentInput += ".";
                    textView.setText(currentInput);
                }
            }
        });

        // Números
        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                currentInput += button.getText().toString();
                textView.setText(currentInput);
            }
        };

        button0.setOnClickListener(numberListener);
        button1.setOnClickListener(numberListener);
        button2.setOnClickListener(numberListener);
        button3.setOnClickListener(numberListener);
        button4.setOnClickListener(numberListener);
        button5.setOnClickListener(numberListener);
        button6.setOnClickListener(numberListener);
        button7.setOnClickListener(numberListener);
        button8.setOnClickListener(numberListener);
        button9.setOnClickListener(numberListener);
    }

    private void handleOperator(String op) {
        if (!currentInput.isEmpty()) {
            firstNumber = Double.parseDouble(currentInput);
            operator = op;
            currentInput = "";
            textView.setText(op);
        } else {
            textView.setText("Error");
        }
    }

    private double calculateResult() {
        switch (operator) {
            case "÷":
                if (secondNumber != 0) {
                    return firstNumber / secondNumber;
                } else {
                    textView.setText("Error");
                    return 0;
                }
            case "x":
                return firstNumber * secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "+":
                return firstNumber + secondNumber;
            case "%":
                return firstNumber % secondNumber;
            default:
                return 0;
        }
    }
}
