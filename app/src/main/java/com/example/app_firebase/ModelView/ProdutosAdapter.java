package com.example.app_firebase.ModelView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_firebase.ModelView.Produtos_activity;
import com.example.app_firebase.Model.Produtos;
import com.example.app_firebase.R;
import com.example.app_firebase.config.config;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProdutosAdapter extends RecyclerView.Adapter {

    DatabaseReference bd = config.getbd();
    List<Produtos> produtosList;
    Context Mycontext;
    List<String>IDS_produtos;




    public ProdutosAdapter(List<Produtos> pedidos, Context context,List<String> IDS) {
        this.produtosList = pedidos;
        this.Mycontext = context;
        this.IDS_produtos = IDS;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto,parent,false);
        ViewHolderClass vhClass =new ViewHolderClass(view);

        return vhClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass vhClass = (ViewHolderClass) holder;
        Log.i("Produto", produtosList.get(position).getNome());
        Log.i("Produto", produtosList.get(position).getValor());
        Log.i("Produto", produtosList.get(position).getImagem());
        Log.i("Produto", produtosList.get(position).getSabor());


        vhClass.txtDescricao.setText(produtosList.get(position).getSabor());
        vhClass.txtNome.setText(produtosList.get(position).getNome());
        vhClass.txtValor.setText(produtosList.get(position).getValor());
        Picasso.get().load(produtosList.get(position).getImagem()).into(vhClass.imageView);
        vhClass.txtData2.setText(produtosList.get(position).getData());



        //Configurando Botão alterar
        vhClass.btnAltera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                produtosList.get(position).setNome(vhClass.txtNome.getText().toString());
                produtosList.get(position).setValor(vhClass.txtValor.getText().toString());
                produtosList.get(position).setSabor(vhClass.txtDescricao.getText().toString());
                produtosList.get(position).setData(vhClass.txtData2.getText().toString());

                DatabaseReference pd = bd.child("Produtos").child(IDS_produtos.get(position));

                pd.setValue(produtosList.get(position));

                Intent intent = new Intent(v.getContext(), Produtos_activity.class) ;
                v.getContext().startActivity(intent);
                ((Activity)Mycontext).finish();
                ((Activity)Mycontext).overridePendingTransition(0,0);
            }
        });

        //Configurando Botão excluir
        vhClass.btnExclui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference pd = bd.child("Produtos").child(IDS_produtos.get(position));

                pd.removeValue();

                Intent intent = new Intent(v.getContext(), Produtos_activity.class) ;
                v.getContext().startActivity(intent);
                ((Activity)Mycontext).finish();
                ((Activity)Mycontext).overridePendingTransition(0,0);

            }
        });




    }

    @Override
    public int getItemCount() {
        return produtosList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        ImageView imageView;
        EditText txtNome;
        EditText txtValor;
        EditText txtDescricao;
        Button btnExclui;
        Button btnAltera;
        EditText txtData2;



        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            txtNome = itemView.findViewById(R.id.txtNomeProduto);
            txtDescricao = itemView.findViewById(R.id.txtDescricaoproduto);
            txtValor = itemView.findViewById(R.id.txtvlProduto);
            btnExclui = itemView.findViewById(R.id.btn_excluirProduto);
            btnAltera = itemView.findViewById(R.id.btn_alterar);
            txtData2 = itemView.findViewById(R.id.txtData2);


        }


    }

}
