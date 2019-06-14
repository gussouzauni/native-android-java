package com.example.ilhacisneclube.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.activity.ExibirRankingTime;
import com.example.ilhacisneclube.adaptador.AdapterRanking;
import com.example.ilhacisneclube.model.Campeonato;
import com.example.ilhacisneclube.recycleritemclick.RecyclerItemClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.ilhacisneclube.activity.CadastroCampeonato.usuarioLogado;

public class RankingFragment extends Fragment {
    private ArrayList<Campeonato> vitorias = new ArrayList<>();
    private RecyclerView recyclerViewRanking;
    private AdapterRanking adapterRanking;
    private List<String> idsRanking = new ArrayList<>();
    DatabaseReference databaseReference;
    FirebaseDatabase mFirebasedatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ranking_fragment, container, false);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //Firebase
        mFirebasedatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebasedatabase.getReference();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inicializarComponentes();
        recuperarDados();
        acessarCadastroPontos();
    }



    private void inicializarComponentes() {
        //RecyclerView
        recyclerViewRanking = getView().findViewById(R.id.recycler_ranking);
        recyclerViewRanking.setLayoutManager(new GridLayoutManager(getContext(), 2));
        //Adapter
        adapterRanking = new AdapterRanking(vitorias);
        recyclerViewRanking.setAdapter(adapterRanking);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar_ranking_fragment);
        toolbar.setTitle("Ranking");
    }

    private void recuperarDados() {  //Recuperar dados do firebase para exibir na lista pontuação
        databaseReference.child("Campeonato").child(usuarioLogado()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()) {
                    Campeonato ranking1 = data.getValue(Campeonato.class);
                    vitorias.add(ranking1);
                    idsRanking.add(data.getKey());
                }

                adapterRanking.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void acessarCadastroPontos() {   //Acessa o fragmento de pontos de cada campeonato
        recyclerViewRanking.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerViewRanking, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String idPosicao = idsRanking.get(position);
                Log.v("idposicao", idPosicao);
                Intent intent = new Intent(getContext(), ExibirRankingTime.class);
                intent.putExtra("rankingSelecionado", idPosicao);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));
    }

}





