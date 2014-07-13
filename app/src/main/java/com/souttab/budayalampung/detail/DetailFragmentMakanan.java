package com.souttab.budayalampung.detail;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.souttab.budayalampung.MainActivity;
import com.souttab.budayalampung.R;
import com.souttab.budayalampung.database.DatabaseUtil;
import com.souttab.budayalampung.entity.EntityID;
import com.souttab.budayalampung.util.UtilityImage;

/**
 * Created with IntelliJ IDEA for
 * <p/>
 * User: andi yulistyo
 * Date: 10/25/13
 * Time: 6:59 PM
 * Task: Skripsiku
 * <p/>
 * source code ini bersifat open source
 * siapa saja bisa menggunakannaya untuk
 * tujua belajar
 */
public class DetailFragmentMakanan extends SherlockActivity {

    private ImageView imageViewDetail;
    private TextView textViewTitle, textViewDeskrip;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_makanan_fragment);

        Long id = getIntent().getExtras().getLong("id");

        new getData().execute(id);

        imageViewDetail = (ImageView) findViewById(R.id.imageViewDetail);
        textViewTitle = (TextView) findViewById(R.id.textViewImageTitle);
        textViewDeskrip = (TextView) findViewById(R.id.textViewDeskDetail);
    }

    @Override
    public void onBackPressed() {

        EntityID.setId(4);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();


    }

    class getData extends AsyncTask<Long, Object, Cursor> {

        DatabaseUtil database = new DatabaseUtil(getApplicationContext());


        @Override
        protected Cursor doInBackground(Long... longs) {
            database.open();
            return database.getByIdMakanan(longs[0]);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            cursor.moveToFirst();

            byte[] image = cursor.getBlob(cursor.getColumnIndex("gambar"));

            textViewTitle.setText(cursor.getString(cursor.getColumnIndex("nama")));
            imageViewDetail.setImageBitmap(UtilityImage.getImage(image));
            textViewDeskrip.setText(cursor.getString(cursor.getColumnIndex("deskripsi")));
        }
    }
}
