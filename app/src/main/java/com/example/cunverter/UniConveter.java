package com.example.cunverter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cunverter.databinding.ActivityUniConveterBinding;

public class UniConveter extends AppCompatActivity {

    private ActivityUniConveterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUniConveterBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        binding.spinner2.setSelection(1);


        binding.submit.setOnClickListener(view -> {

            String spin1 = binding.spinner1.getSelectedItem().toString();
            String spin2 = binding.spinner2.getSelectedItem().toString();

            String str = binding.num1.getText().toString();

            String result = binding.result.getText().toString();

            if (str.isEmpty()) {
                Toast.makeText(this, "Please enter any value", Toast.LENGTH_SHORT).show();
                return;
            }

            double num = Double.parseDouble(str);

            if (spin1.equals("millimeters") && spin2.equals("centimeters"))
                binding.result.setText("" + (num / 10));

            else if (spin1.equals("millimeters") && spin2.equals("meters"))
                binding.result.setText("" + (num / 1000));

            else if (spin1.equals("millimeters") && spin2.equals("kilometers"))
                binding.result.setText("" + (num / 1000000 ));

            else if (spin1.equals("centimeters") && spin2.equals("millimeters"))
                binding.result.setText("" + (num * 10));

            else if (spin1.equals("centimeters") && spin2.equals("meters"))
                binding.result.setText("" + (num / 100));

            else if (spin1.equals("centimeters") && spin2.equals("kilometers"))
                binding.result.setText("" + (num / 100000));

            else if (spin1.equals("meters") && spin2.equals("millimeters"))
                binding.result.setText("" + (num * 1000));

            else if (spin1.equals("meters") && spin2.equals("centimeters"))
                binding.result.setText("" + (num * 1000));

            else if (spin1.equals("meters") && spin2.equals("kilometers"))
                binding.result.setText("" + (num / 1000));

            else if (spin1.equals("kilometers") && spin2.equals("millimeters"))
                binding.result.setText("" + (num * 1000000));

            else if (spin1.equals("kilometers") && spin2.equals("centimeters"))
                binding.result.setText("" + (num * 100000));

            else if (spin1.equals("kilometers") && spin2.equals("meters"))
                binding.result.setText("" + (num * 1000));

            else {
                Toast.makeText(this, "Incorrect Conversion", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
