package com.example.cadastrobarbearia;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "clientes")
public class Cliente {
    @PrimaryKey @NonNull
    private String cpf;
    private String nome;
    private String estiloCorte;

    public Cliente(String cpf, String nome, String estiloCorte) {
        this.cpf = cpf;
        this.nome = nome;
        this.estiloCorte = estiloCorte;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstiloCorte() {
        return estiloCorte;
    }

    public void setEstiloCorte(String estiloCorte) {
        this.estiloCorte = estiloCorte;
    }
}