package com.neko.androidexamples.list_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.neko.androidexamples.R;

import java.util.ArrayList;

/**
 * Created by N3K0 on 8/22/17.
 */

public class ListViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Model> modelsToBeFilled;
    private OnListViewDeleteButtonClicked listener;

    ListViewAdapter(ArrayList<Model> modelsToBeFilled, Context context) {
        this.modelsToBeFilled = modelsToBeFilled;
        inflater = LayoutInflater.from(context);
    }

    ListViewAdapter(ArrayList<Model> modelsToBeFilled, Context context,
                    OnListViewDeleteButtonClicked listener) {
        this.modelsToBeFilled = modelsToBeFilled;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    private class ViewHolder {
        ImageView image;
        ImageView deleteButton;
        TextView name;
        TextView description;
    }

    @Override
    public int getCount() {
        return modelsToBeFilled.size();
    }

    @Override
    public Object getItem(int position) {
        return modelsToBeFilled.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {

            //new Layout instance
            convertView = inflater.inflate(R.layout.row_item, null);
            holder = new ViewHolder();

            //init view Components
            holder.image = convertView.findViewById(R.id.ivItemRow);
            holder.deleteButton = convertView.findViewById(R.id.ivButtonDelete);
            holder.name = convertView.findViewById(R.id.tvNameRow);
            holder.description = convertView.findViewById(R.id.tvDescriptionRow);

            convertView.setTag(holder);
        } else {
            //reset Views
            holder = (ViewHolder) convertView.getTag();
        }

        //setting values
        holder.image.setImageResource(modelsToBeFilled.get(position).getImage());
        holder.name.setText(modelsToBeFilled.get(position).getName());
        holder.description.setText(modelsToBeFilled.get(position).getDescription());

        // Delete Item Listener
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            int position;

            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onDeleteButtonClicked(position);
            }

            View.OnClickListener setData(int position) {
                this.position = position;
                return this;
            }
        }.setData(position));


        return convertView;
    }

    interface OnListViewDeleteButtonClicked {
        void onDeleteButtonClicked(int position);
    }
}

