package com.example.guest.ourreddit.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.ourreddit.Constants;
import com.example.guest.ourreddit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private String TAG = LoginActivity.class.getSimpleName();
    private ProgressDialog mAuthProgressDialog;

    @Bind(R.id.loginButton) Button mLoginButton;
    @Bind(R.id.usernameText) EditText mEmailEditText;
    @Bind(R.id.passwordText) EditText mPasswordEditText;
    @Bind(R.id.registerTextView) TextView mRegisterTextView;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, RedditCategoriesActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };

        mLoginButton.setOnClickListener(this);
        mRegisterTextView.setOnClickListener(this);
        createAuthProgressDialog();
    }

    @Override
    public void onClick(View v){
        if(v == mLoginButton) {
            loginWithPassword();
        }
        if (v == mRegisterTextView) {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }

    private void loginWithPassword() {
        String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        if (email.equals("")) {
            mEmailEditText.setError("Please enter your email");
            return;
        }
        if (password.equals("")) {
            mPasswordEditText.setError("Password cannot be blank");
            return;
        }

        mAuthProgressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mAuthProgressDialog.dismiss();
                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                if (!task.isSuccessful()) {
                    Log.w(TAG, "signInWithEmail", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}