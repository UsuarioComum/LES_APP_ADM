package com.example.app_firebase.Model;

import androidx.annotation.NonNull;

import com.example.app_firebase.config.config;
import com.example.app_firebase.helper.MyCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

public class Produtos {
    private String imagem;
    private String nome;
    private String valor;
    private String sabor;
    private String data;

    @Exclude
    private String ID;

    public Produtos() {
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void readprodutos(MyCallback myCallback){
        DatabaseReference bd = config.getbd();
        DatabaseReference pd = bd.child("Produtos");
        Query produtos = pd.child(ID);
        produtos.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Produtos produtos = snapshot.getValue(Produtos.class);
                myCallback.onCallback(produtos);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
