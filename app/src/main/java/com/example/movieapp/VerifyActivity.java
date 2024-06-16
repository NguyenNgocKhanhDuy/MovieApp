    package com.example.movieapp;

    import android.os.Bundle;
    import android.os.CountDownTimer;
    import android.widget.Button;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.appcompat.app.AppCompatActivity;

    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.auth.FirebaseUser;

    public class VerifyActivity extends AppCompatActivity {

        private Button btnContinue;
        private FirebaseAuth mAuth;
        private TextView tvDescription, tvResendEmail;
        private CountDownTimer countDownTimer;
        private String userEmail;
        private boolean isForgotPassword;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_otp);

            mAuth = FirebaseAuth.getInstance();

            btnContinue = findViewById(R.id.btnContinue);
            tvDescription = findViewById(R.id.tvDescription);
            tvResendEmail = findViewById(R.id.tvResendEmail);

            userEmail = getIntent().getStringExtra("email");
            isForgotPassword = getIntent().getBooleanExtra("forgot",false);
            tvDescription.setText("We've just sent you a verification to your Email: " + userEmail);
            checkEmailVerificationStatus();
            tvResendEmail.setOnClickListener(v -> {
                checkEmailVerificationStatus();
                startCountdownTimer();
            });
            btnContinue.setOnClickListener(view -> {
                finish();
            });
        }

        private void checkEmailVerificationStatus() {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            if (user != null) {
                user.reload().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        if (isForgotPassword) {
                            sendPasswordResetEmail(user.getEmail());
                        } else if(!user.isEmailVerified()){
                            resendVerificationEmail(user);
                        }
                    } else {
                        Toast.makeText(VerifyActivity.this, "Failed to check email verification status", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(VerifyActivity.this, "User not signed in or email mismatch", Toast.LENGTH_SHORT).show();
            }
        }
        private void sendPasswordResetEmail(String email) {
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(VerifyActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(VerifyActivity.this, "Failed to send password reset email", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        private void resendVerificationEmail(FirebaseUser user) {
            user.sendEmailVerification()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(VerifyActivity.this, "Verification email resent", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(VerifyActivity.this, "Failed to resend verification email: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }

        private void startCountdownTimer() {
            tvResendEmail.setEnabled(false);
            countDownTimer = new CountDownTimer(60000, 1000) {
                public void onTick(long millisUntilFinished) {
                    tvResendEmail.setText("Resend Email in " + millisUntilFinished / 1000 + "s");
                }
                public void onFinish() {
                    tvResendEmail.setText("Resend Email");
                    tvResendEmail.setEnabled(true);
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
