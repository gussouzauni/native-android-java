package com.example.ilhacisneclube.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.activity.CadastroCampeonato;
import com.example.ilhacisneclube.adaptador.AdapterCampeonato;
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


public class CampeonatoFragment extends Fragment {

    //FIREBASE
     DatabaseReference databaseReference;
     FirebaseDatabase mFirebasedatabase;
    //VARIÁVEIS
    private ArrayList<Campeonato> campeonatos = new ArrayList<>();
    private AdapterCampeonato adapterCampeonato;
    private RecyclerView recyclerViewCampeonato;
    private FloatingActionButton btnAdicionar;
    public static List<String> ids = new ArrayList<>(); //Lista de idsTimes do IDCampeonato clicado do recyclerView;


    @Nullable
    @Override //Infla fragmento na tela
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.campeonato_fragment, container, false); //Infla fragmento na tela]
        return view;
    }

    @Override   //É chamado antes do onCreateView, faz todas as operacoes de back end da tela
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Firebase
        mFirebasedatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebasedatabase.getReference();
        recuperarCampeonato();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inicializarComponentes();
        botaoAdicionar();
        acessarTimeCampeonato();
}
    private void botaoAdicionar() {
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), CadastroCampeonato.class);
                startActivity(i);
            }
        });
    }

    private void recuperarCampeonato(){ //Recupera dados do firebase, da tabela "Campeonato"
        databaseReference.child("Campeonato").child(usuarioLogado()).addValueEventListener(new ValueEventListener() { //Instanciei a classe cadastroCampeonatoFragment e utilizei o método que estava dentro dela "usuarioLogado"
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Campeonato campeonato1 = data.getValue(Campeonato.class);
                    campeonatos.add(campeonato1);
                    //Pego os IDs dos times do ID campeonato e passo nesse array de ids;
                    ids.add(data.getKey());
                }

                adapterCampeonato.notifyDataSetChanged(); //Avisa para o adapter que as atualizações mudaram;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //Quando clicar no item da lista do campeonato ele vai transferir para outro fragmento
    private void acessarTimeCampeonato() {
        recyclerViewCampeonato.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerViewCampeonato, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                 String id = ids.get(position); //Passo a posicao do item da minha lista clicado;
                 CampeonatoTimeFragment fragment = new CampeonatoTimeFragment();
                 //Envio o array de IDs de times deste campeonato para outro fragment;
                 Bundle bundle = new Bundle();
                 FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                 bundle.putString("id", id);
                 fragment.setArguments(bundle);
                 fragmentTransaction.replace(R.id.fragment_container, fragment);
                 fragmentTransaction.commit();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));
    }

    private void inicializarComponentes() {
        btnAdicionar = getView().findViewById(R.id.btn_ir_adicionar_campeonato);
        //Recycler
        recyclerViewCampeonato = getView().findViewById(R.id.recycler_campeonato);
        recyclerViewCampeonato.setLayoutManager((new LinearLayoutManager(getContext())));
        //Adapter
        adapterCampeonato = new AdapterCampeonato(campeonatos);
        recyclerViewCampeonato.setAdapter(adapterCampeonato);
        //Toolbar
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar_campeonato_fragment);
        toolbar.setTitle("Campeonato");
    }
}

