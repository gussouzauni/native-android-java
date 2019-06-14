package com.example.ilhacisneclube.fragments;

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
import com.example.ilhacisneclube.adaptador.AdapterTabela;
import com.example.ilhacisneclube.model.Tabela;
import com.example.ilhacisneclube.recycleritemclick.RecyclerItemClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.ilhacisneclube.activity.CadastroCampeonato.usuarioLogado;

public class TabelaFragment extends Fragment {

    private ArrayList<Tabela> tabelas = new ArrayList<>();
    private RecyclerView recyclerViewTabela;
    private AdapterTabela adapterTabela;

    public List<String> idsTabela = new ArrayList<>();

    DatabaseReference databaseReference;
    FirebaseDatabase mFirebasedatabase;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tabela_fragment, container, false); //Infla o fragmento na tela
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //Firebase
        mFirebasedatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebasedatabase.getReference();
        recuperarCampeonato();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inicializarComponente();
        acessarTabela();
    }


    private void inicializarComponente() {
        recyclerViewTabela = getView().findViewById(R.id.recycler_view_tabela);
        recyclerViewTabela.setLayoutManager(new GridLayoutManager(getContext(), 2));
        //Adapter
        adapterTabela = new AdapterTabela(tabelas);
        recyclerViewTabela.setAdapter(adapterTabela);
        //Toolbar
        Toolbar toolbar = getView().findViewById(R.id.tabela_fragment);
        toolbar.setTitle("Tabela");
    }

    private void recuperarCampeonato() { //Recupera dados do firebase, da tabela "Campeonato"
        databaseReference.child("Campeonato").child(usuarioLogado()).addValueEventListener(new ValueEventListener() { //Instanciei a classe cadastroCampeonatoFragment e utilizei o método que estava dentro dela "usuarioLogado"
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    Tabela tabela1 = data.getValue(Tabela.class);
                    tabelas.add(tabela1);
                    idsTabela.add(data.getKey());  //Pega os ids dos campeonatos e passa lá em baixo no "acessar tabela;
                }
                adapterTabela.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void acessarTabela() { //Método que acessa tabela através do click que o usuario deu na lista;
        recyclerViewTabela.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerViewTabela, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String idPosicao = idsTabela.get(position);
                Log.v("ID", idPosicao);
                /*
                //Exibição da tabela, x4, x8, x16;
                int var = 4; //Colocar a quantidadeTimes aqui no lugar.. A tabela está setada na mão..
                if (var == 8) {
                    TabelaOitoFragment tabelaOitoFragment = new TabelaOitoFragment();
                    //Envio o array de IDs de times deste campeonato para outro fragment e troco de fragment;
                    Bundle bundle = new Bundle();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    bundle.putString("id", idPosicao);
                    tabelaOitoFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragment_container, tabelaOitoFragment);
                    fragmentTransaction.commit();
                }
                if (var == 4) {
                    TabelaQuatroFragment tabelaQuatroFragment = new TabelaQuatroFragment();
                    //Envio o array de IDs de times deste campeonato para outro fragment e troco de fragment;
                    Bundle bundle = new Bundle();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    bundle.putString("id", idPosicao);
                    tabelaQuatroFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragment_container, tabelaQuatroFragment);
                    fragmentTransaction.commit();
                }
                */

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

