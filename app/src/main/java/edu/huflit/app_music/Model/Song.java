package edu.huflit.app_music.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Song implements Serializable {

    @SerializedName("MaSong")
    @Expose
    private String maSong;
    @SerializedName("TenArtist")
    @Expose
    private String tenArtist;
    @SerializedName("MaTL")
    @Expose
    private String maTL;
    @SerializedName("TenBH")
    @Expose
    private String tenBH;
    @SerializedName("MoTa")
    @Expose
    private String moTa;
    @SerializedName("BHUrl")
    @Expose
    private String bHUrl;
    @SerializedName("HinhAnhUrl")
    @Expose
    private String hinhAnhUrl;
    @SerializedName("LuotThich")
    @Expose
    private String luotThich;

    public String getMaSong() {
        return maSong;
    }

    public void setMaSong(String maSong) {
        this.maSong = maSong;
    }

    public String getTenArtist() {
        return tenArtist;
    }

    public void setTenArtist(String tenArtist) {
        this.tenArtist = tenArtist;
    }

    public String getMaTL() {
        return maTL;
    }

    public void setMaTL(String maTL) {
        this.maTL = maTL;
    }

    public String getTenBH() {
        return tenBH;
    }

    public void setTenBH(String tenBH) {
        this.tenBH = tenBH;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getBHUrl() {
        return bHUrl;
    }

    public void setBHUrl(String bHUrl) {
        this.bHUrl = bHUrl;
    }

    public String getHinhAnhUrl() {
        return hinhAnhUrl;
    }

    public void setHinhAnhUrl(String hinhAnhUrl) {
        this.hinhAnhUrl = hinhAnhUrl;
    }

    public String getLuotThich() {
        return luotThich;
    }

    public void setLuotThich(String luotThich) {
        this.luotThich = luotThich;
    }

}