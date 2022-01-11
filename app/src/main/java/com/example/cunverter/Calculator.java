package com.example.cunverter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.cunverter.databinding.ActivityCalculatorBinding;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Calculator extends AppCompatActivity {

    private ActivityCalculatorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalculatorBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        binding.toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        binding.toolbar.setNavigationOnClickListener(view -> {
            onBackPressed();
        });

        View.OnClickListener listener = view -> {
            String result = binding.result.getText().toString();

            if (result.equals("00.00")) {
                binding.result.setText("");
            }


            Button button = (Button) view;
            String value = button.getText().toString();
            if (value.equals("X")) {
                value = "*";
            }
            binding.result.append(value);

        };
        binding.equals.setOnClickListener(view -> {

            String result = binding.result.getText().toString();
            try {
                Expression e = new ExpressionBuilder(result).build();
                binding.result.setText("" + e.evaluate());
            } catch (Exception exc){
                Toast.makeText(this, "Invalid Operation", Toast.LENGTH_SHORT).show();
                binding.result.setText("00.00");
            }
        });

        binding.delete.setOnClickListener(view -> {

            String result = binding.result.getText().toString();
            int length = result.length();
            try {
                binding.result.setText(result.substring(0, length - 1));
            } catch (IndexOutOfBoundsException exc) {
                binding.result.setText("00.00");
            }


        });

        binding.ac.setOnClickListener(view -> binding.result.setText("00.00"));

        binding.num0.setOnClickListener(listener);
        binding.num1.setOnClickListener(listener);
        binding.num2.setOnClickListener(listener);
        binding.num3.setOnClickListener(listener);
        binding.num4.setOnClickListener(listener);
        binding.num5.setOnClickListener(listener);
        binding.num6.setOnClickListener(listener);
        binding.num7.setOnClickListener(listener);
        binding.num8.setOnClickListener(listener);
        binding.num9.setOnClickListener(listener);
        binding.point.setOnClickListener(listener);
        binding.add.setOnClickListener(listener);
        binding.sub.setOnClickListener(listener);
        binding.mutiply.setOnClickListener(listener);
        binding.divide.setOnClickListener(listener);
        binding.perc.setOnClickListener(listener);
    }


}