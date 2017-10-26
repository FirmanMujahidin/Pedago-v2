package com.pedago2.Components;

public class ItemObject extends Object {

    private String url_foto;
    private int layoutType;

    private String no;
    private String nama;
    private String pelajaran;
    private String jam;

    public ItemObject(
            String No,
            String Nama,
            String Pelajaran,
            String Jam
    ) {
        no = No;
        nama = Nama;
        pelajaran = Pelajaran;
        jam = Jam;
    }

    public ItemObject(String Nama) {
        nama = Nama;
    }

    String getJam() {
        return jam;
    }

    String getNama() {
        return nama;
    }

    String getNo() {
        return no;
    }

    String getPelajaran() {
        return pelajaran;
    }

    int getLayoutType() {
        return this.layoutType;
    }

    String getUrl_foto() {
        return url_foto;
    }

}
