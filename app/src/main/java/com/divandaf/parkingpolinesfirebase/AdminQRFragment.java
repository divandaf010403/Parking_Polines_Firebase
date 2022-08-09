package com.divandaf.parkingpolinesfirebase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class AdminQRFragment extends Fragment {

    Button logoutBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin_q_r, container, false);

        logoutBtn = (Button) view.findViewById(R.id.admLogoutBtn);

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
        return view;
    }
}