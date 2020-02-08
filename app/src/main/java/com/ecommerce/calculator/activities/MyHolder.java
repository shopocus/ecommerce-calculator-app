package com.ecommerce.calculator.activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecommerce.calculator.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView mImageView;
    TextView mTitle;
    itemClick itemClickListener;

    MyHolder(@NonNull View itemView){
        super(itemView);

        this.mImageView = itemView.findViewById(R.id.image);
        this.mTitle = itemView.findViewById(R.id.title);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClickListener(v, getLayoutPosition());
    }
    public void setItemClickListener(itemClick id){
        this.itemClickListener = id;
    }
}
