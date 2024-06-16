package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SendVerifyActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button btnSendResetEmail;
    private FirebaseAuth mAuth;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etEmail = findViewById(R.id.email_input);
        btnSendResetEmail = findViewById(R.id.continue_button);
        mAuth = FirebaseAuth.getInstance();

        btnSendResetEmail.setOnClickListener(v -> {
            userEmail = etEmail.getText().toString().trim();
            if (userEmail.isEmpty()) {
                etEmail.setError("Email is required!");
                etEmail.requestFocus();
                return;
            }

            sendResetPasswordEmail(userEmail);
        });
    }

    private void sendResetPasswordEmail(String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SendVerifyActivity.this, "Reset email sent!", Toast.LENGTH_SHORT).show();
                            onCodeSent(userEmail); // Gọi hàm onCodeSent khi gửi email thành công
                        } else {
                            // Xử lý các ngoại lệ Firebase Auth
                            Exception exception = task.getException();
                            if (exception instanceof FirebaseAuthInvalidUserException) {
                                Toast.makeText(SendVerifyActivity.this, "Email address not found", Toast.LENGTH_SHORT).show();
                            } else if (exception instanceof FirebaseAuthRecentLoginRequiredException) {
                                Toast.makeText(SendVerifyActivity.this, "Recent login is required", Toast.LENGTH_SHORT).show();
                            } else if (exception instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(SendVerifyActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SendVerifyActivity.this, "Failed to send reset email. " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void onCodeSent(String email) {
        Intent intent = new Intent(SendVerifyActivity.this, VerifyActivity.class);
        intent.putExtra("email", email);
        intent.putExtra("forgot",true);
        startActivity(intent);
        finish();
    }
}
