package com.example.ilhacisneclube.adaptador;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.model.Time;

import java.util.ArrayList;

public class AdapterTimeCampeonato extends RecyclerView.Adapter<AdapterTimeCampeonato.ViewHolderVisualizarTime> {

    ArrayList<Time> times;

    public AdapterTimeCampeonato(ArrayList<Time> times) {
        this.times = times;
    }

    @NonNull
    @Override
    public ViewHolderVisualizarTime onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_visualizar_time, viewGroup, false);
        return new ViewHolderVisualizarTime(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderVisualizarTime viewHolderVisualizarTime, int posicao) {
            Time time = times.get(posicao);
            viewHolderVisualizarTime.nomeTime.setText(time.getNomeTime());
    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    public class ViewHolderVisualizarTime extends RecyclerView.ViewHolder {

        private TextView nomeTime;

        public ViewHolderVisualizarTime(@NonNull View itemView) {
            super(itemView);

            nomeTime = itemView.findViewById(R.id.text_view_nome_time_adapter_visualizar_time);

        }
    }

}
