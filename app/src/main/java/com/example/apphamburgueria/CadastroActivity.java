package com.example.apphamburgueria;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {
    private EditText nomeInput, emailInput, senhaInput, confirmarSenhaInput, telefoneInput;
    private Button salvarButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casdastro); // Corrigido o nome do layout

        nomeInput = findViewById(R.id.edtNome);
        emailInput = findViewById(R.id.edtEmailCad);
        senhaInput = findViewById(R.id.edtSenhaCad);
        confirmarSenhaInput = findViewById(R.id.edtSenhaConfirmar);
        telefoneInput = findViewById(R.id.edtFoneCad);
        salvarButton = findViewById(R.id.btnSalvar);
        dbHelper = new DBHelper(this);

        salvarButton.setOnClickListener(view -> {
            String nome = nomeInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String senha = senhaInput.getText().toString();
            String confirmarSenha = confirmarSenhaInput.getText().toString();
            String telefone = telefoneInput.getText().toString().trim();

            // Verificação da confirmação da senha
            if (!senha.equals(confirmarSenha)) {
                Toast.makeText(this, "As senhas não coincidem!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Verifica se os outros campos estão preenchidos
            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || telefone.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Insere os dados no banco de dados
            if (dbHelper.insertUser(nome, email, senha, telefone)) {
                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Erro ao realizar cadastro", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
