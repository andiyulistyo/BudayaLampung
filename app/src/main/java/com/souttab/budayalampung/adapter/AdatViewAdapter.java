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
 * Date: 11/1/13
 * Time: 11:21 PM
 * Task: Skripsiku
 * source code ini bersifat open source
 * siapa saja bisa menggunakannaya untuk
 * tujuan belajar
 */
public class AdatViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private List<Entity> entityList;

    public AdatViewAdapter(Context context, List<Entity> entityList) {
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

    class ViewHold {
        TextView textViewNama, textViewDeskrip;
        ImageView imageView;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHold hold;

        if (view == null) {
            hold = new ViewHold();

            view = layoutInflater.inflate(R.layout.list_item_adat_istiadat_fragment, viewGroup, false);

            hold.imageView = (ImageView) view.findViewById(R.id.imageViewItemAdatIstiadat);
            hold.textViewDeskrip = (TextView) view.findViewById(R.id.textViewDeskAdatIstiadat);
            hold.textViewNama = (TextView) view.findViewById(R.id.textViewNamaAdatIstiadat);

            view.setTag(hold);
        } else {
            hold = (ViewHold) view.getTag();
        }

        String des = (entityList.get(position).getDeskription());

        if (des.length() > 90) {
            des = des.substring(0, 90) + "...";
        }

        hold.textViewDeskrip.setText(des);
        hold.imageView.setImageBitmap(entityList.get(position).getGambar());
        hold.textViewNama.setText(entityList.get(position).getTitle());


        return view;
    }
}
