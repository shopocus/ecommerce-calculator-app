package com.ecommerce.calculator.holder;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.ecommerce.calculator.R;

public class TitleHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView textViewTitle;
    itemClick itemClickListener;

    public TitleHolder(View itemView) {
        super(itemView);
        textViewTitle = itemView.findViewById(R.id.title);
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
