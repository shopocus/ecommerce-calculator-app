package com.ecommerce.calculator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.view.animation.Animation;
import androidx.annotation.NonNull;
import android.view.animation.AnimationUtils;
import com.ecommerce.calculator.R;
import com.ecommerce.calculator.models.output;
import java.util.ArrayList;

public class OutputListAdapter extends ArrayAdapter<output> {

    private Context mcontext;
    int mresource;
    private int lastPosition = -1;

    private static class ViewHolder {
        TextView title;
        TextView answer;
    }

    public OutputListAdapter(Context context, int resource, @NonNull ArrayList<output> objects) {
        super(context, resource, objects);
        mcontext = context;
        mresource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        String title = getItem(position).getTitle();
        String answer = getItem(position).getAnswer();

        output output = new output(title,answer);
        final View result;
        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mcontext);
            convertView = inflater.inflate(mresource, parent, false);
            holder= new ViewHolder();
            holder.title = convertView.findViewById(R.id.title);
            holder.answer = convertView.findViewById(R.id.answer);
            result = convertView;
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mcontext, (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.title.setText(output.getTitle());
        holder.answer.setText(output.getAnswer());

        return convertView;
    }
}