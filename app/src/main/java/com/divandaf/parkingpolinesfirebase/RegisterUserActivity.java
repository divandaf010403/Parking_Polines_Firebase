package com.divandaf.parkingpolinesfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.divandaf.parkingpolinesfirebase.databinding.ActivityRegisterUserBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterUserActivity extends AppCompatActivity {

    ActivityRegisterUserBinding binding;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://parking-polines-firebase-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginBtnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginUserActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.registerBtnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strnama = binding.inpNamaregis.getText().toString();
                String strnim = binding.inpNimregis.getText().toString();
                String strkelas = binding.inpKlsregis.getText().toString();
                String strnoHP = binding.inpNoHPregis.getText().toString();
                String stremail = binding.inpEmailregis.getText().toString();
                String strnoKendaraan = binding.inpPlatregis.getText().toString();
                String strpwd = binding.inpPwdregis.getText().toString();

                if (strnama.isEmpty() || strnim.isEmpty() || strkelas.isEmpty() || strnoHP.isEmpty() || stremail.isEmpty() || strnoKendaraan.isEmpty() || strpwd.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("login").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //check nim belum diregistrasi
                            if (snapshot.hasChild(strnim)) {
                                Toast.makeText(getApplicationContext(), "NIM Already Used", Toast.LENGTH_SHORT).show();
                            } else {
                                databaseReference.child("login").child(strnim).child("nama").setValue(strnama);
                                databaseReference.child("login").child(strnim).child("nim").setValue(strnim);
                                databaseReference.child("login").child(strnim).child("kelas").setValue(strkelas);
                                databaseReference.child("login").child(strnim).child("noHP").setValue(strnoHP);
                                databaseReference.child("login").child(strnim).child("email").setValue(stremail);
                                databaseReference.child("login").child(strnim).child("noKendaraan").setValue(strnoKendaraan);
                                databaseReference.child("login").child(strnim).child("password").setValue(strpwd);
                                databaseReference.child("login").child(strnim).child("as").setValue("user");

                                Toast.makeText(getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterUserActivity.this, LoginUserActivity.class));
                                finish();
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
    public void onBackPressed() {
        // make sure you have this outcommented
        // super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}