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
 * Date: 10/31/13
 * Time: 1:32 PM
 * Task: Skripsiku
 * <p/>
 * source code ini bersifat open source
 * siapa saja bisa menggunakannaya untuk
 * tujua belajar
 */
public class KesenianViewAdapter extends BaseAdapter {
    
    private Context mContext;
    private LayoutInflater layoutInflater;
    private List<Entity> entityList;

    public KesenianViewAdapter(Context context, List<Entity> entityList) {
        this.mContext = context;
        this.entityList = entityList;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return entityList.size();
    }

    @Override
    public Object getItem(int position) {
        return entityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHol {
        TextView textViewNama;
        ImageView imageView;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHol viewHol;

        if (view == null) {
            viewHol = new ViewHol();

            view = layoutInflater.inflate(R.layout.list_item_kesenian_fragment, viewGroup, false);

            viewHol.imageView = (ImageView) view.findViewById(R.id.imageViewItemKesenian);
            viewHol.textViewNama = (TextView) view.findViewById(R.id.textViewNamaKesenian);

            view.setTag(viewHol);
        } else {
            viewHol  = (ViewHol) view.getTag();
        }

        viewHol.textViewNama.setText(entityList.get(position).getTitle());
        viewHol.imageView.setImageBitmap(entityList.get(position).getGambar());

        return view;
    }
}