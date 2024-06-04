package com.example.movieapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private ImageView imageView;
    private EditText currentPass, newPass, confirmPass;
    private Button changeBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_password);

        imageView = findViewById(R.id.back);
        currentPass = findViewById(R.id.currentPass);
        newPass = findViewById(R.id.newPass);
        confirmPass = findViewById(R.id.confirmPass);
        changeBtn = findViewById(R.id.change);

        imageView.setOnClickListener(new View.OnClickListener() {
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
    }
}