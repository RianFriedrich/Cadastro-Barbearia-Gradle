package com.example.cadastrobarbearia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etCpf, etNome, etEstiloCorte;
    private Button btnSalvar, btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etCpf = findViewById(R.id.editTextCPF);
        etNome = findViewById(R.id.editTextNome);
        etEstiloCorte = findViewById(R.id.editTextEstilo);
        btnSalvar = findViewById(R.id.buttonCadastrar);
        btnListar = findViewById(R.id.buttonListar);

    }

    public void salvarCliente(View view) {
        String cpf = etCpf.getText().toString().trim();
        String nome = etNome.getText().toString().trim();
        String estiloCorte = etEstiloCorte.getText().toString().trim();

        if (cpf.isEmpty() || nome.isEmpty() || estiloCorte.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        Cliente novoCliente = new Cliente(cpf, nome, estiloCorte);

        new Thread(() -> {
            try {
                AppDatabase.getDatabase(this).clienteDao().inserir(novoCliente);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Cliente salvo com sucesso!", Toast.LENGTH_SHORT).show();
                    limparCampos();
                });
            } catch (Exception e) {
                runOnUiThread(() ->
                        Toast.makeText(this, "Erro: CPF já cadastrado", Toast.LENGTH_LONG).show());
            }
        }).start();
    }

    public void abrirListaClientes(View view) {
        Intent intent = new Intent(this, ListaClientesActivity.class);
        startActivity(intent);
    }

    private void limparCampos() {
        etCpf.setText("");
        etNome.setText("");
        etEstiloCorte.setText("");
        etCpf.requestFocus();
    }
}