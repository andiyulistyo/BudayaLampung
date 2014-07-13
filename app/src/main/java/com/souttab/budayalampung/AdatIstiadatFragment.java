package com.souttab.budayalampung;

import com.souttab.budayalampung.adapter.AdatViewAdapter;
import com.souttab.budayalampung.database.DatabaseUtil;
import com.souttab.budayalampung.detail.DetailFragmentAdatIstiadat;
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
 * Created with IntelliJ IDEA.
 * User: andi
 * Date: 10/19/13
 * Time: 11:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class AdatIstiadatFragment extends SherlockFragment implements AdapterView.OnItemClickListener{

    private AdatViewAdapter adatViewAdapter;
    private ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adat_istiadat, container, false);

        listView = (ListView) view.findViewById(R.id.listviewAdatIstiadat);

        listView.setDividerHeight(0);
        listView.setDivider(null);

        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSherlockActivity().getSupportActionBar().setTitle("Adat Istiadat Lampung");  // set title

        new GetData().execute();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(getSherlockActivity(), DetailFragmentAdatIstiadat.class);
        intent.putExtra("id", l + 1);
        startActivity(intent);
    }

    private class GetData extends AsyncTask<Object, Object, List<Entity>> {

        DatabaseUtil databaseUtil = new DatabaseUtil(getSherlockActivity().getApplicationContext());

        @Override
        protected List<Entity> doInBackground(Object... objects) {
            databaseUtil.open();
            return databaseUtil.getAllAdatIstiadat();
        }

        @Override
        protected void onPostExecute(List<Entity> entities) {
            adatViewAdapter = new AdatViewAdapter(getSherlockActivity().getApplicationContext(), entities);
            listView.setAdapter(adatViewAdapter);
        }
    }
}
