package com.example.ilhacisneclube.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.adaptador.AdapterCadastroTime;
import com.example.ilhacisneclube.model.Time;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.ilhacisneclube.activity.CadastroCampeonato.IDCampeonato;
import static com.example.ilhacisneclube.activity.CadastroCampeonato.usuarioLogado;

public class CadastroTime extends AppCompatActivity {

    //Firebase
    FirebaseDatabase mFirebasedatabase;
    DatabaseReference databaseReference;

    //Variáveis
    private FloatingActionButton btnCadastrarTime;
    private EditText editNomeTime;
    private Toolbar toolbar;
    //RecyclerView e adapter
    private RecyclerView recyclerViewTime;
    private ArrayList<Time> times = new ArrayList<>();
    private AdapterCadastroTime adapterCadastroTime;

    public static String IDTime;//Variável statica para ser chamado em outra classe sem necessidade de instanciar a classe; //Recebendo a variavel estática vindo lá do IDcampeonato da classe cadastroCampeonato;
    int contadorTime; //Variavel contador para identificar quantos times ja foram adicionados no database;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_time_activity);
        //Firebase
        mFirebasedatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebasedatabase.getReference();
        inicializarComponentes();
        botaoCadastrar();
        recuperarTime();
    }

    private void botaoCadastrar() { //Método do botão cadastrar;
        btnCadastrarTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCampo();
                final int numTimes = Integer.parseInt(CadastroCampeonato.qtdTimes); //Converte a variavel qtdTimes para inteiro e armazeno na variavel num
                if (numTimes > contadorTime) {
                    Log.v("contador:", contadorTime + "");
                } else {
                    esconderTeclado();
                    btnCadastrarTime.hide(); //Some com o botão
                    Toast.makeText(CadastroTime.this, "Limite atingido", Toast.LENGTH_SHORT).show();
                }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionCheck:
                esconderTeclado();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void validarCampo() {
        final String nomeTime = editNomeTime.getText().toString().trim();

        if (nomeTime.isEmpty()) {
            editNomeTime.setError("Campo vazio");
            editNomeTime.requestFocus();
            return;
        }
        if (nomeTime != null) {
            salvarTime(editNomeTime.getText().toString().trim());
            limpaCampo();
            contadorTime = contadorTime + 1; //Só armazena + 1 na variável se o campo for diferente de nulo.
            return;
        }
    }

    //Chamo o para recuperar os times no firebase e exibi-los no recyclerView;
    private void recuperarTime() {
        databaseReference.child("Time").child(usuarioLogado()).child(IDCampeonato).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                times.clear();
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    Time time1 = data.getValue(Time.class);
                        times.add(time1);
                }
                adapterCadastroTime.notifyDataSetChanged(); //Avisa para o adaptador que há mudanças;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void limpaCampo() { //Limpa os campos após adicionar os dados;
        editNomeTime.setText("");
    }

    private void salvarTime(String nomeTime) { //Método que salva os times no Firebase database;
        String idTime = databaseReference.child("Time").push().getKey(); //Gero uma chave aleatória para IDTime;
        IDTime = idTime;
        databaseReference.child("Time").child(usuarioLogado()).child(IDCampeonato).child(idTime).child("nomeTime").setValue(nomeTime);
    }

    private void esconderTeclado() {
        //Esconde o teclado apos concluir o campeonato;
        InputMethodManager inputManager = (InputMethodManager) CadastroTime.this.getSystemService(Context.INPUT_METHOD_SERVICE); //Sumir o teclado ao clicar no botão entrar
        inputManager.hideSoftInputFromWindow(btnCadastrarTime.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        return;
    }

    private void inicializarComponentes() {
        editNomeTime = findViewById(R.id.edit_text_cadastro_nome_time);
        btnCadastrarTime = findViewById(R.id.btn_add_times);
        //RecyclerView times
        recyclerViewTime = findViewById(R.id.recycler_times);
        recyclerViewTime.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTime.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        //Adapter times
        adapterCadastroTime = new AdapterCadastroTime(times);
        recyclerViewTime.setAdapter(adapterCadastroTime);
        toolbar = findViewById(R.id.toolbar_cadastro_time_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cadastro time");
    }
}

