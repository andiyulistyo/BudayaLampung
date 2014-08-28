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
 * Time: 11:09 PM
 * Task: Skripsiku
 * source code ini bersifat open source
 * siapa saja bisa menggunakannaya untuk
 * tujuan belajar
 */
public class BahasaViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private List<Entity> entityList;

    public BahasaViewAdapter(Context context, List<Entity> entityList) {
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
        TextView textViewDeskri;
        ImageView imageView;
        Bitmap bitmap;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHol hol;

        if (view == null) {
            hol = new ViewHol();

            view = layoutInflater.inflate(R.layout.list_item_bahasa_fragment, viewGroup, false);

//            hol.textViewDeskri = (TextView) view.findViewById(R.id.textViewDeskBahasa);
            hol.imageView = (ImageView) view.findViewById(R.id.imageViewItemBahasa);

            view.setTag(hol);
        } else {
            hol = (ViewHol) view.getTag();
        }

        hol.bitmap = BitmapFactory.decodeByteArray(entityList.get(position).getGambar(), 0, entityList.get(position).getGambar().length);
        hol.imageView.setImageBitmap(Bitmap.createScaledBitmap(hol.bitmap, 200, 200, false));
//        hol.textViewDeskri.setText(entityList.get(position).getDeskription());

        return view;
    }
}
