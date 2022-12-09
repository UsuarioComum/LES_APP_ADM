package com.example.app_firebase.Model;

import android.app.ActivityManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_firebase.config.config;
import com.example.app_firebase.helper.Base64Custom;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Clientes {
    private String Nome;
    private String email;
    private String Cpf;
    private  String Bairro;
    private String Rua;
    private String N;





    public static String BuscaCliente(String Id_cliente) {
        final List<Clientes> cliente =new ArrayList<>();
        final Clientes[] c = new Clientes[1];
        DatabaseReference bd = config.getbd();
        DatabaseReference cd = bd.child("Clientes");
        Query clientes = cd.child(Id_cliente);

        clientes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                c[0] = snapshot.getValue(Clientes.class);
                cliente.add(c[0]);
                String a = String.valueOf(cliente.size());
                Log.i("Teste", a);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        String a = String.valueOf(c.length);
        Log.i("Teste2", a);


        return "";

    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public String getRua() {
        return Rua;
    }

    public void setRua(String rua) {
        Rua = rua;
    }

    public String getN() {
        return N;
    }

    public void setN(String n) {
        N = n;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }


    public Clientes() {
    }

    public String getNome() {
        return Nome;
    }
}
