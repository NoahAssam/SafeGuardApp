package com.example.safeguard;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.safeguard.databinding.ActivitySignInBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use view binding to inflate the layout
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Set up sign-in button functionality
        binding.signInButton.setOnClickListener(v -> {
            String email = binding.emailEditText.getText().toString();
            String password = binding.passwordEditText.getText().toString();
            signInWithEmail(email, password);
        });

        // Set up registration button functionality
        binding.registerButton.setOnClickListener(v -> {
            String email = binding.emailEditText.getText().toString();
            String password = binding.passwordEditText.getText().toString();
            registerWithEmail(email, password);
        });
    }

    // Function to handle Firebase Email/Password Sign-In
    private void signInWithEmail(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(SignInActivity.this, "Email or Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign-in successful
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(SignInActivity.this, "Sign-In Successful", Toast.LENGTH_SHORT).show();
                        // TODO: Navigate to the next activity (Home screen, for example)
                    } else {
                        // Sign-in failed
                        Toast.makeText(SignInActivity.this, "Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    // Function to handle Firebase Email/Password Registration
    private void registerWithEmail(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(SignInActivity.this, "Email or Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Registration successful
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(SignInActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        // TODO: Navigate to the next activity (Home screen, for example)
                    } else {
                        // Registration failed
                        Toast.makeText(SignInActivity.this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // User is signed in, navigate to the next activity
            Toast.makeText(SignInActivity.this, "Already Signed In", Toast.LENGTH_SHORT).show();
            // TODO: Navigate to the next activity (Home screen, for example)
        }
    }
}
