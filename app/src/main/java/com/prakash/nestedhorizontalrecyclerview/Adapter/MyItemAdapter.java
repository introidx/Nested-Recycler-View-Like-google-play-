package com.prakash.nestedhorizontalrecyclerview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prakash.nestedhorizontalrecyclerview.Interface.IItemClickListener;
import com.prakash.nestedhorizontalrecyclerview.R;
import com.prakash.nestedhorizontalrecyclerview.itemData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.MyViewHolder> {

    private Context context;
    private List<itemData> itemDataList;

    public MyItemAdapter(Context context, List<itemData> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_item,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.txt_item_titles.setText(itemDataList.get(i).getName());
        Picasso.get().load(itemDataList.get(i).getImage()).into(myViewHolder.img_item);

        myViewHolder.setiItemClickListener(new IItemClickListener() {
            @Override
            public void onItemClickListener(View view, int i) {
                Toast.makeText(context, ""+ itemDataList.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (itemDataList != null ? itemDataList.size() : 0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txt_item_titles;
        ImageView img_item;

        IItemClickListener iItemClickListener;

        public void setiItemClickListener(IItemClickListener iItemClickListener) {
            this.iItemClickListener = iItemClickListener;
        }

        public MyViewHolder (View itemView ){
            super(itemView);

            txt_item_titles= (TextView)itemView.findViewById(R.id.tvTitle);
            img_item= (ImageView) itemView.findViewById(R.id.itemImage);

           itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            iItemClickListener.onItemClickListener(v,getAdapterPosition());
        }
    }
}
