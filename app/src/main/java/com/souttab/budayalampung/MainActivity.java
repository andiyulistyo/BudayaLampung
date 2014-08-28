package com.souttab.budayalampung;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.souttab.budayalampung.adapter.AdatViewAdapter;
import com.souttab.budayalampung.adapter.AksaraViewAdapter;
import com.souttab.budayalampung.adapter.BahasaViewAdapter;
import com.souttab.budayalampung.adapter.KesenianViewAdapter;
import com.souttab.budayalampung.adapter.MakananViewAdapter;
import com.souttab.budayalampung.database.CopyDatabase;
import com.souttab.budayalampung.database.DatabaseUtil;
import com.devsmart.android.ui.HorizontalListView;

import java.sql.SQLException;


public class MainActivity extends Activity{

    private DatabaseUtil databaseUtil;

    private HorizontalListView horizontalListViewAdat, horizontalListViewAksara,
            horizontalListViewBahasa, horizontalListViewKesenian, horizontalListViewMakanan;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        databaseUtil = new DatabaseUtil(this);

        //Thread.setDefaultUncaughtExceptionHandler(new CrashException(MainActivity.this));
//        ViewTarget viewTarget = new ViewTarget(drawerToggle.hashCode(), this);
//        showcaseView = new  ShowcaseView.Builder(this)
//                .setTarget(viewTarget)
//                .setContentTitle("Show case example")
//                .setContentText("sdfalsdmflksmfasdfsdf")
//                .hideOnTouchOutside().singleShot(1)
//                .build();

        copyData();

        horizontalListViewAdat = (HorizontalListView) findViewById(R.id.listviewAdat);
        horizontalListViewAksara = (HorizontalListView) findViewById(R.id.listViewAksara);
        horizontalListViewBahasa = (HorizontalListView) findViewById(R.id.listViewBahasa);
        horizontalListViewKesenian = (HorizontalListView) findViewById(R.id.listViewKesenian);
        horizontalListViewMakanan = (HorizontalListView) findViewById(R.id.listViewMakanan);

        AdatViewAdapter adatViewAdapter = new AdatViewAdapter(this, databaseUtil.getAllAdatIstiadat());
        horizontalListViewAdat.setAdapter(adatViewAdapter);
        horizontalListViewAdat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();
            }
        });

        AksaraViewAdapter aksaraViewAdapter = new AksaraViewAdapter(databaseUtil.getAllAksara(), this);
        horizontalListViewAksara.setAdapter(aksaraViewAdapter);

        BahasaViewAdapter bahasaViewAdapter = new BahasaViewAdapter(this, databaseUtil.getAllBahasa());
        horizontalListViewBahasa.setAdapter(bahasaViewAdapter);

        KesenianViewAdapter kesenianViewAdapter = new KesenianViewAdapter(this, databaseUtil.getAllKesenian());
        horizontalListViewKesenian.setAdapter(kesenianViewAdapter);

        MakananViewAdapter makananViewAdapter = new MakananViewAdapter(this, databaseUtil.ListAllMakanan());
        horizontalListViewMakanan.setAdapter(makananViewAdapter);
    }

    void copyData() {
        CopyDatabase copyDatabase = new CopyDatabase(MainActivity.this);

        try {
            copyDatabase.createdDatabase();
            copyDatabase.openDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
