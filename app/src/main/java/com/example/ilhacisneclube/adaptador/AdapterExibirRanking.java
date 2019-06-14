package com.example.ilhacisneclube.adaptador;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.model.Time;

import java.util.ArrayList;

public class AdapterExibirRanking extends RecyclerView.Adapter<AdapterExibirRanking.ViewHolderRanking> {

    ArrayList<Time> rankings;

    public AdapterExibirRanking(ArrayList<Time> rankings) {
        this.rankings = rankings;
    }

    @NonNull
    @Override
    public ViewHolderRanking onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_exibir_ranking, viewGroup, false);
        return new ViewHolderRanking(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRanking viewHolderRanking, int posicao) {
        Time times = rankings.get(posicao);
        viewHolderRanking.nomeTime.setText(times.getNomeTime());
    }

    @Override
    public int getItemCount() {
        return rankings.size();
    }
    public class ViewHolderRanking extends RecyclerView.ViewHolder {

        private TextView nomeVitorias, nomeTime, qtdVitorias;
        public ViewHolderRanking(@NonNull View itemView) {
            super(itemView);

            nomeVitorias = itemView.findViewById(R.id.text_view_vitoria_adapter_cadastro_ranking);
            nomeTime = itemView.findViewById(R.id.text_view_nome_time_cadastrar_ranking_adapter);
            qtdVitorias = itemView.findViewById(R.id.text_view_qtd_vitorias_adapter_exibir_ranking);
        }
    }
}
