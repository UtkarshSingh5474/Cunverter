package com.example.cunverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cunverter.databinding.ActivityCalculatorBinding;
import com.example.cunverter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        binding.calculator.setOnClickListener(view -> {
            Intent intent = new Intent(this, Calculator.class);
            startActivity(intent);
        });

        binding.covidtracker.setOnClickListener(view -> {
            Intent intent = new Intent(this, CovidTracker.class);
            startActivity(intent);
        });


    }



}