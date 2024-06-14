package com.example.movieapp;

import static android.content.ContentValues.TAG;
import static com.example.movieapp.R.id.btnRegister;
import static com.example.movieapp.R.id.btnlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.dao.UserDao;
import com.example.movieapp.model.db.User;
import com.example.movieapp.services.UserService;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.List;

public class Login extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView forgotPassword;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.inputEmail);
        passwordEditText = findViewById(R.id.inputPassword);
        loginButton = findViewById(btnlogin);
        forgotPassword = findViewById(R.id.forgotPassword);
        TextView textViewSignUp = findViewById(R.id.textViewSignUp);
        forgotPassword.setOnClickListener(view ->{
            Intent intent = new Intent(Login.this, SendOTPActivity.class);
            startActivity(intent);
        });
        textViewSignUp.setOnClickListener(view -> {
                // Chuyển sang RegisterActivity
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
        });
        loginButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            String password = UserService.getInstance().hashPassword(passwordEditText.getText().toString().trim());
            loginUser(email, password);
        });
        // Lấy các tham chiếu đến các thành phần trong layout
        EditText inputPassword = findViewById(R.id.inputPassword);
        ImageButton passwordToggle = findViewById(R.id.passwordToggle);


        passwordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(inputPassword, passwordToggle);
            }
        });


    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Authentication failed", Toast.LENGTH_SHORT).show();
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
