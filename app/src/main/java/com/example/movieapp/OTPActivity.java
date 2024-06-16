package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class OTPActivity extends AppCompatActivity {

    private EditText etCode1, etCode2, etCode3, etCode4;
    private Button btnContinue;
    private FirebaseAuth mAuth;
    private String verificationId;
    private TextView tvDescription, tvResendCode;
    private CountDownTimer countDownTimer;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        mAuth = FirebaseAuth.getInstance();

        etCode1 = findViewById(R.id.etOtpDigit1);
        etCode2 = findViewById(R.id.etOtpDigit2);
        etCode3 = findViewById(R.id.etOtpDigit3);
        etCode4 = findViewById(R.id.etOtpDigit4);
        btnContinue = findViewById(R.id.btnContinue);
        tvDescription = findViewById(R.id.tvDescription);
        tvResendCode = findViewById(R.id.tvResendCode);

        verificationId = getIntent().getStringExtra("verificationId");
        userEmail = getIntent().getStringExtra("userEmail");

        tvDescription.setText("We've just sent you a 4-digit code to your Email: " + userEmail);

        btnContinue.setOnClickListener(v -> {
            String code = etCode1.getText().toString().trim() +
                    etCode2.getText().toString().trim() +
                    etCode3.getText().toString().trim() +
                    etCode4.getText().toString().trim();

            if (code.length() != 4) {
                Toast.makeText(OTPActivity.this, "Please enter valid code", Toast.LENGTH_SHORT).show();
                return;
            }

            verifyCode(code);
        });

        tvResendCode.setOnClickListener(v -> {
            resendVerificationCode(userEmail);
            startCountdownTimer();
        });
    }
//    private void verifyCode(String code) {
//        EmailAuthCredential credential = EmailAuthProvider.getCredential(userEmail, code);
//        signInWithCredential(credential);
//    }
    private void verifyCode(String code) {
        EmailAuthCredential credential = (EmailAuthCredential) EmailAuthProvider.getCredential(userEmail, code);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(OTPActivity.this, "Verification successful!", Toast.LENGTH_SHORT).show();
                            // Proceed to next activity or update UI as needed
                        } else {
                            // Sign in failed, display a message to the user.
                            Toast.makeText(OTPActivity.this, "Verification failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void signInWithCredential(EmailAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(OTPActivity.this, "Verification successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(OTPActivity.this, CreateNewPasswordActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(OTPActivity.this, "Verification failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

//    private void resendVerificationCode(String email) {
//        EmailAuthOptions options = EmailAuthOptions.newBuilder(mAuth)
//                .setEmail(email)
//                .setTimeout(60L, TimeUnit.SECONDS)
//                .setActivity(this)
//                .setCallbacks(new EmailAuthProvider.OnVerificationStateChangedCallbacks() {
//                    @Override
//                    public void onVerificationCompleted(EmailAuthCredential credential) {
//                        // Auto verification (not needed for manual resend)
//                    }
//
//                    @Override
//                    public void onVerificationFailed(FirebaseException e) {
//                        Toast.makeText(OTPActivity.this, "Failed to resend OTP: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onCodeSent(String newVerificationId, EmailAuthProvider.ForceResendingToken token) {
//                        super.onCodeSent(newVerificationId, token);
//                        verificationId = newVerificationId; // Update verification ID if necessary
//                        Toast.makeText(OTPActivity.this, "OTP Resent", Toast.LENGTH_SHORT).show();
//                    }
//                }).build();
//
//        EmailAuthProvider.verifyEmail(options);
//    }
private void resendVerificationCode(String email) {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    if (user != null) {
        user.sendEmailVerification()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(OTPActivity.this, "Verification email resent", Toast.LENGTH_SHORT).show();
                        // Optionally, update UI or provide feedback to the user
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(OTPActivity.this, "Failed to resend verification email: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    } else {
        Toast.makeText(OTPActivity.this, "User not signed in", Toast.LENGTH_SHORT).show();
        // Handle user not being signed in appropriately
    }
}


    private void startCountdownTimer() {
        tvResendCode.setEnabled(false);
        countDownTimer = new CountDownTimer(60000, 1000) { // 60 seconds countdown
            public void onTick(long millisUntilFinished) {
                tvResendCode.setText("Resend Code in " + millisUntilFinished / 1000 + "s");
            }
            public void onFinish() {
                tvResendCode.setText("Resend Code");
                tvResendCode.setEnabled(true);
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
