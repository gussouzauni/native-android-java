package com.example.ilhacisneclube.adaptador;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.model.Campeonato;

import java.util.ArrayList;


public class AdapterCampeonato extends RecyclerView.Adapter<AdapterCampeonato.ViewHolderCampeonato> {

    private ArrayList<Campeonato> campeonatos;

    public AdapterCampeonato(ArrayList<Campeonato> campeonatos) {
        this.campeonatos = campeonatos;
    }

    @NonNull
    @Override
    public ViewHolderCampeonato onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_campeonato,viewGroup, false);
        return new ViewHolderCampeonato(view);

    }

    @Override //Método para pegar os dados e inserir no recycler, popular o recycler
    public void onBindViewHolder(@NonNull final ViewHolderCampeonato viewHolderCampeonato, int posicao) {
        final Campeonato campeonato = campeonatos.get(posicao);
        viewHolderCampeonato.nomeCampeonato.setText(campeonato.getNomeCampeonato());
        viewHolderCampeonato.nomeModalidade.setText(campeonato.getModalidadeCampeonato());
        viewHolderCampeonato.nomeDescricao.setText(campeonato.getDescricaoCampeonato());
        viewHolderCampeonato.quantidadeTimes.setText(campeonato.getQuantidadeTimes());
        viewHolderCampeonato.liderCampeonato.setText(campeonato.getLiderCampeonato());

        viewHolderCampeonato.acessarTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(viewHolderCampeonato.itemView.getContext(), "Times", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return campeonatos.size();
    }

    public class ViewHolderCampeonato extends RecyclerView.ViewHolder {

        private TextView nomeCampeonato, nomeDescricao, nomeModalidade, quantidadeTimes, liderCampeonato, acessarTime;

        public ViewHolderCampeonato(@NonNull View itemView) {
            super(itemView);

            nomeCampeonato = itemView.findViewById(R.id.text_view_nome_adapter);
            nomeDescricao = itemView.findViewById(R.id.text_view_descricao_adapter);
            nomeModalidade = itemView.findViewById(R.id.text_view_modalidade_adapter);
            quantidadeTimes = itemView.findViewById(R.id.text_view_qtd_times_adapter);
            acessarTime = itemView.findViewById(R.id.text_view_acessar_times);
            liderCampeonato = itemView.findViewById(R.id.text_view_nome_lider_adapter_campeonato);
        }
    }
}
