package com.divandaf.parkingpolinesfirebase;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class UserAccountFragment extends Fragment {

    Button logoutBtn, deleteBtn;
    TextView namaView, nimView, kelasView, noHPview, emailView, noPlatView;
    public static final String TAG = "deleteUser";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_account, container, false);

        namaView = view.findViewById(R.id.viewNama);
        nimView = view.findViewById(R.id.viewnim);
        kelasView = view.findViewById(R.id.viewkelas);
        noHPview = view.findViewById(R.id.viewtelp);
        emailView = view.findViewById(R.id.viewEmail);
        noPlatView = view.findViewById(R.id.viewPlat);
        logoutBtn = view.findViewById(R.id.logout_btn);
        deleteBtn = view.findViewById(R.id.delete_btn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Logout");
                builder.setMessage("Confirm Logout?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Logout Success", Toast.LENGTH_SHORT).show();
                        Preference.clearData(getActivity());
                        startActivity(new Intent(getActivity(), LoginUserActivity.class));
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });

        showAllUserData();
        HashMap<String, String> userDetails = Preference.getDetails(getActivity());

        String sNama = userDetails.get(Preference.key_nama);
        String sNim = userDetails.get(Preference.key_nim);
        String sKelas = userDetails.get(Preference.key_kelas);
        String sNoHP = userDetails.get(Preference.key_noHP);
        String sEmail = userDetails.get(Preference.key_email);
        String sNoKendaraan = userDetails.get(Preference.key_noKendaraan);

        namaView.setText(": " + sNama);
        nimView.setText(": " + sNim);
        kelasView.setText(": " + sKelas);
        noHPview.setText(": " + sNoHP);
        emailView.setText(": " + sEmail);
        noPlatView.setText(": " + sNoKendaraan);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Delete Account");
                builder.setMessage("Confirm?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Delete Account Success", Toast.LENGTH_SHORT).show();
                        deleteUser(sNim);
                        Preference.clearData(getActivity());
                        startActivity(new Intent(getActivity(), LoginUserActivity.class));
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });

        return view;
    }

    private void showAllUserData() {
        Intent intent = getActivity().getIntent();
        String namanya = intent.getStringExtra("nama");
        String nimnya = intent.getStringExtra("nim");
        String kelasnya = intent.getStringExtra("kelas");
        String noHPnya = intent.getStringExtra("noHP");
        String emailnya = intent.getStringExtra("email");
        String noKendaraannya = intent.getStringExtra("noKendaraan");

        namaView.setText(namanya);
        nimView.setText(nimnya);
        kelasView.setText(kelasnya);
        noHPview.setText(noHPnya);
        emailView.setText(emailnya);
        noPlatView.setText(noKendaraannya);
    }

    private void deleteUser(String nim) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("login").child(nim);
        databaseReference.removeValue();
    }
}