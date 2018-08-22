package com.csc470.palla.githubquery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class sampleAdapter  extends RecyclerView.Adapter<sampleAdapter.sampleViewHolder> {

    private Context context;

    public sampleAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public sampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row,parent,false);
        return new sampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sampleViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class sampleViewHolder extends RecyclerView.ViewHolder{
        TextView names ;
        public sampleViewHolder(View itemView) {
            super(itemView);
            names = itemView.findViewById(R.id.name);
        }
    }
}
