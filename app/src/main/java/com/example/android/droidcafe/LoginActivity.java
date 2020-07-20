package com.example.android.droidcafe;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText et_username,et_password;
    TextView tv_login,tv_register;
    private FirebaseAuth mAuth;
    Toast back;
    long timepress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        tv_login = findViewById(R.id.tv_login);
        tv_register = findViewById(R.id.tv_register);
        mAuth = FirebaseAuth.getInstance();
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                if (username.equals("")) {
                    Toast.makeText(LoginActivity.this, "Silahkan isi username Anda", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Silahkan isi password Anda", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Preferences.setRegisteredUser(getBaseContext(),username);
                                        Preferences.setLoggedInUser(getBaseContext(),Preferences.getRegisteredUser(getBaseContext()));
                                        Preferences.setLoggedInStatus(getBaseContext(),true);
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(LoginActivity.this, "Login Success.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent inhome = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(inhome);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(LoginActivity.this, "Login Failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
//                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(LoginActivity.this, activityregister.class);
                startActivity(a);
                finish();
//                startActivity(new Intent(getApplicationContext(),activityregister.class));
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        //Preferences
        if (Preferences.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(),MainActivity.class));
            FirebaseUser currentUser = mAuth.getCurrentUser();
            finish();

        }
        // Check if user is signed in (non-null) and update UI accordingly.

    }

    @Override
    public void onBackPressed() {

        if (timepress + 2000>System.currentTimeMillis())
        {
            back.cancel();
            Intent i = new Intent(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        }
        else
        {
            back = Toast.makeText(getApplicationContext(),"Back press again to exit",Toast.LENGTH_LONG);
            back.show();
        }

        timepress = System.currentTimeMillis();
    }
}
