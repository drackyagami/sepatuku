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

public class activityregister extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText et_nama,et_email,et_username_r,et_password_r;
    TextView tv_register_r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityregister);
        mAuth = FirebaseAuth.getInstance();
        tv_register_r = findViewById(R.id.tv_register_r);
        et_nama = findViewById(R.id.et_nama);
        et_email= findViewById(R.id.et_email);
        et_password_r = findViewById(R.id.et_password_r);

        tv_register_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_nama.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password_r.getText().toString();

                if (et_nama.equals(" ")){
                    Toast.makeText(activityregister.this, "Silahakan isi nama anda", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (et_email.equals(" ")){
                    Toast.makeText(activityregister.this, "Silahkan Isi email anda", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (et_password_r.equals(" ")){
                    Toast.makeText(activityregister.this, "Silahkan isi password anda", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(activityregister.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(activityregister.this, "Registrasi Success.",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(activityregister.this, "Registrasi Failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                });
            }
        });

    } @Override
    public void onBackPressed() {
        Intent c = new Intent(activityregister.this,LoginActivity.class);
        startActivity(c);
        finish();

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}
