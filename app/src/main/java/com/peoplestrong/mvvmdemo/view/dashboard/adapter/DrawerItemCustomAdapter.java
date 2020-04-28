package com.peoplestrong.mvvmdemo.view.dashboard.adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.peoplestrong.mvvmdemo.R;
import com.peoplestrong.mvvmdemo.view.dashboard.model.DataModel;

import org.w3c.dom.Text;

public class DrawerItemCustomAdapter extends ArrayAdapter<DataModel> {

    Context mContext;
    int layoutResourceId;
    DataModel data[] = null;

    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, DataModel[] data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);
        DataModel folder = data[position];
        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
        TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);
        RelativeLayout rl_header=(RelativeLayout)listItem.findViewById(R.id.rl_header);
        RelativeLayout rl_item=(RelativeLayout)listItem.findViewById(R.id.rl_item);
      ImageView  iv_userImage=(ImageView)listItem.findViewById(R.id.iv_userImage);
        TextView txtName=(TextView)listItem.findViewById(R.id.txtName);
        TextView txtMailId=(TextView)listItem.findViewById(R.id.txtMailId);
        if(position==0){
            rl_header.setVisibility(View.VISIBLE);
            rl_item.setVisibility(View.GONE);
        }else {
            rl_header.setVisibility(View.GONE);
            rl_item.setVisibility(View.VISIBLE);
            imageViewIcon.setImageResource(folder.icon);
            textViewName.setText(folder.name);
        }





        return listItem;
    }
}

