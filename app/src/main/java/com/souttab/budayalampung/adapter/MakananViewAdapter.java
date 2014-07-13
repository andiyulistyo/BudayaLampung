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
 * <p/>
 * User: andi yulistyo
 * Date: 10/19/13
 * Time: 7:02 PM
 * Task: Skripsiku
 * <p/>
 * source code ini bersifat open source
 * siapa saja bisa menggunakannaya untuk
 * tujua belajar
 */
public class MakananViewAdapter extends BaseAdapter {

    private List<Entity> makananList;
    private LayoutInflater layoutInflater;
    private Context mcontext;

    public MakananViewAdapter(Context context, List<Entity> makananList) {
        this.mcontext = context;
        this.makananList = makananList;
        layoutInflater = LayoutInflater.from(mcontext);
    }

    @Override
    public int getCount() {  // count title length
        return makananList.size();
    }

    @Override
    public Object getItem(int position) {
        return makananList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.list_item_makanan_fragment, viewGroup, false);  // inflate layout list item


            holder.textViewNama = (TextView) view.findViewById(R.id.textViewNama);
            holder.imageView = (ImageView) view.findViewById(R.id.imageViewIcon);
            holder.textViewDeskripsi = (TextView) view.findViewById(R.id.textViewDesk);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();

        }

        String des = makananList.get(position).getDeskription();

        // set result
        holder.textViewNama.setText(makananList.get(position).getTitle());
        holder.imageView.setImageBitmap(makananList.get(position).getGambar());
        holder.textViewDeskripsi.setText(des.substring(0, 60) + "...");
        return view;
    }

    private class ViewHolder {
        TextView textViewNama, textViewDeskripsi;
        ImageView imageView;
    }


}
