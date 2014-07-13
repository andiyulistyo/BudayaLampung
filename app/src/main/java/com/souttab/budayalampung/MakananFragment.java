package com.souttab.budayalampung;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockFragment;
import com.souttab.budayalampung.adapter.MakananViewAdapter;
import com.souttab.budayalampung.database.DatabaseUtil;
import com.souttab.budayalampung.detail.DetailFragmentMakanan;
import com.souttab.budayalampung.entity.Entity;

import java.util.List;

/**
 * Created with IntelliJ IDEA for
 * <p/>
 * User: andi yulistyo
 * Date: 10/25/13
 * Time: 11:37 AM
 * Task: Skripsiku
 * <p/>
 * source code ini bersifat open source
 * siapa saja bisa menggunakannaya untuk
 * tujuan belajar
 */
public class MakananFragment extends SherlockFragment implements AdapterView.OnItemClickListener {

    private MakananViewAdapter viewAdapter;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_makanan, container, false);

        listView = (ListView) view.findViewById(R.id.listviewMakanan);

        listView.setDivider(null);
        listView.setDividerHeight(0);

        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSherlockActivity().getSupportActionBar().setTitle("Makanan Khas");


        new setViewAdapter().execute();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(getSherlockActivity(), DetailFragmentMakanan.class);
        intent.putExtra("id", l + 1);
        startActivity(intent);
        getSherlockActivity().overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
        getSherlockActivity().finish();
    }

    private class setViewAdapter extends AsyncTask<Object, Object, List<Entity>> {

        DatabaseUtil database = new DatabaseUtil(getSherlockActivity().getApplicationContext());

        @Override
        protected List<Entity> doInBackground(Object... objects) {
            database.open();
            return database.ListAllMakanan();
        }

        @Override
        protected void onPostExecute(List<Entity> makanans) {
            viewAdapter = new MakananViewAdapter(getSherlockActivity().getApplicationContext(), makanans);
            listView.setAdapter(viewAdapter);
        }
    }
}
