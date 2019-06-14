package com.example.ilhacisneclube.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.model.Campeonato;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroCampeonato extends AppCompatActivity {

    private EditText editNomeCampeonato, editModalidade, editDescricaoCampeonato, editLiderCampeonato;
    private Spinner spinnerTimes;
    private Toolbar toolbar;

    //Instância da classe
    Campeonato campeonato = new Campeonato();
    public static String IDCampeonato; //Crio variável IDcampeonato para utilizar em outra classe, variavel global
    public static String qtdTimes; //Criei uma variável global para que possa pegar o spinner selecionado do usuário, sem precisar puxar do banco de dados;

    //Firebase
    static DatabaseReference databaserefence; //Usa para gravar os dados e ler dados no firebase
    static FirebaseDatabase mFirebaseDatabase; //Faz menção ao firebase realtime

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_campeonato_activity);
        //Pegando a instância e a referência do firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaserefence = mFirebaseDatabase.getReference();
        inicializarComponentes();
    }

    private void adicionarCampeonato(String nomeCampeonato, String descricao, String modalidade, String quantidadeTimes, String liderCampeonato) {
        IDCampeonato = databaserefence.child(usuarioLogado()).push().getKey(); //Gero uma chave key e armazeno na variavel idCampeonato e passo ela lá em baixo no meu child;
        //Aqui especifico o caminho no meu banco de dados e atribuo o id que é o id do AUTHENTIC do usuário que está logado no meu aplicativo e o idCampeonato relacionado aquele campeonato criado e digo que a tabela campeonato que ele criou é relacionado com o ID dele e do IDcampeonato;
        databaserefence.child("Campeonato").child(usuarioLogado()).child(IDCampeonato).child("nomeCampeonato").setValue(nomeCampeonato);
        databaserefence.child("Campeonato").child(usuarioLogado()).child(IDCampeonato).child("descricaoCampeonato").setValue(descricao);
        databaserefence.child("Campeonato").child(usuarioLogado()).child(IDCampeonato).child("modalidadeCampeonato").setValue(modalidade);
        databaserefence.child("Campeonato").child(usuarioLogado()).child(IDCampeonato).child("liderCampeonato").setValue(liderCampeonato);
        databaserefence.child("Campeonato").child(usuarioLogado()).child(IDCampeonato).child("quantidadeTimes").setValue(quantidadeTimes);

    }

    private void validarCampo() {  //Valido os dados dos meus campos editText
        final String nomeCampeonato = editNomeCampeonato.getText().toString().trim();
        final String nomeModalidade = editModalidade.getText().toString().trim();
        final String descricaoCampeonato = editDescricaoCampeonato.getText().toString().trim();
        final String liderCampeonato = editLiderCampeonato.getText().toString().trim();
        final String quantidadeTimes = (spinnerTimes.getSelectedItem().toString()); //Pego o que foi selecionado no spinner e armazena em uma variável;
        campeonato.setQuantidadeTimes(quantidadeTimes); //Altero a o estado da variável quantidadeTimes, para salvar no meu banco;
        qtdTimes = quantidadeTimes; //Armazeno o spinner selecionado pelo usuário nesta variável "qtdTimes";

        if (nomeCampeonato.isEmpty()) {
            editNomeCampeonato.setError("Campo vazio");
            editNomeCampeonato.requestFocus();
            return;
        }

        if (nomeModalidade.isEmpty()) {
            editModalidade.setError("Campo vazio");
            editModalidade.requestFocus();
            return;
        }
        if (descricaoCampeonato.isEmpty()) {
            editDescricaoCampeonato.setError("Campo vazio");
            editDescricaoCampeonato.requestFocus();
            return;
        }
        if (liderCampeonato.isEmpty()) {
            editLiderCampeonato.setError("Campo vazio");
            editLiderCampeonato.requestFocus();
        }
        if (nomeCampeonato != null && nomeModalidade != null && descricaoCampeonato != null && liderCampeonato != null) {
            //Faço a validação para saber se é nulo, se for diferente chamo o método adicionarCampeonato, se eu tirar ele daqui, ele adiciona no banco do firebase, valores nulos;
            adicionarCampeonato(editNomeCampeonato.getText().toString().trim(), editDescricaoCampeonato.getText().toString().trim(), editModalidade.getText().toString().trim(), campeonato.getQuantidadeTimes().trim(), editLiderCampeonato.getText().toString().trim());
            Intent i = new Intent(CadastroCampeonato.this, CadastroTime.class);
            startActivity(i);
            return;
        }
    }

    public static String usuarioLogado() { //Criei um método que vai armazenar a variável IdUsuário com a instância do usuário logado no momento.
        String idUsuario = FirebaseAuth.getInstance().getCurrentUser().getUid();  //Salvar o id auth do usuário que está logado no momento
        return idUsuario;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar, menu);
        menu.findItem(R.id.actionCheck).setVisible(false);
        menu.findItem(R.id.actionSair).setVisible(false);
        menu.findItem(R.id.actionEdit).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionProximo:
               validarCampo();
                return true;
        }
        return false;
    }

    private void inicializarComponentes() {  //Inicializa os componentes do layout
        editNomeCampeonato = findViewById(R.id.edit_text_nome_campeonato);
        editDescricaoCampeonato = findViewById(R.id.edit_text_descricao_campeonato);
        editModalidade = findViewById(R.id.edit_text_modalidade_campeonato);
        editLiderCampeonato = findViewById(R.id.edit_text_lider_campeonato);
        toolbar = findViewById(R.id.toolbar_cadastro_campeonato);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cadastro campeonato");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Faz o botao do icone do menu voltar para a "home", define no meu manifest;
        //Spinner com array adapter
        spinnerTimes = findViewById(R.id.spinner_qtd_times);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.array_spinner, android.R.layout.simple_list_item_1);
        spinnerTimes.setAdapter(adapter);
    }
}
