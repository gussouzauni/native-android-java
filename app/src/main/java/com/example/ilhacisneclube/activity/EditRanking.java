package com.example.ilhacisneclube.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.adaptador.AdapterEditRankingTime;
import com.example.ilhacisneclube.model.Time;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.ilhacisneclube.activity.CadastroCampeonato.usuarioLogado;

public class EditRanking extends AppCompatActivity {

    RecyclerView recyclerViewEditRanking;
    ArrayList<Time> times = new ArrayList<>();
    AdapterEditRankingTime adapterEditRankingTime;

    private String idRank;

    FirebaseDatabase mFirebasedatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_ranking_activity);
        //FIREBASE
        mFirebasedatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebasedatabase.getReference();
        idRank = getIntent().getExtras().getString("IdRanking");  //Receber IdRanking;
        Log.v("IdRanking", idRank); //Id do campeonato dos times;
        inicializarComponente();
        recuperarTime();
    }

    private void recuperarTime() {
        databaseReference.child("Time").child(usuarioLogado()).child(idRank).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Time time = data.getValue(Time.class);
                    times.add(time);
                }
                adapterEditRankingTime.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        menu.findItem(R.id.actionSair).setVisible(false);
        menu.findItem(R.id.actionProximo).setVisible(false);
        menu.findItem(R.id.actionEdit).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    private void inicializarComponente() {
        Toolbar toolbar = findViewById(R.id.toolbar_edit_ranking);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Editar Ranking");
        recyclerViewEditRanking = findViewById(R.id.recycler_view_edit_ranking_activity);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerViewEditRanking.setLayoutManager(manager);
        adapterEditRankingTime = new AdapterEditRankingTime(times);
        recyclerViewEditRanking.setAdapter(adapterEditRankingTime);
    }

}
