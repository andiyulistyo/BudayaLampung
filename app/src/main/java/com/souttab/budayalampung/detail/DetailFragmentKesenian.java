package com.souttab.budayalampung.detail;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.souttab.budayalampung.MainActivity;
import com.souttab.budayalampung.R;
import com.souttab.budayalampung.database.DatabaseUtil;
import com.souttab.budayalampung.entity.EntityID;
import com.souttab.budayalampung.util.UtilityImage;

/**
 * Created with IntelliJ IDEA for
 * <p/>
 * User: andi yulistyo
 * Date: 10/31/13
 * Time: 3:03 PM
 * Task: Skripsiku
 * <p/>
 * source code ini bersifat open source
 * siapa saja bisa menggunakannaya untuk
 * tujua belajar
 */

public class DetailFragmentKesenian extends SherlockFragmentActivity  {

    WebView webView;
    String data;
    private TextView textViewNama, textViewDeskripsi;
    private ImageView imageView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_kesenian_fragment);
        getSupportActionBar().setTitle("Detail");

        Long id = getIntent().getExtras().getLong("id");

        webView = (WebView) findViewById(R.id.webview);
        webView.setBackgroundColor(Color.parseColor("#2f2e2c"));
        textViewNama = (TextView) findViewById(R.id.textViewImageTitleKesenian);
        imageView = (ImageView) findViewById(R.id.imageViewDetailKesenian);


        new GetData().execute(id);

    }

    @Override
    public void onBackPressed() {
        EntityID.setId(3);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

        finish();
    }

    class GetData extends AsyncTask<Long, Object, Cursor> {

        DatabaseUtil databaseUtil = new DatabaseUtil(getApplicationContext());

        @Override
        protected Cursor doInBackground(Long... longs) {
            databaseUtil.open();
            return databaseUtil.getByIdKesenian(longs[0]);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            cursor.moveToFirst();

            byte[] image = cursor.getBlob(cursor.getColumnIndex("gambar"));

            String des = cursor.getString(cursor.getColumnIndex("deskripsi"));

            String deskrip = "<html><body text=\"#ffffff\">"
                    + "<p align=\"justify\">"
                    + des.replace(".", "<br><br>")
                    + "</p> "
                    + "</body></html>";

            webView.loadData(deskrip, "text/html", "utf-8");
            textViewNama.setText(cursor.getString(cursor.getColumnIndex("nama_kesenian")));
            imageView.setImageBitmap(UtilityImage.getImage(image));
        }
    }
}
