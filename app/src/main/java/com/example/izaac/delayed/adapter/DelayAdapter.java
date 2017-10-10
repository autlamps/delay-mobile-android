package com.example.izaac.delayed.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.izaac.delayed.R;
import com.example.izaac.delayed.models.ListItem;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by izaac on 4/10/2017.
 */

public class DelayAdapter extends RecyclerView.Adapter<DelayAdapter.DelayHolder>{

    private List<ListItem> listData;
    private LayoutInflater layoutInflater;
    public static int recylerListTripAdapterLocation;

    private ItemClickCallBack itemClickCallBack;

    public interface ItemClickCallBack {
        void onItemClick(int p);
    }

    public void setItemClickCallBack (final ItemClickCallBack itemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack;
    }

    public DelayAdapter(List<ListItem> listData, Context c) {
        this.layoutInflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    @Override
    public DelayHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.list_item, parent, false);


        return new DelayHolder(view);
    }

    @Override
    public void onBindViewHolder(DelayHolder holder, int position) {

        ListItem item = listData.get(position);
        holder.title.setText(item.getTitle());
        holder.icon.setImageResource(item.getImageResId());

    }

    @Override
    public int getItemCount() {

        return listData.size();
    }

    class DelayHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private ImageView icon;
        private View container;

        public DelayHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.item_text);
            icon  = (ImageView) itemView.findViewById(R.id.im_item_icon);
            container = (View) itemView.findViewById(R.id.cont_item_root);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.cont_item_root) {
               itemClickCallBack.onItemClick(getAdapterPosition());
            }
        }
    }
}
