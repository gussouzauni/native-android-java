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

public class AdapterExibirNomeTimeTabela extends RecyclerView.Adapter<AdapterExibirNomeTimeTabela.ViewHolderExibirNomeTimeTabela> {

    ArrayList<Time> times;

    public AdapterExibirNomeTimeTabela(ArrayList<Time> times) {
        this.times = times;
    }

    @NonNull
    @Override
    public ViewHolderExibirNomeTimeTabela onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_exibir_time_tabela, viewGroup, false);
       return new ViewHolderExibirNomeTimeTabela(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderExibirNomeTimeTabela viewHolderExibirNomeTimeTabela, int posicao) {
        Time time = times.get(posicao);
        viewHolderExibirNomeTimeTabela.nomeTime.setText(time.getNomeTime());
    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    public class ViewHolderExibirNomeTimeTabela extends RecyclerView.ViewHolder {

        private TextView nomeTime;

        public ViewHolderExibirNomeTimeTabela(@NonNull View itemView) {
            super(itemView);
            nomeTime = itemView.findViewById(R.id.text_view_exibir_nome_time_tabela_adapter);
        }
    }
}
