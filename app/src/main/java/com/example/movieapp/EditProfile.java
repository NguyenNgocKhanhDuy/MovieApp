package com.example.movieapp;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class EditProfile extends AppCompatActivity {
    private ImageView imageView, avatar;
    private FirebaseAuth auth;
    private EditText username;
    private TextView email;
    private Button saveBtn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);

        imageView = findViewById(R.id.back);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        avatar = findViewById(R.id.avatar);
        saveBtn = findViewById(R.id.save);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Glide.with(EditProfile.this).load(getDrawable(R.drawable.ic_profile_image)).into(avatar);


        auth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        username.setText(user.getDisplayName());
        email.setText(user.getEmail());

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(username.getText().toString().trim())
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(task2 -> {
                            if (task2.isSuccessful()) {
                                Log.d("TAG", "Display name updated.");
                            } else {
                                Log.w("TAG", "Failed to update display name.", task2.getException());
                            }
                        });
                Toast.makeText(EditProfile.this, "Succesfull", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


//        Log.w(TAG, auth.getCurrentUser().getDisplayName());
//        Log.w(TAG, auth.getCurrentUser().getPhotoUrl()+"");


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}