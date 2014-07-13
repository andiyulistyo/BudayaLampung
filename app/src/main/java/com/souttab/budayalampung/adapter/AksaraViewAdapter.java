package com.souttab.budayalampung.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.souttab.budayalampung.R;
import com.souttab.budayalampung.entity.Entity;

import java.util.List;

/**
 * Created with IntelliJ IDEA for
 * User: andi yulistyo
 * Date: 11/5/13
 * Time: 10:37 PM
 * Task: Skripsiku
 * source code ini bersifat open source
 * siapa saja bisa menggunakannaya untuk
 * tujuan belajar
 */
public class AksaraViewAdapter extends BaseAdapter {

    private List<Entity> entityList;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public AksaraViewAdapter(List<Entity> entityList, Context context) {
        this.entityList = entityList;
        this.mContext = context;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return entityList.size();   // get data from array
    }

    @Override
    public Object getItem(int position) {
        return entityList.get(position);      // get data form the position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) { // get layout and set data to layout
        final ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();

            view = layoutInflater.inflate(R.layout.list_item_aksara_fragment, viewGroup, false);        // inflate the layout

            // variable reference
            holder.imageViewGambar = (ImageView) view.findViewById(R.id.imageViewItemAksara);
            holder.textViewNama = (TextView) view.findViewById(R.id.textViewNamaAksara);

            view.setTag(holder);
        } else holder = (ViewHolder) view.getTag();

        // set result data from array list
        holder.imageViewGambar.setImageBitmap(entityList.get(position).getGambar());
        holder.textViewNama.setText(entityList.get(position).getTitle());


        return view;
    }

    class ViewHolder {
        TextView textViewNama;
        ImageView imageViewGambar;
    }
}
