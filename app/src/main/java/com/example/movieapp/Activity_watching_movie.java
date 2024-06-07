package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.flexbox.FlexboxLayout;

public class Activity_watching_movie extends AppCompatActivity {
    private FlexboxLayout flexboxLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watching_movie);

         flexboxLayout = findViewById(R.id.flexbox_button_container);

        // Số lượng Button cần tạo
        int numberOfButtons = 8;

        createButtons(numberOfButtons);
    }

    private void createButtons(int numberOfButtons) {
        for (int i = 0; i < numberOfButtons; i++) {
            Button button = new Button(this);

            // Thiết lập LayoutParams cho FlexboxLayout
            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            // Thiết lập margin
            params.setMargins(
                    (int) getResources().getDimension(R.dimen.margin_left), // Left margin
                    0, // Top margin
                    0, // Right margin
                    (int) getResources().getDimension(R.dimen.margin_bottom) // Bottom margin
            );

            // Thiết lập flexBasisPercent
            params.setFlexBasisPercent(0.25f); // 25%

            // Thiết lập LayoutParams cho Button
            button.setLayoutParams(params);

            // Thiết lập các thuộc tính khác cho Button
            button.setText("Tập " + (i + 1));
            button.setTextColor(getResources().getColor(R.color.background));
            button.setBackgroundResource(R.color.Item_checked);

            // Thiết lập sự kiện OnClickListener
            button.setOnClickListener(view -> {
                Button clickedButton = (Button) view;
                String buttonText = clickedButton.getText().toString();
            });

            // Thêm Button vào FlexboxLayout
            flexboxLayout.addView(button);
        }
    }
}