package com.example.ilhacisneclube.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ilhacisneclube.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetarSenha extends AppCompatActivity {

    private EditText editEmail;
    private Button btnResetarSenha, btnVoltarResetarSenha;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resetar_senha_activity);
        auth = FirebaseAuth.getInstance(); //Pega a instância do FirebaseAuth
        inicializarComponentes();
        resetarSenha();
        voltarResetarSenha();
    }
    private void voltarResetarSenha() {
        btnVoltarResetarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResetarSenha.this, Login.class);
                startActivity(i);
            }
        });
    }
    private void resetarSenha() {
        btnResetarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Delay no click do botão
                Runnable mRun = null;
                Handler mHandler = new Handler();
                mHandler.postDelayed(mRun, 60000);

                final String email = editEmail.getText().toString().trim();

                InputMethodManager inputManager = (InputMethodManager) ResetarSenha.this.getSystemService(Context.INPUT_METHOD_SERVICE); //Sumir o teclado ao clicar no botão entrar
                inputManager.hideSoftInputFromWindow(btnResetarSenha.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                if (email.isEmpty()) {
                    editEmail.setError("Campo vazio");
                    editEmail.requestFocus();
                } else {  //Dar um time no click do botão

                    auth.sendPasswordResetEmail(editEmail.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                editEmail.setText("");
                                alert("Verifique a sua caixa de email");

                            } else {
                                alert("Falhou, tente novamente");
                            }
                        }
                    });
                }
            }
        });
    }
    private void inicializarComponentes() {
        editEmail = findViewById(R.id.edit_text_email_resetar_senha);
        btnResetarSenha = findViewById(R.id.btn_resetar_senha);
        btnVoltarResetarSenha = findViewById(R.id.btn_voltar_resetar_senha);

    }
    private void alert(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }




}
