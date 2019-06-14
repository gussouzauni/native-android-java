package com.example.ilhacisneclube.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.adaptador.AdapterExibirRanking;
import com.example.ilhacisneclube.model.Time;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.ilhacisneclube.activity.CadastroCampeonato.usuarioLogado;

public class ExibirRankingTime extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase mFirebasedatabase;
    AdapterExibirRanking adapterExibirRanking;
    ArrayList<Time> times = new ArrayList<>();
    RecyclerView recyclerViewExibirRanking;
    private String idRanking;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exibir_ranking_time_activity);
        //FIREBASE
        mFirebasedatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebasedatabase.getReference();
        //Id do time clicado na recyclerView;
        idRanking = getIntent().getExtras().getString("rankingSelecionado");
        inicializarComponente();
        recuperarTimeRanking();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar, menu);
        menu.findItem(R.id.actionCheck).setVisible(false);
        menu.findItem(R.id.actionSair).setVisible(false);
        menu.findItem(R.id.actionProximo).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionEdit:
                Intent i = new Intent (ExibirRankingTime.this, EditRanking.class);
                i.putExtra("IdRanking", idRanking);
                startActivity(i);
                return true;
        }
        return false;
    }


    private void inicializarComponente() {
        recyclerViewExibirRanking = findViewById(R.id.recycler_exibir_ranking_time_activity);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerViewExibirRanking.setLayoutManager(manager);
        adapterExibirRanking = new AdapterExibirRanking(times);
        recyclerViewExibirRanking.setAdapter(adapterExibirRanking);
        Toolbar toolbar = findViewById(R.id.toolbar_exibir_ranking);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Exibir ranking");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    private void recuperarTimeRanking() {
        databaseReference.child("Time").child(usuarioLogado()).child(idRanking).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Time time1 = data.getValue(Time.class);
                    times.add(time1);
                }
                adapterExibirRanking.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
