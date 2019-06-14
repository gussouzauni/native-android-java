package com.example.ilhacisneclube.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.adaptador.AdapterTimeCampeonato;
import com.example.ilhacisneclube.model.Time;

import java.util.ArrayList;

import static com.example.ilhacisneclube.activity.CadastroCampeonato.usuarioLogado;

public class CampeonatoTimeFragment extends Fragment {

    private RecyclerView recyclerViewExibirTime;

    private ArrayList<Time> times = new ArrayList<>();
    private AdapterTimeCampeonato adapterTimeCampeonato;
    private String idTimesLista;
    DatabaseReference databaseReference;
    FirebaseDatabase mFirebasedatabase;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.campeonato_time_fragment, container, false);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Firebase
        mFirebasedatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebasedatabase.getReference();

        //Recebe os dados vindo do fragment com o ID dos times do campeonato que foi clicado no recyclerView no CampeonatoFragment;
        Bundle mBundle = new Bundle();
        if(mBundle != null) {
            mBundle = getArguments();
            idTimesLista = mBundle.getString("id");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inicializarComponente();
        recuperarTime();
    }

    private void inicializarComponente() {
        recyclerViewExibirTime = getView().findViewById(R.id.recycler_exibir_time);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerViewExibirTime.setLayoutManager(manager);
        adapterTimeCampeonato = new AdapterTimeCampeonato(times);
        recyclerViewExibirTime.setAdapter(adapterTimeCampeonato);
        Toolbar toolbar = getView().findViewById(R.id.toolbar_campeonato_time_fragment);
        toolbar.setTitle("Times");
    }

        private void recuperarTime () {
            databaseReference.child("Time").child(usuarioLogado()).child(idTimesLista).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Time time1 = data.getValue(Time.class);
                            times.add(time1);
                        }
                        adapterTimeCampeonato.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });

    }

}


