package model.dto;

public class ThongKeChiPhiView {
    private String maKH;
    private String tenKH;
    private String diaChi;
    private long tongTien;

    public ThongKeChiPhiView(String maKH, String tenKH, String diaChi, long tongTien) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.tongTien = tongTien;
    }

    public String getMaKH() {
        return maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public long getTongTien() {
        return tongTien;
    }
}
