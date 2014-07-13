package com.souttab.budayalampung;

import com.souttab.budayalampung.adapter.AksaraViewAdapter;
import com.souttab.budayalampung.database.DatabaseUtil;
import com.souttab.budayalampung.entity.Entity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockFragment;

import java.util.List;

/**
 * Created with IntelliJ IDEA for
 * User: andi yulistyo
 * Date: 11/1/13
 * Time: 10:20 PM
 * Task: Skripsiku
 * source code ini bersifat open source
 * siapa saja bisa menggunakannaya untuk
 * tujuan belajar
 */
public class AksaraFragment extends SherlockFragment {

    private AksaraViewAdapter viewAdapter;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aksara, container, false);

        listView = (ListView) view.findViewById(R.id.listViewAksara);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSherlockActivity().getSupportActionBar().setTitle("Aksara Lampung");

        new GetData().execute();
    }


    private class GetData extends AsyncTask<Object, Object, List<Entity>> {

        DatabaseUtil databaseUtil = new DatabaseUtil(getActivity().getApplicationContext());

        @Override
        protected List<Entity> doInBackground(Object... objects) {
            databaseUtil.open();
            return databaseUtil.getAllAksara();
        }

        @Override
        protected void onPostExecute(List<Entity> entities) {

            viewAdapter = new AksaraViewAdapter(entities, getSherlockActivity().getApplicationContext());
            listView.setAdapter(viewAdapter);

        }
    }
}
