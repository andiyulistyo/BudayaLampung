package com.souttab.budayalampung;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.souttab.budayalampung.adapter.MenuLeftListAdapater;
import com.souttab.budayalampung.database.CopyDatabase;
import com.souttab.budayalampung.entity.EntityID;

import java.sql.SQLException;

public class MainActivity extends SherlockFragmentActivity {

    // declare variable
    private static DrawerLayout drawerLayout;
    private static ListView drawerList;

    // create inisialisation from fragment class
    private AdatIstiadatFragment adatIstiadatFragment = new AdatIstiadatFragment();
    private BahasaFragment bahasaFragment = new BahasaFragment();
    private KesenianFragment kesenianFragment = new KesenianFragment();
    private MakananFragment makananFragment = new MakananFragment();
    private FeedbackFragment feedbackFragment = new FeedbackFragment();
    private AksaraFragment aksaraFragment = new AksaraFragment();

    private ActionBarDrawerToggle drawerToggle;
    private MenuLeftListAdapater menuLeftListAdapater;

    // array
    private String[] title;
    private String[] subtitle;
    private int[] icon;

    // for select id in navigation menu
    private int id;

    ShowcaseView showcaseView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        //Thread.setDefaultUncaughtExceptionHandler(new CrashException(MainActivity.this));
//        ViewTarget viewTarget = new ViewTarget(drawerToggle.hashCode(), this);
//        showcaseView = new  ShowcaseView.Builder(this)
//                .setTarget(viewTarget)
//                .setContentTitle("Show case example")
//                .setContentText("sdfalsdmflksmfasdfsdf")
//                .hideOnTouchOutside().singleShot(1)
//                .build();
        id = EntityID.getId();

        CopyDatabase copyDatabase = new CopyDatabase(MainActivity.this);

        try {
            copyDatabase.createdDatabase();
            copyDatabase.openDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        title = new String[]{ // title for navigation
                "Ruwa Jurai",
                "Aksara Lampung",
                "Bahasa",
                "Kesenian",
                "Makanan Khas",
                "Umpan Balik"

        };

        subtitle = new String[]{ // subtitle for navigation
                "Golongan Masyarakat Lampung",
                "Aksara Lampung",
                "Pembagian Bahasa",
                "Seni Tari, Alat Musik Tradisional",
                "Makanan Khas Lampung",
                "Saran tentang aplikasi ini"
        };

        icon = new int[]{ // icon for navigation
                R.drawable.tombol_ruwa_jurai,
                R.drawable.tombol_aksara2,
                R.drawable.tombol_bahasa,
                R.drawable.tombol_kesenian,
                R.drawable.tombol_makanan,
                R.drawable.tombol_feedback
        };

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        // set drawer
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // set data into adapter
        menuLeftListAdapater = new MenuLeftListAdapater(this, icon, subtitle, title);

        // set to adapter list menu
        drawerList.setAdapter(menuLeftListAdapater);

        // capture on clik list item
        drawerList.setOnItemClickListener(new DrawerItemClick());

        // enable action bar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.drawable.ic_launcher, R.drawable.ic_launcher) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);

        if (savedInstanceState != null) {
            selectItem(0);
        }
    }

    public void selectItem(int position) {
        // set Fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (position) {
            case 0: // replace fragment with layout below
                transaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out);
                transaction.replace(R.id.content_frame, adatIstiadatFragment);
                break;
            case 1: // replace fragment with layout below
                transaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out);
                transaction.replace(R.id.content_frame, aksaraFragment);
                break;

            case 2: // replace fragment with layout below
                transaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out);
                transaction.replace(R.id.content_frame, bahasaFragment);
                break;
            case 3: // replace fragment with layout below
                transaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out);
                transaction.replace(R.id.content_frame, kesenianFragment);
                break;
            case 4:// replace fragment with layout below
                transaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out);
                transaction.replace(R.id.content_frame, makananFragment);
                break;
            case 5: // replace fragment exist with layout belayout
                transaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out);
                transaction.replace(R.id.content_frame, feedbackFragment);
        }

        transaction.commit();    // commit
        drawerList.setItemChecked(position, true);

        // close drawer
        drawerLayout.closeDrawer(drawerList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            if (drawerLayout.isDrawerOpen(drawerList)) { // if menu open
                drawerLayout.closeDrawer(drawerList);
            } else {
                drawerLayout.openDrawer(drawerList); // if closed
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();   // sync toogle on restore
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig); // pass anything new configuration
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectItem(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EntityID.setId(0);
    }

    public class DrawerItemClick implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            selectItem(position);
        }
    }
}
