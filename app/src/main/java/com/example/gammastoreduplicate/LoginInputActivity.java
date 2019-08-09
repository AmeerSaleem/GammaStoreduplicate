package com.example.gammastoreduplicate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
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

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class LoginInputActivity extends AppCompatActivity {

    ImageView wallpaper;
    LinearLayout signin_login_input;
    ExtendedEditText extendedEditTextLoginEmail, extendedEditTextLoginPassword;
    TextView register_link;
    ProgressBar progressBar1;
    FirebaseAuth mAuth1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_input);

        progressBar1 = findViewById(R.id.progress_bar_login);
        extendedEditTextLoginEmail = findViewById(R.id.extended_edit_text_login_email);
        extendedEditTextLoginPassword = findViewById(R.id.extended_edit_text_login_password);
        mAuth1 = FirebaseAuth.getInstance();
        register_link = findViewById(R.id.login_register_here_text);

        register_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginInputActivity.this, SignUpActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), android.R.anim.fade_in,
                        android.R.anim.fade_out).toBundle();
                startActivity(intent, bundle);
                finish();
            }
        });

        signin_login_input = findViewById(R.id.login_input_sign_in_button);
        signin_login_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email1 = extendedEditTextLoginEmail.getText().toString().trim();
                String password1 = extendedEditTextLoginPassword.getText().toString().trim();

                if (email1.isEmpty()) {
                    extendedEditTextLoginEmail.setError("Email is required");
                    extendedEditTextLoginEmail.requestFocus();
                    return;
                }

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]";
                boolean match = Patterns.EMAIL_ADDRESS.matcher(email1).matches();
                if (!match) {
                    extendedEditTextLoginEmail.setError("Enter a valid email");
                    extendedEditTextLoginEmail.requestFocus();
                    return;
                }

                if (password1.isEmpty()) {
                    extendedEditTextLoginPassword.setError("Password is required");
                    extendedEditTextLoginPassword.requestFocus();
                    return;
                }

                if (password1.length() < 6) {
                    extendedEditTextLoginPassword.setError("Password length must be at least 6 characters");
                    extendedEditTextLoginPassword.requestFocus();
                    return;
                }

                progressBar1.setVisibility(View.VISIBLE);
                mAuth1.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar1.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginInputActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), android.R.anim.fade_in,
                                    android.R.anim.fade_out).toBundle();
                            startActivity(intent, bundle);
                            finish();
                        } else {
                            Toast.makeText(LoginInputActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        wallpaper = findViewById(R.id.login_signup_screen_wallpaper);

        Glide
                .with(this)
                .load("https://images.pexels.com/photos/264554/pexels-photo-264554.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940")
                .into(wallpaper);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
//            if ( v instanceof EditText) {
//                Rect outRect = new Rect();
//                v.getGlobalVisibleRect(outRect);
//                if (!outRect.contains((int)ev.getRawX(), (int)ev.getRawY())) {
//                    v.clearFocus();
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                }
//            }
//        }
//        return super.dispatchTouchEvent( ev );    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(LoginInputActivity.this, LoginIntroActivity.class);
        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), android.R.anim.fade_in,
                android.R.anim.fade_out).toBundle();
        startActivity(intent, bundle);
        finish();

//        R.anim.left_to_right, R.anim.right_to_left
//        R.anim.slide_left, R.anim.slide_right
    }
}