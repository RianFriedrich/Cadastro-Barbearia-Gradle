package com.example.cadastrobarbearia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListaClientesActivity extends AppCompatActivity {

    private ListView lvClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_clientes_activity);

        lvClientes = findViewById(R.id.listView);
        carregarClientes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarClientes(); // Recarrega quando voltar para esta tela
    }

    private void carregarClientes() {
        new Thread(() -> {
            try {
                List<Cliente> clientes = AppDatabase.getDatabase(this)
                        .clienteDao()
                        .getAll();

                runOnUiThread(() -> {
                    if (clientes.isEmpty()) {
                        Toast.makeText(this, "Nenhum cliente cadastrado", Toast.LENGTH_SHORT).show();
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            this,
                            android.R.layout.simple_list_item_1,
                            formatarDadosClientes(clientes)
                    );

                    lvClientes.setAdapter(adapter);
                });
            } catch (Exception e) {
                runOnUiThread(() ->
                        Toast.makeText(this, "Erro ao carregar clientes", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    public void voltarMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private List<String> formatarDadosClientes(List<Cliente> clientes) {
        List<String> dadosFormatados = new ArrayList<>();
        for (Cliente cliente : clientes) {
            String item = "Nome: " + cliente.getNome() + "\n" +
                    "CPF: " + cliente.getCpf() + "\n" +
                    "Estilo: " + cliente.getEstiloCorte();
            dadosFormatados.add(item);
        }
        return dadosFormatados;
    }
}