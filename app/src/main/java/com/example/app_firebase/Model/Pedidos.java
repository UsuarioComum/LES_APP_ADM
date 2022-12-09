package com.example.app_firebase.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pedidos {
    private String bairro;
    private String complemento;
    private String CPF;
    private String dtaEntrega;
    private String email;
    private String nome;
    private String numero;
    private String rua;
    private String status;
    private String valorTotal;



    private List<Produtos>produtosList;

    public Pedidos() {
        produtosList = new ArrayList<>();

    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getDtaEntrega() {
        return dtaEntrega;
    }

    public void setDtaEntrega(String dtaEntrega) {
        this.dtaEntrega = dtaEntrega;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Produtos> getProdutosList() {
        return produtosList;
    }

    public void setProdutosList(Produtos produtos) {
        this.produtosList.add(produtos) ;
    }
}
