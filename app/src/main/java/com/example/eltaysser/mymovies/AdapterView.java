package com.example.eltaysser.mymovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterView extends RecyclerView.Adapter<AdapterView.HoldMyView> {
    private final ArrayList<LayoutContent> arrayList;
    private final Context context;
    private final OnItemClickListener mOnItemClickListener;

    public AdapterView(ArrayList<LayoutContent> arrayList, Context context,OnItemClickListener mOnItemClickListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.mOnItemClickListener=mOnItemClickListener;
    }

    @NonNull
    @Override
    public HoldMyView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.show_items,viewGroup,false);
        return new HoldMyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoldMyView holdMyView, final int position) {
        String path="https://image.tmdb.org/t/p/w500/";
        Picasso.get().load(path+arrayList.get(position).getImageUrlForPicasso()).into(holdMyView.imageView);
        holdMyView.tName.setText(arrayList.get(position).getMovieName());
        holdMyView.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size() ;
    }

    class HoldMyView extends RecyclerView.ViewHolder{
    final ImageView imageView;
    final TextView tName;
    HoldMyView(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.imageView);
        tName=itemView.findViewById(R.id.moviename);
    }
}

    public interface OnItemClickListener {
         void onItemClick(int position);
    }

}