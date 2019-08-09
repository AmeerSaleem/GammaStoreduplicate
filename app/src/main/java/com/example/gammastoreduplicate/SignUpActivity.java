package com.example.gammastoreduplicate;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class SignUpActivity extends AppCompatActivity {

    ImageView wallpaper;
    LinearLayout sign_up_register_button;
    ExtendedEditText extendedEditTextEmail, extendedEditTextPassword;
    TextView sign_in_text;

    ProgressBar progress;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progress = findViewById(R.id.sign_up_progress_bar);
        progress.bringToFront();
//        progress.getIndeterminateDrawable().setColorFilter(0x005B4C, PorterDuff.Mode.MULTIPLY);

        mAuth = FirebaseAuth.getInstance();
        extendedEditTextEmail = findViewById(R.id.extended_edit_text_email_sign_up);
        extendedEditTextPassword = findViewById(R.id.extended_edit_text_password_sign_up);

        sign_up_register_button = findViewById(R.id.sign_up_button1);

        sign_up_register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();
//                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), android.R.anim.fade_in,
//                        android.R.anim.fade_out).toBundle();
//                startActivity(intent, bundle);
//                finish();
            }
        });
        sign_in_text = findViewById(R.id.sign_in_here_text);
        wallpaper = findViewById(R.id.signup_screen_wallpaper);

        sign_in_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginInputActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), android.R.anim.fade_in,
                        android.R.anim.fade_out).toBundle();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent, bundle);
                finish();
            }
        });

        Glide.with(this)
                .load("https://images.pexels.com/photos/1368690/pexels-photo-1368690.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940")
                .into(wallpaper);

    }

    private void registerUser() {

        String email = extendedEditTextEmail.getText().toString().trim();
        String password = extendedEditTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            extendedEditTextEmail.setError("Email is required");
            extendedEditTextEmail.requestFocus();
            return;
        }

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]";
        boolean match = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        if (!match) {
            extendedEditTextEmail.setError("Enter a valid email");
            extendedEditTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            extendedEditTextPassword.setError("Password is required");
            extendedEditTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            extendedEditTextPassword.setError("Password length must be at least 6 characters");
            extendedEditTextPassword.requestFocus();
            return;
        }

        progress.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progress.setVisibility(View.GONE);

                    Toast.makeText(SignUpActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), android.R.anim.fade_in,
                            android.R.anim.fade_out).toBundle();
                    startActivity(intent, bundle);
                    finish();
                } else {
                    progress.setVisibility(View.GONE);
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(SignUpActivity.this, "User is already registered", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Some error occurred", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
//            if (v instanceof EditText) {
//                Rect outRect = new Rect();
//                v.getGlobalVisibleRect(outRect);
//                if (!outRect.contains((int) ev.getRawX(), (int) ev.getRawY())) {
//                    v.clearFocus();
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                }
//            }
//        }
//        return super.dispatchTouchEvent(ev);
//    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SignUpActivity.this, LoginIntroActivity.class);
        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), android.R.anim.fade_in,
                android.R.anim.fade_out).toBundle();
        startActivity(intent, bundle);
        finish();
    }
}