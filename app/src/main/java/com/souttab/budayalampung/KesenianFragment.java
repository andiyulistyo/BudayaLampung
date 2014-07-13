package com.souttab.budayalampung;

import com.souttab.budayalampung.adapter.KesenianViewAdapter;
import com.souttab.budayalampung.database.DatabaseUtil;
import com.souttab.budayalampung.detail.DetailFragmentKesenian;
import com.souttab.budayalampung.entity.Entity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;

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

public class KesenianFragment extends SherlockFragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private KesenianViewAdapter kesenianViewAdapter;
    private DetailFragmentKesenian detailFragmentKesenian = new DetailFragmentKesenian();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kesenian, container, false);

        listView = (ListView) view.findViewById(R.id.listviewKesenian);

        listView.setDividerHeight(0);
        listView.setDivider(null);

        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSherlockActivity().getSupportActionBar().setTitle("Kesenian Lampung");

        new getData().execute();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(getSherlockActivity(), DetailFragmentKesenian.class);
        intent.putExtra("id", l + 1);
        startActivity(intent);
        getSherlockActivity().finish();

    }

    private class getData extends AsyncTask<Object, Object, List<Entity>> {

        DatabaseUtil databaseUtil = new DatabaseUtil(getSherlockActivity().getApplicationContext());

        @Override
        protected List<Entity> doInBackground(Object... objects) {
            databaseUtil.open();
            return databaseUtil.getAllKesenian();
        }

        @Override
        protected void onPostExecute(List<Entity> entities) {
            kesenianViewAdapter = new KesenianViewAdapter(getSherlockActivity().getApplicationContext(), entities);
            listView.setAdapter(kesenianViewAdapter);
        }
    }
}
