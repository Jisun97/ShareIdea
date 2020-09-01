package com.example.shareidea;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.shareidea.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setEmail("");
        binding.setPw("");

        getWindow().setStatusBarColor(Color.parseColor("#fbfbfb"));

        binding.btnLoginSignin.setOnClickListener(view -> {
            login(binding.getEmail(), binding.getPw());
        });

        binding.btnLoginSignup.setOnClickListener(view ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

    }

    private void login(String email, String pw) {

        if (email.isEmpty() || pw.isEmpty()) {
            Toast.makeText(this, "빈칸을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseFirestore
                .collection("users")
                .document(email)
                .get()
                .addOnSuccessListener(document -> {
                    firebaseAuth
                            .signInWithEmailAndPassword(email, pw)
                            .addOnSuccessListener(runnable1 -> {
                                UserCache.setUser(this, document.toObject(UserModel.class));
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            })
                            .addOnFailureListener(e ->
                                    Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show());
                })
                .addOnFailureListener(e -> Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show());

    }
}