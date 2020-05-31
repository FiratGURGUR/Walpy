package com.firatg.walpy.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firatg.walpy.R;
import com.firatg.walpy.model.Item;


public class FavWaalAdapter extends ListAdapter<Item, FavWaalAdapter.WaalViewHolder> {

    private OnItemClickListener listener;
    public FavWaalAdapter() {
        super(USER_COMPARATOR);
    }

    @NonNull
    @Override
    public WaalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.waal_item, parent, false);
        return new WaalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaalViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    class WaalViewHolder extends RecyclerView.ViewHolder {
        private ImageView photo;

        public WaalViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.imageView);


        }

        void bind(Item waal) {
            Glide.with(itemView.getContext()).load(waal.getUrl()).into(photo);
        }

    }

    private static final DiffUtil.ItemCallback<Item> USER_COMPARATOR = new DiffUtil.ItemCallback<Item>() {
        @Override
        public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem == newItem;
        }

    };

}
