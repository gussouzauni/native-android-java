package com.example.ilhacisneclube.adaptador;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.model.Time;

import java.util.ArrayList;

public class AdapterEditRankingTime extends RecyclerView.Adapter<AdapterEditRankingTime.ViewHolderEditRankingTime>{

    private ArrayList<Time> times;
    private Context context;

    public AdapterEditRankingTime(ArrayList<Time> times, Context context) {
        this.times = times;
        this.context = context;
    }

    public AdapterEditRankingTime(ArrayList<Time> times) {
        this.times = times;
    }


    @NonNull
    @Override
    public ViewHolderEditRankingTime onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_edit_ranking, viewGroup, false);
        return new ViewHolderEditRankingTime(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEditRankingTime viewHolderEditRankingTime, int posicao) {
        Time time = times.get(posicao);
        viewHolderEditRankingTime.nomeTime.setText(time.getNomeTime());
}

    @Override
    public int getItemCount() {
        return times.size();
    }


    public class ViewHolderEditRankingTime extends RecyclerView.ViewHolder {
        private TextView vitoria, nomeTime;
        private Spinner spinnerVitoria;
        public ViewHolderEditRankingTime(@NonNull View itemView) {
            super(itemView);

            nomeTime = itemView.findViewById(R.id.text_view_adapter_edit_time_ranking);
            vitoria = itemView.findViewById(R.id.text_view_edit_ranking);
            spinnerVitoria = itemView.findViewById(R.id.spinner_quantidade_vitorias_adapter_edit);

        }
    }
}
