package com.nareshit.recyclerview_book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private final Context mContext;
    private final ArrayList<ExampleItem> mExampleList;

    public ExampleAdapter(Context context,ArrayList<ExampleItem> exampleList){
        mContext=context;
        mExampleList=exampleList;
    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.example_item,parent,false);
        return new ExampleViewHolder(v);

   }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem=mExampleList.get(position);

        String imageUrl=currentItem.getmImageUrl();
        String bookTitle=currentItem.getmTitle();
        String bookAuthors=currentItem.getmAuthors();

        holder.mTextViewTitle.setText(bookTitle);
        holder.mTextViewAuthors.setText(bookAuthors);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);
        Glide.with(mContext).load(currentItem.getmImageUrl()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextViewTitle;
        public TextView mTextViewAuthors;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.image_view);
            mTextViewTitle=itemView.findViewById(R.id.text_view_title);
            mTextViewAuthors=itemView.findViewById(R.id.text_view_authors);
        }

    }

}
