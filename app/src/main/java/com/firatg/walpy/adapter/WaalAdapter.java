package com.firatg.walpy.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firatg.walpy.R;
import com.firatg.walpy.api.data.WaalDataSource;
import com.firatg.walpy.model.PhotosItem;

public class WaalAdapter extends PagedListAdapter<PhotosItem, WaalAdapter.WaalViewHolder> {

    private OnItemClickListener listener;
    public WaalAdapter() {
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


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(v, getItem(position), position);
                    }
                }
            });
        }

        void bind(PhotosItem waal) {
            Glide.with(itemView.getContext()).load(waal.getSrc().getPortrait()).into(photo);
        }

    }

    private static final DiffUtil.ItemCallback<PhotosItem> USER_COMPARATOR = new DiffUtil.ItemCallback<PhotosItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull PhotosItem oldItem, @NonNull PhotosItem newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull PhotosItem oldItem, @NonNull PhotosItem newItem) {
            return oldItem == newItem;
        }

    };

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
