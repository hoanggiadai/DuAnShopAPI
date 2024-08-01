package edu.fpt.lenovo.shoponline.model;

import java.io.Serializable;

public class SanPham implements Serializable {
    private int ID;
    private String tenSanPham;
    private Integer giaSanPham;
    private String hinhAnhSanPham;
    private String moTaSanPham;
    private int IDSanPham;

    public SanPham() {
    }

    public SanPham(int ID, String tenSanPham, Integer giaSanPham, String hinhAnhSanPham, String moTaSanPham, int IDSanPham) {
        this.ID = ID;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.hinhAnhSanPham = hinhAnhSanPham;
        this.moTaSanPham = moTaSanPham;
        this.IDSanPham = IDSanPham;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Integer getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(Integer giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getHinhAnhSanPham() {
        return hinhAnhSanPham;
    }

    public void setHinhAnhSanPham(String hinhAnhSanPham) {
        this.hinhAnhSanPham = hinhAnhSanPham;
    }

    public String getMoTaSanPham() {
        return moTaSanPham;
    }

    public void setMoTaSanPham(String moTaSanPham) {
        this.moTaSanPham = moTaSanPham;
    }

    public int getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(int IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

}
