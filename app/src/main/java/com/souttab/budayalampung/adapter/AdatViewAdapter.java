package com.souttab.budayalampung.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
        TextView textViewNama;
        ImageView imageView;
        Bitmap bitmap;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHold hold;

        if (view == null) {
            hold = new ViewHold();

            view = layoutInflater.inflate(R.layout.list_item_adat_istiadat_fragment, viewGroup, false);

            hold.imageView = (ImageView) view.findViewById(R.id.imageViewItemAdatIstiadat);
            hold.textViewNama = (TextView) view.findViewById(R.id.textViewNamaAdatIstiadat);

            view.setTag(hold);
        } else {
            hold = (ViewHold) view.getTag();
        }

        // for resize image
        hold.bitmap = BitmapFactory.decodeByteArray(entityList.get(position).getGambar(), 0, entityList.get(position).getGambar().length);

        hold.imageView.setImageBitmap(Bitmap.createScaledBitmap(hold.bitmap, 200, 200, false));
        hold.textViewNama.setText(entityList.get(position).getTitle());

        return view;
    }
}
