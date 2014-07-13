package com.souttab.budayalampung.detail;

import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.souttab.budayalampung.R;
import com.souttab.budayalampung.database.DatabaseUtil;
import com.souttab.budayalampung.util.UtilityImage;

/**
 * Created with IntelliJ IDEA for
 * User: andi yulistyo
 * Date: 11/1/13
 * Time: 11:43 PM
 * Task: Skripsiku
 * source code ini bersifat open source
 * siapa saja bisa menggunakannaya untuk
 * tujuan belajar
 */
public class DetailFragmentAdatIstiadat extends SherlockActivity {

    private TextView textView;
    private ImageView imageView;
    private WebView webView;

    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.detail_adat_istiadat_fragment);

        textView = (TextView) findViewById(R.id.textViewImageTitleDetailAdatIstiadat);
        imageView = (ImageView) findViewById(R.id.imageViewDetailAdatIstiadat);
        webView = (WebView) findViewById(R.id.webviewDetailAdatIstiadat);
        webView.setBackgroundColor(Color.parseColor("#2f2e2c"));


        id = getIntent().getExtras().getLong("id");

        new GetData().execute(id);
    }

    private class GetData extends AsyncTask<Long, Object, Cursor> {

        DatabaseUtil databaseUtil = new DatabaseUtil(getApplicationContext());

        @Override
        protected Cursor doInBackground(Long... longs) {
            databaseUtil.open();
            return databaseUtil.getByIdAdatIstiadat(id);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            cursor.moveToFirst();
            byte[] image = cursor.getBlob(cursor.getColumnIndex("gambar"));
            String desk = cursor.getString(cursor.getColumnIndex("deskripsi"));

            String data ="<html><body text=\"#ffffff\"><p align = \"justify\" class=\"wrap\">" + desk.replace(".", "<br><br>") + "</p></body></html>";

            textView.setText(cursor.getString(cursor.getColumnIndex("nama_adat_istiadat")));
            imageView.setImageBitmap(UtilityImage.getImage(image));
            webView.loadData(data, "text/html", "utf_8");
        }
    }
}
