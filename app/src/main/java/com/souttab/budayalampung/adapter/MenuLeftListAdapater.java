package com.souttab.budayalampung.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.souttab.budayalampung.R;

/**
 * Created with IntelliJ IDEA.
 * User: andi
 * Date: 10/19/13
 * Time: 11:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class MenuLeftListAdapater extends BaseAdapter {

    private String[] title;
    private String[] subTitle;
    private int[] icon;

    private LayoutInflater inflater;
    private Context context;

    public MenuLeftListAdapater(Context context, int[] icon, String[] subTitle, String[] title) {
        this.context = context;
        this.icon = icon;
        this.subTitle = subTitle;
        this.title = title;
    }

    @Override
    public int getCount() { // get length title
        return title.length;
    }

    @Override
    public Object getItem(int position) { // get title position
        return title[position];
    }

    @Override
    public long getItemId(int position) { //
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        TextView textViewTitle, textViewSubtitle;
        ImageView iconImage;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // set inflate

        View view = inflater.inflate(R.layout.list_item_menu_left, parent, false);

        // get location variable
        textViewSubtitle = (TextView) view.findViewById(R.id.subtitle);
        textViewTitle = (TextView) view.findViewById(R.id.title);
        iconImage = (ImageView) view.findViewById(R.id.icon);

        // set result
        textViewSubtitle.setText(subTitle[position]);
        textViewTitle.setText(title[position]);
        iconImage.setImageResource(icon[position]);

        return view;
    }
}
