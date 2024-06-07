package com.example.movieapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.movieapp.services.UserService;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {
    private ImageView back;
    private EditText currentPass, newPass, confirmPass;
    private Button changeBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_password);

        back = findViewById(R.id.back);
        currentPass = findViewById(R.id.currentPass);
        newPass = findViewById(R.id.newPass);
        confirmPass = findViewById(R.id.confirmPass);
        changeBtn = findViewById(R.id.change);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newP = newPass.getText().toString().trim();
                String confirmP = confirmPass.getText().toString().trim();
                String currentP = currentPass.getText().toString().trim();
                if (newP.equals(confirmP)) {
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    FirebaseUser user = auth.getCurrentUser();
                    String hashPassOld = UserService.getInstance().hashPassword(currentP);
                    String hashPassNew = UserService.getInstance().hashPassword(newP);

                    auth.signInWithEmailAndPassword(user.getEmail(), hashPassOld)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    user.updatePassword(hashPassNew).addOnCompleteListener(task2 -> {
                                        if (task.isSuccessful()) {
                                            Log.d("TAG", "Password updated successfully.");
                                            Toast.makeText(ChangePassword.this, "Successfully", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Log.w("TAG", "Password update failed.", task2.getException());
                                            Toast.makeText(ChangePassword.this, "ERROR", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    });
                                } else {
                                    Toast.makeText(ChangePassword.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            });
                }else {
                    Toast.makeText(ChangePassword.this, "Password not match", Toast.LENGTH_SHORT).show();
                }
            }
        });


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        EditText currentPass= findViewById(R.id.currentPass);
        ImageView currentToggle = findViewById(R.id.passwordToggle);
        EditText newPass= findViewById(R.id.newPass);
        ImageView newToggle = findViewById(R.id.newPasswordToggle);
        EditText confirmPass= findViewById(R.id.confirmPass);
        ImageView confirmToggle = findViewById(R.id.confirmPasswordToggle);

        currentToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(currentPass, currentToggle);
            }
        });
        newToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(newPass, newToggle);
            }
        });


        confirmToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(confirmPass, confirmToggle);
            }
        }
        );
    }
    private boolean isPasswordVisible = false;
    // Hàm togglePasswordVisibility được sử dụng để thay đổi trạng thái của mật khẩu
    private void togglePasswordVisibility(EditText passwordField, ImageView toggleButton) {
        if (isPasswordVisible) {
            // Nếu mật khẩu đang hiển thị, ẩn nó lại
            passwordField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            toggleButton.setImageResource(R.drawable.ic_visibility);
        } else {
            // Nếu mật khẩu đang ẩn, hiển thị nó
            passwordField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            toggleButton.setImageResource(R.drawable.ic_visibility_off);
        }

        // Cập nhật trạng thái của mật khẩu
        isPasswordVisible = !isPasswordVisible;

        // Di chuyển con trỏ đến cuối
        passwordField.setSelection(passwordField.getText().length());
    }
}