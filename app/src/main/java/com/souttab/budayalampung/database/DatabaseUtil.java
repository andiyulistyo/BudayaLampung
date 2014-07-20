package com.souttab.budayalampung.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.souttab.budayalampung.entity.Entity;
import com.souttab.budayalampung.util.UtilityImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA for
 * <p/>
 * User: andi yulistyo
 * Date: 10/19/13
 * Time: 8:39 PM
 * Task: Skripsiku
 * <p/>
 * source code ini bersifat open source
 * siapa saja bisa menggunakannaya untuk
 * tujua belajar
 */
public class DatabaseUtil {

    private static final String _ID = "_id";
    private static final String _TABLE_MAKANAN = "makanan";
    private static final String _TABLE_KESENIAN = "kesenian";
    private static final String _TABLE_ADAT_ISTIADAT = "adat_istiadat";
    private static final String _TABLE_BAHASA = "bahasa";
    private static final String _TABLE_AKSARA = "aksara";

    private  SQLiteDatabase database;
    private CopyDatabase copyDatabase;


    String nama, deskripsi;
    byte[] image;

    public DatabaseUtil(Context context) {
        copyDatabase = new CopyDatabase(context);
    }

    public void open() { // open database and allow to write data
        database = copyDatabase.getReadableDatabase();
    }

    public void close() { // close database connection
        if (database != null) {
            database.close();
        }
    }

    // MAKANAN
    public  List<Entity> ListAllMakanan() {
        List<Entity> makananList = new ArrayList<Entity>();
        String query = "SELECT * FROM "+ _TABLE_MAKANAN ;

        open();

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            Entity entity;
            do {
                entity = new Entity();

                image = cursor.getBlob(cursor.getColumnIndex("gambar"));
                nama = cursor.getString(cursor.getColumnIndex("nama"));

                entity.setGambar(UtilityImage.getImage(image));
                entity.setTitle(nama);
                entity.setDeskription(cursor.getString(cursor.getColumnIndex("deskripsi")));

                makananList.add(entity);
            } while (cursor.moveToNext());
        }
        close();
        return makananList;
    }


    public Cursor getByIdMakanan(long id) {
        return database.query(_TABLE_MAKANAN, null, _ID + "=" + id, null, null, null, null, null);
    }

    // KESENIAN
    public List<Entity> getAllKesenian() {
        List<Entity> entityList = new ArrayList<Entity>();
        
        String query = "SELECT * FROM kesenian";
        
        open();
        
        Cursor cursor = database.rawQuery(query, null);
        
        if (cursor.moveToFirst()) {
            Entity entity ;
            do {
                entity = new Entity();
                image = cursor.getBlob(cursor.getColumnIndex("gambar"));
                nama = cursor.getString(cursor.getColumnIndex("nama_kesenian"));
                deskripsi = cursor.getString(cursor.getColumnIndex("deskripsi"));

                entity.setGambar(UtilityImage.getImage(image));
                entity.setDeskription(deskripsi);
                entity.setTitle(nama);

                entityList.add(entity);
                
            } while (cursor.moveToNext());
        }
        close();
        return entityList;
    }

    public Cursor getByIdKesenian(long id) {
        return database.query(_TABLE_KESENIAN, null, _ID + "=" + id, null, null, null, null, null);
    }

    // ADAT ISTIADAT
    public List<Entity> getAllAdatIstiadat() {
        List<Entity> entityList = new ArrayList<Entity>();

        String query = "SELECT * FROM adat_istiadat";

        open();

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            Entity entity ;
            do {
                entity = new Entity();
                image = cursor.getBlob(cursor.getColumnIndex("gambar"));
                nama = cursor.getString(cursor.getColumnIndex("nama_adat_istiadat"));
                deskripsi = cursor.getString(cursor.getColumnIndex("deskripsi"));

                entity.setGambar(UtilityImage.getImage(image));
                entity.setDeskription(deskripsi);
                entity.setTitle(nama);

                entityList.add(entity);

            } while (cursor.moveToNext());
        }
        close();
        return entityList;
    }

    public Cursor getByIdAdatIstiadat(long id) {
        return database.query(_TABLE_ADAT_ISTIADAT, null, _ID + "=" + id, null, null, null, null, null);
    }

    // BAHASA
    public List<Entity> getAllBahasa() {
        List<Entity> entityList = new ArrayList<Entity>();

        String query = "SELECT * FROM bahasa";

        open();

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            Entity entity ;
            do {
                entity = new Entity();
                image = cursor.getBlob(cursor.getColumnIndex("gambar"));
                nama = cursor.getString(cursor.getColumnIndex("nama_bahasa"));
                deskripsi = cursor.getString(cursor.getColumnIndex("deskripsi"));

                entity.setGambar(UtilityImage.getImage(image));
                entity.setDeskription(deskripsi);
                entity.setTitle(nama);

                entityList.add(entity);

            } while (cursor.moveToNext());
        }
        close();
        return entityList;
    }

    public Cursor getByIdBahasa(long id) {
        return database.query(_TABLE_BAHASA, null, _ID + "=" + id, null, null, null, null, null);
    }

    // AKSARA
    public List<Entity> getAllAksara() {
        List<Entity> entityList = new ArrayList<Entity>();

        String query = "SELECT * FROM " + _TABLE_AKSARA;

        open();

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            Entity entity ;
            do {
                entity = new Entity();
                image = cursor.getBlob(cursor.getColumnIndex("gambar"));
                nama = cursor.getString(cursor.getColumnIndex("nama"));
                deskripsi = cursor.getString(cursor.getColumnIndex("deskripsi"));

                entity.setGambar(UtilityImage.getImage(image));
                entity.setDeskription(deskripsi);
                entity.setTitle(nama);

                entityList.add(entity);

            } while (cursor.moveToNext());
        }
        close();
        return entityList;
    }

    public Cursor getByIdAksara(long id) {
        return database.query(_TABLE_AKSARA, null, _ID + "=" + id, null, null, null, null, null);
    }
 }
