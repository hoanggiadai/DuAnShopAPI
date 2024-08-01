package edu.fpt.lenovo.shoponline.model;

public class LoaiSP {
    private int ID;
    private String tenLoaiSP;
    private String hinhLoaiSP;

    public LoaiSP() {
    }

    public LoaiSP(int ID, String tenLoaiSP, String hinhLoaiSP) {
        this.ID = ID;
        this.tenLoaiSP = tenLoaiSP;
        this.hinhLoaiSP = hinhLoaiSP;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }

    public String getHinhLoaiSP() {
        return hinhLoaiSP;
    }

    public void setHinhLoaiSP(String hinhLoaiSP) {
        this.hinhLoaiSP = hinhLoaiSP;
    }
}
