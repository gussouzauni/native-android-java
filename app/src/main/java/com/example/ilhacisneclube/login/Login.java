package com.example.ilhacisneclube.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ilhacisneclube.R;
import com.example.ilhacisneclube.activity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import tyrantgit.explosionfield.ExplosionField;


public class Login extends AppCompatActivity {

    private Button btnEntrar, btnIrCadastrar;
    private TextView txtViewResetarSenha;
    private EditText userEmail;
    private EditText userSenha;

    FirebaseAuth firebaseAuth;
    ExplosionField explosionField;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        firebaseAuth = FirebaseAuth.getInstance();
        inicializarComponentes();
        explosionField = ExplosionField.attach2Window(this);
        botaoCadastrar();
        resetarSenha();
        verificarLogin();
    }

    private void botaoCadastrar() {
        btnIrCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Cadastrar.class);
                startActivity(i);
            }
        });
    }

    private void resetarSenha() {
            txtViewResetarSenha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Login.this, ResetarSenha.class);
                    startActivity(i);

                }
            });
        }

    private void verificarLogin() {
        //Verificação de entrada do login, se email e senha for correto, entrar no aplicativo
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = userEmail.getText().toString().trim();
                final String senha = userSenha.getText().toString().trim();

                if (email.isEmpty()) {
                    userEmail.setError("Campo vazio");
                    userEmail.requestFocus();
                    return;
                }
                if (senha.isEmpty()) {
                    userSenha.setError("Campo vazio");
                    userSenha.requestFocus();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(userEmail.getText().toString().trim(),userSenha.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        esconderTeclado();
                        if (task.isSuccessful()) {
                            if (user != null) { // Verifica se o usuario está logado
                                startActivity(new Intent(Login.this, MainActivity.class));
                            }

                        } else { //Se não estiver logado
                            Log.w("", "signInWithCredential:failure", task.getException());
                            alert("Login incorreto");
                        }
                    }
                });
            }

        });

    }
    private void inicializarComponentes() {
        userEmail = findViewById(R.id.edit_text_email_login);
        userSenha = findViewById(R.id.edit_text_senha_login);
        btnEntrar  = findViewById(R.id.btn_entrar_login);
        btnIrCadastrar = findViewById(R.id.btn_ir_cadastrar_login);
        txtViewResetarSenha = findViewById(R.id.txt_view_resetar_senha);
    }

    private void esconderTeclado() {
        //Esconde o teclado apos concluir o campeonato;
        InputMethodManager inputManager = (InputMethodManager) Login.this.getSystemService(Context.INPUT_METHOD_SERVICE); //Sumir o teclado ao clicar no botão entrar
        inputManager.hideSoftInputFromWindow(btnEntrar.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        return;
    }
    private void alert(String msg) { //Método para exibir uma mensagem
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
