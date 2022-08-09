package com.divandaf.parkingpolinesfirebase;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

public class Preference {

    private static final String DATA_LOGIN = "Data",
            DATA_AS = "as";
    public static final String key_nama = "nama";
    public static final String key_nim = "nim";
    public static final String key_kelas = "kelas";
    public static final String key_noHP = "noHP";
    public static final String key_email = "email";
    public static final String key_noKendaraan = "noKendaraan";

    private static SharedPreferences getSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setDataAs(Context context, String data){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(DATA_AS,data);
        editor.apply();
    }

    public static String getDataAs(Context context){
        return getSharedPreferences(context).getString(DATA_AS,"");
    }

    public static void setDataLogin(Context context, boolean status, String nama, String nim, String kelas, String noHP, String email, String noKendaraan){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(DATA_LOGIN,status);
        editor.putString(key_nama, nama);
        editor.putString(key_nim, nim);
        editor.putString(key_kelas, kelas);
        editor.putString(key_noHP, noHP);
        editor.putString(key_email, email);
        editor.putString(key_noKendaraan, noKendaraan);

        editor.commit();
        editor.apply();
    }

    public static boolean getDataLogin(Context context){
        return getSharedPreferences(context).getBoolean(DATA_LOGIN,false);
    }

    public static void clearData(Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(DATA_AS);
        editor.remove(DATA_LOGIN);
        editor.apply();
    }

    public static void getDataLogin(Context context, String nama, String nim, String kelas, String noHP, String email, String noKendaraan){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(DATA_LOGIN, false);

        editor.putString(key_nama, nama);
        editor.putString(key_nim, nim);
        editor.putString(key_kelas, kelas);
        editor.putString(key_noHP, noHP);
        editor.putString(key_email, email);
        editor.putString(key_noKendaraan, noKendaraan);

        editor.commit();
    }

    public static HashMap<String, String> getDetails(Context context) {
        HashMap<String, String> userData = new HashMap<String, String>();

        userData.put(key_nama, getSharedPreferences(context).getString(key_nama, null));
        userData.put(key_nim, getSharedPreferences(context).getString(key_nim, null));
        userData.put(key_kelas, getSharedPreferences(context).getString(key_kelas, null));
        userData.put(key_noHP, getSharedPreferences(context).getString(key_noHP, null));
        userData.put(key_email, getSharedPreferences(context).getString(key_email, null));
        userData.put(key_noKendaraan, getSharedPreferences(context).getString(key_noKendaraan, null));

        return userData;
    }
}
