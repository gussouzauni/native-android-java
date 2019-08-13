package com.example.ilhacisneclube.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tyrantgit.explosionfield.ExplosionField;


public class Cadastrar extends AppCompatActivity {

    private EditText editTextNome, editTextEmail, editTextSenha, editTextSobrenome;
    private Button btnCadastrar, btnVoltar;
    private RadioGroup btnGenero;
    private RadioButton btnMasculino, btnFeminino;
    FirebaseAuth firebaseAuth;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar_activity);
        //Firebase
        databaseReference = firebaseDatabase.getInstance().getReference("User");
        firebaseAuth = FirebaseAuth.getInstance();
        inicializarComponentes();
        cadastrarUsuario();
        botaoVoltar();
    }

    private void botaoGenero() {
        btnGenero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn_masculino:
                      break;
                    case R.id.btn_feminino:
                        break;
                }
            }
        });
    }

    private void cadastrarUsuario() {
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nome = editTextNome.getText().toString().trim();
                final String email = editTextEmail.getText().toString().trim();
                final String sobrenome = editTextSobrenome.getText().toString().trim();
                final String senha = editTextSenha.getText().toString().trim();
                //Campo de validações de nullo
                if (nome.isEmpty()) {
                    editTextNome.setError("Nome vazio");
                    editTextNome.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    editTextEmail.setError("Email vazio");
                    editTextEmail.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError("Email errado");
                    editTextEmail.requestFocus();
                    return;
                }
                if (senha.isEmpty()) {
                    editTextSenha.setError("Campo vazio");
                    editTextSenha.requestFocus();
                    return;
                }
                if (senha.length() < 6) {
                    editTextSenha.setError("Mínimo 6 dígitos");
                    editTextSenha.requestFocus();
                    return;
                }
                if (sobrenome.isEmpty()) {
                    editTextSobrenome.setError("Campo vazio");
                    editTextSobrenome.requestFocus();
                    return;
                }


                firebaseAuth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(Cadastrar.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(
                                    nome, email, sobrenome
                            );
                            FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Alert("Cadastro efetuado com sucesso");
                                    final Runnable mRun = new Runnable () {
                                        @Override
                                        public void run() {
                                            //Código que quiser executar aqui
                                            Intent i = new Intent(Cadastrar.this, Login.class);
                                            startActivity(i);
                                        }
                                    };
                                    Handler mHandler = new Handler();
                                    mHandler.postDelayed(mRun, 500);
                                }
                            });
                        } else {
                            Alert("Erro no cadastro");  //Escrever mensagem aqui caso de algo errado

                        }
                    }
                });
            }
        });
    }
    private void botaoVoltar() {
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Cadastrar.this, Login.class);
                startActivity(i);
            }
        });
    }
    //Inicializar Componentes do layout
    private void inicializarComponentes() {
        editTextNome = findViewById(R.id.edit_text_nome_cadastro);
        editTextEmail =findViewById(R.id.edit_text_email_cadastrar);
        editTextSenha = findViewById(R.id.edit_text_senha_cadastrar);
        editTextSobrenome = findViewById(R.id.edit_text_sobrenome_cadastrar);
        btnCadastrar = findViewById(R.id.btn_cadastrar);
        btnVoltar = findViewById(R.id.btn_voltar_cadastrar);
        btnGenero = findViewById(R.id.botao_genero);
        btnMasculino = findViewById(R.id.btn_masculino);
        btnFeminino = findViewById(R.id.btn_feminino);
    }

    private void Alert(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
