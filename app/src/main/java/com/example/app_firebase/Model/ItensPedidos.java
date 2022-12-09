package com.example.app_firebase.Model;

public class ItensPedidos {
    private String ID_Pedido;
    private String ID_Produto;
    private int Qtd;
    private double Valor;

    public ItensPedidos() {
    }

    public String getID_Pedido() {
        return ID_Pedido;
    }

    public void setID_Pedido(String ID_Pedido) {
        this.ID_Pedido = ID_Pedido;
    }

    public String getID_Produto() {
        return ID_Produto;
    }

    public void setID_Produto(String ID_Produto) {
        this.ID_Produto = ID_Produto;
    }

    public int getQtd() {
        return Qtd;
    }

    public void setQtd(int qtd) {
        Qtd = qtd;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double valor) {
        this.Valor = valor;
    }
}
