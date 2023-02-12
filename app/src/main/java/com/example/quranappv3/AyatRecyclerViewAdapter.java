package com.example.quranappv3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AyatRecyclerViewAdapter extends RecyclerView.Adapter<AyatRecyclerViewAdapter.ViewHolder> {

    Context context;
    List<Ayat> ayats;

    public AyatRecyclerViewAdapter(Context context, List<Ayat> ayats) {
        this.context = context;
        this.ayats = ayats;
    }

    @NonNull
    @Override
    public AyatRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ayat_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AyatRecyclerViewAdapter.ViewHolder holder, int position) {
        Ayat a = ayats.get(position);
        holder.arabic.setText(a.ArabicText);
        holder.urdu.setText(a.UrduText);
    }

    @Override
    public int getItemCount() {
        return ayats.size();
    }

    public void setAyats(List<Ayat> list) {
        this.ayats = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView arabic, urdu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            arabic = itemView.findViewById(R.id.tvArabictext);
            urdu = itemView.findViewById(R.id.tvUrduText);
        }
    }
}
