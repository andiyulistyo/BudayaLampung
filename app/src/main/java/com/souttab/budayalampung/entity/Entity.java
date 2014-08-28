package com.souttab.budayalampung.entity;

/**
 * Created with IntelliJ IDEA for
 * <p/>
 * User: andi yulistyo
 * Date: 10/31/13
 * Time: 1:30 PM
 * Task: Skripsiku
 * <p/>
 * source code ini bersifat open source
 * siapa saja bisa menggunakannaya untuk
 * tujua belajar
 */

public class Entity {

    private String title;
    private byte[] gambar;
    private String deskription;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getGambar() {
        return gambar;
    }

    public void setGambar(byte[] gambar) {
        this.gambar = gambar;
    }

    public String getDeskription() {
        return deskription;
    }

    public void setDeskription(String deskription) {
        this.deskription = deskription;
    }
}
