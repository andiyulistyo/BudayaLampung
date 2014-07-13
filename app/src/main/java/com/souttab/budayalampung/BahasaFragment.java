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
import com.souttab.budayalampung.adapter.BahasaViewAdapter;
import com.souttab.budayalampung.database.DatabaseUtil;
import com.souttab.budayalampung.detail.DetailFragmentBahasa;
import com.souttab.budayalampung.entity.Entity;

import java.util.List;

/**
 * Created with IntelliJ IDEA for
 * User: andi yulistyo
 * Date: 11/1/13
 * Time: 10:05 PM
 * Task: Skripsiku
 * source code ini bersifat open source
 * siapa saja bisa menggunakannaya untuk
 * tujuan belajar
 */
public class BahasaFragment extends SherlockFragment implements AdapterView.OnItemClickListener {

    private ListView listView;

    private BahasaViewAdapter bahasaViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bahasa, container, false);

        listView = (ListView) view.findViewById(R.id.listviewBahasa);
        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSherlockActivity().getSupportActionBar().setTitle("Bahasa");

        new GetData().execute();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(getSherlockActivity().getApplicationContext(), DetailFragmentBahasa.class);
//        intent.putExtra("id", l + 1);
//        startActivity(intent);
//        getSherlockActivity().finish();
    }


    class GetData extends AsyncTask<Long, Object, List<Entity>> {

        DatabaseUtil databaseUtil = new DatabaseUtil(getSherlockActivity().getApplicationContext());

        @Override
        protected List<Entity> doInBackground(Long... longs) {
            databaseUtil.open();
            return databaseUtil.getAllBahasa();
        }

        @Override
        protected void onPostExecute(List<Entity> entities) {
            bahasaViewAdapter = new BahasaViewAdapter(getSherlockActivity().getApplicationContext(), entities);
            listView.setAdapter(bahasaViewAdapter);
        }
    }
}
