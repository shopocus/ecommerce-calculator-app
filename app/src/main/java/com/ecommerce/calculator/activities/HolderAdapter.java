package com.ecommerce.calculator.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.ColorSpace.Model;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ecommerce.calculator.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import com.ecommerce.calculator.models.menu;

public class HolderAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<menu> menu;

    public HolderAdapter(Context c, ArrayList<menu> menu){
        this.c = c;
        this.menu = menu;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int position) {
        myHolder.mTitle.setText(menu.get(position).getTitle());
        myHolder.mImageView.setImageResource(menu.get(position).getImg());
//
//        myHolder.setItemClickListener(new itemClick() {
//            @Override
//            public void onItemClickListener(View v, int position) {
//                String gtitle = menu.get(position).getTitle();
//                BitmapDrawable bitmapDrawable = (BitmapDrawable)myHolder.mImageView.getDrawable();
//
//                Bitmap bitmap = bitmapDrawable.getBitmap();
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//
//                byte[] bytes = stream.toByteArray();
//                Intent intent = new Intent(c, FragmentSelection.class);
//                intent.putExtra("iTitle", gtitle);
//                intent.putExtra("iImage", bytes);
//                c.startActivity(intent);
//            }
//        });

        myHolder.setItemClickListener(new itemClick() {
            @Override
            public void onItemClickListener(View v, int position) {
                if (menu.get(position).getTitle().equals("Meesho")){
                    Intent intent = new Intent(c , FragmentSelection.class);
                    c.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }
}
