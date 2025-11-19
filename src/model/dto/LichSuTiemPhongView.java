package model.dto;

public class LichSuTiemPhongView {
    private String maKH;
    private String tenKH;
    private String tenBenh;
    private String maVacxin;
    private String tenVacxin;
    private int tongSoMui;

    public LichSuTiemPhongView(String maKH, String tenKH, String tenBenh, String maVacxin, String tenVacxin, int tongSoMui) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.tenBenh = tenBenh;
        this.maVacxin = maVacxin;
        this.tenVacxin = tenVacxin;
        this.tongSoMui = tongSoMui;
    }

    public String getMaKH() {
        return maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public String getTenBenh() {
        return tenBenh;
    }

    public String getMaVacxin() {
        return maVacxin;
    }

    public String getTenVacxin() {
        return tenVacxin;
    }

    public int getTongSoMui() {
        return tongSoMui;
    }
}
