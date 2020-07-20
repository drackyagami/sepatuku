package com.example.android.droidcafe;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class logout extends AppCompatActivity {
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        logout = findViewById(R.id.btnlogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(com.example.android.droidcafe.logout.this);
                alertdialog.setTitle("Konfirmasi logout");
                alertdialog.setMessage("apakah anda ingin Logout ?");
                alertdialog.setNegativeButton("ya",(dialog, which) -> {
                    dialog.dismiss();
                    Preferences.clearLoggedInUser(getBaseContext());
                    startActivity(new Intent(getBaseContext(),LoginActivity.class));
                    finish();
                });
                alertdialog.setPositiveButton("tidak",
                        (dialog,which) -> dialog.dismiss());
                alertdialog.show();
            }
        });
    }
}