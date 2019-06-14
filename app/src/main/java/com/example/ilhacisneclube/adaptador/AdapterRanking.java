package com.example.ilhacisneclube.adaptador;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.model.Campeonato;

import java.util.ArrayList;


public class AdapterRanking extends RecyclerView.Adapter<AdapterRanking.ViewHolderRanking> {

    private ArrayList<Campeonato> rankings;

    public AdapterRanking(ArrayList<Campeonato> rankings) {
        this.rankings = rankings;
    }

    @NonNull
    @Override
    public ViewHolderRanking onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_ranking, viewGroup,false);
        return new ViewHolderRanking(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderRanking viewHolderRanking, int posicao) {
        Campeonato ranking = rankings.get(posicao);
        viewHolderRanking.nomeCampeonato.setText(ranking.getNomeCampeonato());
        viewHolderRanking.quantidadeTimes.setText(ranking.getQuantidadeTimes());
        viewHolderRanking.modalidadeCampeonato.setText(ranking.getModalidadeCampeonato());
        viewHolderRanking.acessarRanking.setOnClickListener(new View.OnClickListener() { //Insere o click no botão exibir;
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return rankings.size();
    }

    public class ViewHolderRanking extends RecyclerView.ViewHolder {


        private TextView acessarRanking, quantidadeTimes, nomeCampeonato, modalidadeCampeonato, timeCampeao, exibirLider, exibirTimes;

        public ViewHolderRanking(@NonNull View itemView) {
            super(itemView);

            acessarRanking = itemView.findViewById(R.id.text_view_acessar_ranking);
            quantidadeTimes = itemView.findViewById(R.id.text_view_quantidade_times_adapter_ranking);
            nomeCampeonato = itemView.findViewById(R.id.text_view_nome_campeonato_adapter_ranking);
            modalidadeCampeonato = itemView.findViewById(R.id.text_view_modalidade_campeonato_adapter_ranking);
            exibirTimes = itemView.findViewById(R.id.text_view_exibir_times_adapter_ranking);
            timeCampeao = itemView.findViewById(R.id.text_view_nome_time_campeao_adapter_ranking);

        }
    }
}
