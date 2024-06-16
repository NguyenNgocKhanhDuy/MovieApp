package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.dao.UserDao;
import com.example.movieapp.model.db.User;
import com.example.movieapp.services.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Register extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText userNameEditText;
    private Button registerButton;
    private FirebaseAuth mAuth;
    private TextView singIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEditText = findViewById(R.id.inputEmail);
        passwordEditText = findViewById(R.id.inputPassword);
        userNameEditText = findViewById(R.id.inputUsername);
        registerButton = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
        singIn = findViewById(R.id.textViewSignIn);
        singIn.setOnClickListener(view ->{
        startActivity(new Intent(Register.this, Login.class));
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        EditText inputPassword = findViewById(R.id.inputPassword);
        ImageButton passwordToggle = findViewById(R.id.passwordToggle);
        EditText inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        ImageButton confirmPasswordToggle = findViewById(R.id.confirmPasswordToggle);


        passwordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(inputPassword, passwordToggle);
            }
        });


        confirmPasswordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(inputConfirmPassword, confirmPasswordToggle);
            }
        }
        );
    }

    private void registerUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String userName = userNameEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Email is required.");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Password is required.");
            return;
        }

        if (password.length() < 6) {
            passwordEditText.setError("Password must be at least 6 characters long.");
            return;
        }

        String hashPassword = UserService.getInstance().hashPassword(password);

        mAuth.createUserWithEmailAndPassword(email, hashPassword)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        User user2 = new User(userName, email, hashPassword, "");
                        UserDao.getInstance().insertUser(user2);
                        FirebaseUser userAfterInsert = FirebaseAuth.getInstance().getCurrentUser();
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(user2.getUsername())
                                .build();

                        userAfterInsert.updateProfile(profileUpdates)
                                .addOnCompleteListener(task2 -> {
                                    if (task2.isSuccessful()) {
                                        // Update succeeded
                                        Log.d("TAG", "Display name updated.");
                                    } else {
                                        // Update failed
                                        Log.w("TAG", "Failed to update display name.", task2.getException());
                                    }
                                });
                        //gui email xac nhan
                        user.sendEmailVerification().addOnCompleteListener(task3 -> {
                            if (task3.isSuccessful()) {
                                Toast.makeText(Register.this, "Registration successful. Please check your email for verification.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register.this, Login.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Log.e("TAG", "sendEmailVerification", task3.getException());
                                Toast.makeText(Register.this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(Register.this, "User with this email already exists.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Register.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean isPasswordVisible = false;
    // Hàm togglePasswordVisibility được sử dụng để thay đổi trạng thái của mật khẩu
    private void togglePasswordVisibility(EditText passwordField, ImageButton toggleButton) {
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
