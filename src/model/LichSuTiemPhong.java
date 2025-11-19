package model;

import java.time.LocalDate;

public class LichSuTiemPhong {
    private String maKH;
    private String maVacxin;
    private int sttMui;
    private LocalDate ngayTiemPhong;
    private LocalDate ngayHenTiepTheo;

    public LichSuTiemPhong() {
    }

    public LichSuTiemPhong(String maKH, String maVacxin, int sttMui, LocalDate ngayTiemPhong, LocalDate ngayHenTiepTheo) {
        this.maKH = maKH;
        this.maVacxin = maVacxin;
        this.sttMui = sttMui;
        this.ngayTiemPhong = ngayTiemPhong;
        this.ngayHenTiepTheo = ngayHenTiepTheo;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaVacxin() {
        return maVacxin;
    }

    public void setMaVacxin(String maVacxin) {
        this.maVacxin = maVacxin;
    }

    public int getSttMui() {
        return sttMui;
    }

    public void setSttMui(int sttMui) {
        this.sttMui = sttMui;
    }

    public LocalDate getNgayTiemPhong() {
        return ngayTiemPhong;
    }

    public void setNgayTiemPhong(LocalDate ngayTiemPhong) {
        this.ngayTiemPhong = ngayTiemPhong;
    }

    public LocalDate getNgayHenTiepTheo() {
        return ngayHenTiepTheo;
    }

    public void setNgayHenTiepTheo(LocalDate ngayHenTiepTheo) {
        this.ngayHenTiepTheo = ngayHenTiepTheo;
    }
}
