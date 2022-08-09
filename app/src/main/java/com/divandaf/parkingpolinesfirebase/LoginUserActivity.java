package com.divandaf.parkingpolinesfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.divandaf.parkingpolinesfirebase.databinding.ActivityLoginUserBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.prefs.Preferences;

public class LoginUserActivity extends AppCompatActivity {

    ActivityLoginUserBinding binding;
    String nim, password;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://parking-polines-firebase-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.registerBtnlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterUserActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.loginBtnlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nim = binding.inputNIMlgn.getText().toString();
                password = binding.inputPasslgn.getText().toString();

                if (nim.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Name and Password", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("login").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child(nim).exists()) {
                                if (snapshot.child(nim).child("password").getValue(String.class).equals(password)) {
                                    if (binding.chkLgn.isChecked()) {
                                        if (snapshot.child(nim).child("as").getValue(String.class).equals("admin")) {
                                            Toast.makeText(LoginUserActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                                            String getNama = snapshot.child(nim).child("nama").getValue(String.class);
                                            String getNim = snapshot.child(nim).child("nim").getValue(String.class);
                                            String getKelas = snapshot.child(nim).child("kelas").getValue(String.class);
                                            String getnoHP = snapshot.child(nim).child("noHP").getValue(String.class);
                                            String getEmail = snapshot.child(nim).child("email").getValue(String.class);
                                            String getnoKendaraan = snapshot.child(nim).child("noKendaraan").getValue(String.class);

                                            Preference.setDataLogin(LoginUserActivity.this, true, getNama, getNim, getKelas, getnoHP,
                                                    getEmail, getnoKendaraan);
                                            Preference.setDataAs(LoginUserActivity.this, "admin");
                                            startActivity(new Intent(LoginUserActivity.this, AdminMainActivity.class));

                                        } else if (snapshot.child(nim).child("as").getValue(String.class).equals("user")){
                                            Toast.makeText(LoginUserActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
//                                            startActivity(new Intent(LoginUserActivity.this, MainActivity.class));

                                            String getNama = snapshot.child(nim).child("nama").getValue(String.class);
                                            String getNim = snapshot.child(nim).child("nim").getValue(String.class);
                                            String getKelas = snapshot.child(nim).child("kelas").getValue(String.class);
                                            String getnoHP = snapshot.child(nim).child("noHP").getValue(String.class);
                                            String getEmail = snapshot.child(nim).child("email").getValue(String.class);
                                            String getnoKendaraan = snapshot.child(nim).child("noKendaraan").getValue(String.class);

                                            Preference.setDataLogin(LoginUserActivity.this, true, getNama, getNim, getKelas, getnoHP,
                                                    getEmail, getnoKendaraan);
                                            Preference.setDataAs(LoginUserActivity.this, "user");

                                            Intent intent = new Intent(LoginUserActivity.this, MainActivity.class);

                                            intent.putExtra("nama", getNama);
                                            intent.putExtra("nim", getNim);
                                            intent.putExtra("kelas", getKelas);
                                            intent.putExtra("noHP", getnoHP);
                                            intent.putExtra("email", getEmail);
                                            intent.putExtra("noKendaraan", getnoKendaraan);

                                            startActivity(intent);
                                        }
                                    } else {
                                        if (snapshot.child(nim).child("as").getValue(String.class).equals("admin")) {
                                            Toast.makeText(LoginUserActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                                            String getNama = snapshot.child(nim).child("nama").getValue(String.class);
                                            String getNim = snapshot.child(nim).child("nim").getValue(String.class);
                                            String getKelas = snapshot.child(nim).child("kelas").getValue(String.class);
                                            String getnoHP = snapshot.child(nim).child("noHP").getValue(String.class);
                                            String getEmail = snapshot.child(nim).child("email").getValue(String.class);
                                            String getnoKendaraan = snapshot.child(nim).child("noKendaraan").getValue(String.class);

                                            Preference.setDataLogin(LoginUserActivity.this, false, getNama, getNim, getKelas, getnoHP,
                                                    getEmail, getnoKendaraan);
                                            Preference.setDataAs(LoginUserActivity.this, "admin");

                                            startActivity(new Intent(LoginUserActivity.this, AdminMainActivity.class));


                                        } else if (snapshot.child(nim).child("as").getValue(String.class).equals("user")){
                                            Toast.makeText(LoginUserActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                                            String getNama = snapshot.child(nim).child("nama").getValue(String.class);
                                            String getNim = snapshot.child(nim).child("nim").getValue(String.class);
                                            String getKelas = snapshot.child(nim).child("kelas").getValue(String.class);
                                            String getnoHP = snapshot.child(nim).child("noHP").getValue(String.class);
                                            String getEmail = snapshot.child(nim).child("email").getValue(String.class);
                                            String getnoKendaraan = snapshot.child(nim).child("noKendaraan").getValue(String.class);

                                            Preference.setDataLogin(LoginUserActivity.this, false, getNama, getNim, getKelas, getnoHP,
                                                    getEmail, getnoKendaraan);
                                            Preference.setDataAs(LoginUserActivity.this, "user");

                                            Intent intent = new Intent(LoginUserActivity.this, MainActivity.class);

                                            intent.putExtra("nama", getNama);
                                            intent.putExtra("nim", getNim);
                                            intent.putExtra("kelas", getKelas);
                                            intent.putExtra("noHP", getnoHP);
                                            intent.putExtra("email", getEmail);
                                            intent.putExtra("noKendaraan", getnoKendaraan);

                                            startActivity(intent);
                                        }
                                    }

                                } else {
                                    Toast.makeText(LoginUserActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginUserActivity.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preference.getDataLogin(this)) {
            if (Preference.getDataAs(this).equals("admin")) {
                Intent intent = new Intent(this, AdminMainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // make sure you have this outcommented
        // super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}