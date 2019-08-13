package com.example.ilhacisneclube.adaptador;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.model.Tabela;

import java.util.ArrayList;

public class AdapterTabela extends RecyclerView.Adapter<AdapterTabela.ViewHolderTabela> {

    private ArrayList<Tabela> tabelas;

    public AdapterTabela(ArrayList<Tabela> tabelas) {
        this.tabelas = tabelas;
    }


    @NonNull
    @Override
    public ViewHolderTabela onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_tabela, viewGroup, false);
        return new ViewHolderTabela(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTabela viewHolderTabela, int posicao) {
        Tabela tabela = tabelas.get(posicao);
        viewHolderTabela.nomeCampeonato.setText(tabela.getNomeCampeonato());
    }

    @Override
    public int getItemCount() {
        return tabelas.size();
    }

    public class ViewHolderTabela extends RecyclerView.ViewHolder {

        private TextView nomeCampeonato;

        public ViewHolderTabela(@NonNull View itemView) {
            super(itemView);

            nomeCampeonato = itemView.findViewById(R.id.text_view_nome_campeonato_adapter_tabela);

        }
    }

}