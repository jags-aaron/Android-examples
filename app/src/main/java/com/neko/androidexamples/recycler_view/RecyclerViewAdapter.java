package com.neko.androidexamples.recycler_view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.neko.androidexamples.R;
import com.neko.androidexamples.list_view.Model;

import java.util.List;

/**
 * Created by N3K0 on 8/22/17.
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.HolderView> {

    private List<Model> dataset;
    private OnDeleteButtonClickedListener listener;

    class HolderView extends RecyclerView.ViewHolder {
        private TextView name, description;
        private ImageView image, deleteButton;

        HolderView(View view) {
            super(view);
            image = view.findViewById(R.id.ivItemCard);
            name = view.findViewById(R.id.tvNameCard);
            description = view.findViewById(R.id.tvDescriptionCard);
            deleteButton = view.findViewById(R.id.ivButtonDeleteCard);
        }
    }

    /**
     * Constructor
     * @param dataset items to be populated
     */
    RecyclerViewAdapter(List<Model> dataset, OnDeleteButtonClickedListener listener) {
        this.dataset = dataset;
        this.listener = listener;
    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        return new HolderView(itemView);
    }

    @Override
    public void onBindViewHolder(HolderView holder, int position) {
        Model model = dataset.get(position);
        holder.image.setImageResource(model.getImage());
        holder.name.setText(model.getName());
        holder.description.setText(model.getDescription());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            int position;

            @Override
            public void onClick(View view) {
                if(listener != null)
                    listener.onDeleteClicked(position);
            }

            View.OnClickListener setData(int position){
                this.position = position;
                return this;
            }
        }.setData(position));

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    /**
     * Callback to tell Activity when DeleteButton was clicked
     */
    interface OnDeleteButtonClickedListener {
        void onDeleteClicked(int position);
    }
}