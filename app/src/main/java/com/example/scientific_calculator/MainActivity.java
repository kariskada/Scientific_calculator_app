package com.example.scientific_calculator;





import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputField;
    TextView result;
    Button add, sub, mul, div, sin, cos, tan, sqrt, log, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputField = findViewById(R.id.inputField);
        result = findViewById(R.id.result);

        // Basic Operations
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        mul = findViewById(R.id.mul);
        div = findViewById(R.id.div);

        // Scientific Operations
        sin = findViewById(R.id.sin);
        cos = findViewById(R.id.cos);
        tan = findViewById(R.id.tan);
        sqrt = findViewById(R.id.sqrt);
        log = findViewById(R.id.log);
        clear = findViewById(R.id.clear);

        // Set Listeners
        add.setOnClickListener(v -> performTwoInputOperation("+"));
        sub.setOnClickListener(v -> performTwoInputOperation("-"));
        mul.setOnClickListener(v -> performTwoInputOperation("*"));
        div.setOnClickListener(v -> performTwoInputOperation("/"));
        sin.setOnClickListener(v -> performSingleInputOperation("sin"));
        cos.setOnClickListener(v -> performSingleInputOperation("cos"));
        tan.setOnClickListener(v -> performSingleInputOperation("tan"));
        sqrt.setOnClickListener(v -> performSingleInputOperation("sqrt"));
        log.setOnClickListener(v -> performSingleInputOperation("log"));
        clear.setOnClickListener(v -> clearFields());
    }

    private void performTwoInputOperation(String operator) {
        try {
            String input = inputField.getText().toString();
            String[] values = input.split(" ");
            if (values.length != 2) {
                result.setText("Error: Enter 2 numbers separated by a space");
                return;
            }

            double num1 = Double.parseDouble(values[0]);
            double num2 = Double.parseDouble(values[1]);
            double res = 0;

            switch (operator) {
                case "+":
                    res = num1 + num2;
                    break;
                case "-":
                    res = num1 - num2;
                    break;
                case "*":
                    res = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        result.setText("Error: Division by 0");
                        return;
                    }
                    res = num1 / num2;
                    break;
            }
            result.setText(String.valueOf(res));
        } catch (Exception e) {
            result.setText("Error");
        }
    }

    private void performSingleInputOperation(String operation) {
        try {
            String input = inputField.getText().toString();
            double num = Double.parseDouble(input);
            double res = 0;

            switch (operation) {
                case "sin":
                    res = Math.sin(Math.toRadians(num));
                    break;
                case "cos":
                    res = Math.cos(Math.toRadians(num));
                    break;
                case "tan":
                    res = Math.tan(Math.toRadians(num));
                    break;
                case "sqrt":
                    if (num < 0) {
                        result.setText("Error: Negative input for √");
                        return;
                    }
                    res = Math.sqrt(num);
                    break;
                case "log":
                    if (num <= 0) {
                        result.setText("Error: Non-positive input for log");
                        return;
                    }
                    res = Math.log10(num);
                    break;
            }
            result.setText(String.valueOf(res));
        } catch (Exception e) {
            result.setText("Error");
        }
    }

    private void clearFields() {
        inputField.setText("");
        result.setText("Result");
    }
}
