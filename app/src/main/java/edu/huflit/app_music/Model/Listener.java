package edu.huflit.app_music.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Listener {

    @SerializedName("TenNguoiDung")
    @Expose
    private String tenNguoiDung;
    @SerializedName("GioiTinh")
    @Expose
    private String gioiTinh;
    @SerializedName("NgaySinh")
    @Expose
    private String ngaySinh;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Sdt")
    @Expose
    private String sdt;
    @SerializedName("MatKhau")
    @Expose
    private String matKhau;
    @SerializedName("NgayDangKy_Premium")
    @Expose
    private String ngayDangKyPremium;
    @SerializedName("TrangThai_Premium")
    @Expose
    private String trangThaiPremium;

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getNgayDangKyPremium() {
        return ngayDangKyPremium;
    }

    public void setNgayDangKyPremium(String ngayDangKyPremium) {
        this.ngayDangKyPremium = ngayDangKyPremium;
    }

    public String getTrangThaiPremium() {
        return trangThaiPremium;
    }

    public void setTrangThaiPremium(String trangThaiPremium) {
        this.trangThaiPremium = trangThaiPremium;
    }

}