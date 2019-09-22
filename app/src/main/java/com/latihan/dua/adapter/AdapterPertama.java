package com.latihan.dua.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.latihan.dua.R;
import com.latihan.dua.modal.Modakr;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterPertama extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<Modakr>modak1s;


    public AdapterPertama(Context context, ArrayList<Modakr> modak1s) {
        this.context = context;
        this.modak1s = modak1s;
    }

    class MyadapterKu extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView text;

        public MyadapterKu(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageview);
            text = itemView.findViewById(R.id.text);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_activity, null);

        return new MyadapterKu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewholder, final int i) {
         Modakr modal = modak1s.get(i);
         Picasso.get().load(R.drawable.rog).into(((MyadapterKu) viewholder).image);
        ((MyadapterKu)viewholder).text.setText(modal.getTitle());

    }

    @Override
    public int getItemCount() {
        return modak1s.size();
    }
}
