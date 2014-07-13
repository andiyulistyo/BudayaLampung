package com.souttab.budayalampung;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import com.actionbarsherlock.app.SherlockActivity;

/**
 * Created with IntelliJ IDEA for
 * User: andi yulistyo
 * Date: 11/16/13
 * Time: 6:09 PM
 * Task: Skripsiku
 * source code ini bersifat open source
 * siapa saja bisa menggunakannaya untuk
 * tujuan belajar
 */
public class SpashScreen extends SherlockActivity{

    private static final long SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splashscreen);

        // new DownloadaData().execute();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SpashScreen.this,
                        MainActivity.class);
                startActivity(intent);

                // Close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
