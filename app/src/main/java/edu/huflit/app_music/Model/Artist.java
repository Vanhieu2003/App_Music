package edu.huflit.app_music.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Artist {

    @SerializedName("MaArtist")
    @Expose
    private String maArtist;
    @SerializedName("HoTenArtist")
    @Expose
    private String hoTenArtist;
    @SerializedName("HinhArtist")
    @Expose
    private String hinhArtist;

    public String getMaArtist() {
        return maArtist;
    }

    public void setMaArtist(String maArtist) {
        this.maArtist = maArtist;
    }

    public String getHoTenArtist() {
        return hoTenArtist;
    }

    public void setHoTenArtist(String hoTenArtist) {
        this.hoTenArtist = hoTenArtist;
    }

    public String getHinhArtist() {
        return hinhArtist;
    }

    public void setHinhArtist(String hinhArtist) {
        this.hinhArtist = hinhArtist;
    }

}