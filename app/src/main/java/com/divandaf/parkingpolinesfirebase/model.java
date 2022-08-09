package com.divandaf.parkingpolinesfirebase;

public class model {

    String nama, nim, kelas, noHP, email, noKendaraan;

    model(){

    }

    public model(String nama, String nim, String kelas, String noHP, String email, String noKendaraan) {
        this.nama = nama;
        this.nim = nim;
        this.kelas = kelas;
        this.noHP = noHP;
        this.email = email;
        this.noKendaraan = noKendaraan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoKendaraan() {
        return noKendaraan;
    }

    public void setNoKendaraan(String noKendaraan) {
        this.noKendaraan = noKendaraan;
    }
}
