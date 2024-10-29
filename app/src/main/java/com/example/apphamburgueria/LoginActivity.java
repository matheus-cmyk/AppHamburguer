package com.example.apphamburgueria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmailLogin, edtSenhaLogin;
    private Button btnEntrar, btnCriarConta;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Verifique se o nome do layout está correto

        // Inicializando os campos de entrada e botões
        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtSenhaLogin = findViewById(R.id.edtSenhaLogin);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnCriarConta = findViewById(R.id.btnCriarConta);
        dbHelper = new DBHelper(this);

        // Configurando a ação do botão "Entrar"
        btnEntrar.setOnClickListener(view -> {
            String email = edtEmailLogin.getText().toString().trim();
            String senha = edtSenhaLogin.getText().toString().trim();

            // Verificando se os campos estão vazios
            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else if (dbHelper.checkUser(email, senha)) {
                // Login bem-sucedido, navega para a tela do catálogo
                startActivity(new Intent(LoginActivity.this, CatalogoActivity.class));
                finish(); // Finaliza a LoginActivity para não voltar a esta tela
            } else {
                // Login falhou, mostra mensagem de erro
                Toast.makeText(this, "E-mail ou senha incorretos", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurando a ação do botão "Criar Conta"
        btnCriarConta.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
        });
    }
}
