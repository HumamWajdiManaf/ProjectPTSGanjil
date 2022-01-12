package com.example.ptsganjil202111rpl2humam14.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class model extends RealmObject implements Comparable  {
    @PrimaryKey
    private Integer id;
    private Boolean favorite;
    private String gambar;
    private String judul;
    private String deskripsi;
    private String rating;

    public model(Integer id, Boolean favorite, String gambar, String judul, String deskripsi, String rating) {
        this.id = id;
        this.favorite = favorite;
        this.gambar = gambar;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.rating = rating;
    }


    public model(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }


    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public int compareTo(Object abang) {
        int compareage=((model)abang).getId();
        return this.id-compareage;
    }

}
