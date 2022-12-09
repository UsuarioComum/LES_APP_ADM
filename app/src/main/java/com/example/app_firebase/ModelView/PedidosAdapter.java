package com.example.app_firebase.ModelView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_firebase.ModelView.Home;
import com.example.app_firebase.Model.Pedidos;
import com.example.app_firebase.Model.Produtos;
import com.example.app_firebase.R;
import com.example.app_firebase.config.config;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class PedidosAdapter extends RecyclerView.Adapter {

    DatabaseReference bd = config.getbd();
    List<Pedidos> pedidosList;
    Context Mycontext;
    List<String> IDS_pedidos;




    public PedidosAdapter(List<Pedidos> pedidos, Context context, List<String> IDS) {
        this.pedidosList = pedidos;
        this.Mycontext = context;
        this.IDS_pedidos = IDS;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido,parent,false);
        ViewHolderClass vhClass =new ViewHolderClass(view);

        return vhClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass vhClass = (ViewHolderClass) holder;
        Pedidos pedidos = pedidosList.get(position);
        vhClass.txtnome.setText(pedidos.getNome());
        vhClass.txtdtEntrega.setText(pedidos.getDtaEntrega());
        vhClass.txtRua.setText(pedidos.getRua());
        vhClass.txtBairro.setText(pedidos.getBairro());
        vhClass.txtContatos.setText("EMAIL: "+pedidos.getEmail()+"\nNumero: "+pedidos.getNumero());
        vhClass.txtCom.setText(pedidos.getComplemento());

        //listando itens de pedido
        StringBuilder stringBuilder = new StringBuilder();

        for(Produtos produtos : pedidos.getProdutosList()){
            stringBuilder.append("\t-"+produtos.getNome()+"\n\t\t+Sabor:"+produtos.getSabor()+" \n\t\t+Valor:"+produtos.getValor()+"\n");
        }
        vhClass.itensPedidos.setText(stringBuilder);
        vhClass.txtValor.setText("Valor Total: "+pedidos.getValorTotal());


        //Configurando Botão
        vhClass.btn_entregue.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Log.i("TOTALDIFF", String.valueOf(IDS_pedidos.size()));

                DatabaseReference pd = bd.child("pedidos").child(IDS_pedidos.get(position)).child("status");
                pd.setValue("0");

                Intent intent = new Intent(v.getContext(), Home.class) ;
                v.getContext().startActivity(intent);
                ((Activity)Mycontext).finish();
                ((Activity)Mycontext).overridePendingTransition(0,0);
            }
        });


    }

    @Override
    public int getItemCount() {
        return pedidosList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView txtnome;
        TextView txtRua;
        TextView txtBairro;
        TextView txtdtEntrega;
        TextView itensPedidos;
        TextView txtContatos;
        TextView txtValor;
        Button btn_entregue;
        TextView txtCom;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            txtnome = itemView.findViewById(R.id.txtCliente);
            txtCom = itemView.findViewById(R.id.txtNum);
            txtRua = itemView.findViewById(R.id.txtRua);
            txtBairro = itemView.findViewById(R.id.txtBairro);
            txtdtEntrega = itemView.findViewById(R.id.txtDt_entrega);
            itensPedidos =itemView.findViewById(R.id.txtItemPedido);
            btn_entregue = itemView.findViewById(R.id.btn_Entregue);
            txtContatos = itemView.findViewById(R.id.txtContatos);
            txtValor = itemView.findViewById(R.id.txtValor);

        }


    }

}
