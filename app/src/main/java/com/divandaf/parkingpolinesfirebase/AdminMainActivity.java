package com.divandaf.parkingpolinesfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.divandaf.parkingpolinesfirebase.databinding.ActivityAdminMainBinding;
import com.divandaf.parkingpolinesfirebase.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminMainActivity extends AppCompatActivity {

    ActivityAdminMainBinding binding;
    AdminDataFragment adminDataFragment = new AdminDataFragment();
    AdminQRFragment adminQRFragment = new AdminQRFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.admContentView, adminDataFragment).commit();

        binding.admNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeNav:
                        getSupportFragmentManager().beginTransaction().replace(R.id.admContentView, adminDataFragment).commit();
                        return true;
                    case R.id.historyNav:
                        getSupportFragmentManager().beginTransaction().replace(R.id.admContentView, adminQRFragment).commit();
                        return true;
                }

                return false;
            }
        });
    }
}