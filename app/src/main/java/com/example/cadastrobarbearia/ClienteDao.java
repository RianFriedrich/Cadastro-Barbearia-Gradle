package com.example.cadastrobarbearia;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ClienteDao {
    @Insert
    void inserir(Cliente cliente);

    @Query("SELECT * FROM clientes")
    List<Cliente> getAll();

    @Query("SELECT * FROM clientes WHERE cpf = :cpf")
    Cliente getByCpf(String cpf);

    @Query("DELETE FROM clientes WHERE cpf = :cpf")
    void deletar(String cpf);


}