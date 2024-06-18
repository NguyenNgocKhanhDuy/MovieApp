package com.example.movieapp;

import static com.example.movieapp.R.id.btn_not_now;
import static com.example.movieapp.R.id.btnlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.dao.MyDataBaseHelper;
import com.example.movieapp.services.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton, notnowButton;
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
        notnowButton = findViewById(btn_not_now);
        forgotPassword = findViewById(R.id.forgotPassword);
        TextView textViewSignUp = findViewById(R.id.textViewSignUp);


        forgotPassword.setOnClickListener(view ->{
            Intent intent = new Intent(Login.this, SendVerifyActivity.class);
            startActivity(intent);
        });
        textViewSignUp.setOnClickListener(view -> {
                // Chuyển sang RegisterActivity
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
        });
        notnowButton.setOnClickListener(view -> {
            loginGuest();
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

        boolean isLogin = false;
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null && user.isEmailVerified()) {
                            Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                            bundle.putBoolean("isLogin", true);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        } else {
//                            auth.signOut();
                            Toast.makeText(Login.this, "Please verify your email first.", Toast.LENGTH_SHORT).show();
                            intent = new Intent(Login.this, VerifyActivity.class);
                            bundle.putString("email",email);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(Login.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        bundle.putBoolean("isLogin",false);
                    }

                });
    }
    private void loginGuest(){
        Bundle bundle = new Bundle();
        Intent intent = new Intent(Login.this, MainActivity.class);
        bundle.putBoolean("isLogin",false);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
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
