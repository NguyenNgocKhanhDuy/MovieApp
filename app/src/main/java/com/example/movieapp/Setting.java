package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Setting extends AppCompatActivity {
//    final int EDIT_PROFILE_ID = R.id.edit_profile;
//    final int CHANGE_PASSWORD_ID = R.id.change_password;
//    final int CHANGE_LANGUAGE_ID = R.id.language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting);

        // Tìm và gán onClickListener cho nút "OpenOtherActivity"
        View editProfile = findViewById(R.id.edit_profile);
        View changePassword = findViewById(R.id.change_password);
        View notification = findViewById(R.id.notify);
        View security = findViewById(R.id.security);
        View language = findViewById(R.id.language);
        View legal = findViewById(R.id.legal);
        View help = findViewById(R.id.help);
        View logout = findViewById(R.id.logout);
        editProfile.setOnClickListener(View ->{
            startActivity(new Intent(Setting.this, EditProfile.class));
        });
        changePassword.setOnClickListener(View ->{
            startActivity(new Intent(Setting.this, ChangePassword.class));
        });
        language.setOnClickListener(View ->{
            startActivity(new Intent(Setting.this, ChangeLanguage.class));
        });
//        View.OnClickListener onClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Sử dụng switch-case để xác định phần tử được nhấn
//                Intent intent; // Đặt biến intent ở đây
//                switch (v.getId()) {
//                    case EDIT_PROFILE_ID:
//                        intent = new Intent(Setting.this, EditProfile.class); // Sử dụng lại biến intent
//                        startActivity(intent);
//                        break;
//                    case CHANGE_PASSWORD_ID:
//                        intent = new Intent(Setting.this, ChangePassword.class); // Sử dụng lại biến intent
//                        startActivity(intent);
//                        break;
//                    case R.id.security:
//                        break;
//                    case CHANGE_LANGUAGE_ID:
//                        intent = new Intent(Setting.this, ChangeLanguage.class); // Sử dụng lại biến intent
//                        startActivity(intent);
//                        break;
//                }
//            }
//        };

    };
//        private void openActivity(Class<?> activityClass) {
//            Intent intent = new Intent(Setting.this, activityClass);
//            startActivity(intent);
//        }




        // Code tương tự có thể được thêm cho các phần tử khác trong Setting Activity
}