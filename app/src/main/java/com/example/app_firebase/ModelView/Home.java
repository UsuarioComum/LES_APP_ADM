package com.example.app_firebase.ModelView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.app_firebase.Model.Pedidos;
import com.example.app_firebase.Model.Produtos;
import com.example.app_firebase.R;
import com.example.app_firebase.config.config;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity  {


    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    List<Pedidos> Pedido;
    PedidosAdapter pedidosAdapter;

    private DatabaseReference bd = config.getbd();
    private DatabaseReference pd = bd.child("pedidos");
    Query pedidos = pd.orderByChild("status").equalTo("1");



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.menu_agenda);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.menu_adm:
                        startActivity(new Intent(getApplicationContext(),Adm_activity.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;

                    case R.id.menu_produtos:
                        startActivity(new Intent(getApplicationContext(),Produtos_activity.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;
                }
                return false;
            }
        });

        recyclerView= findViewById(R.id.Recycle_list_pedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Pedido = new ArrayList<>();
        List<String> IDs = new ArrayList<>();
        Context context = this;



        pedidos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Pedidos P;
                for(DataSnapshot dadosPedido : snapshot.getChildren()){
                    Log.i("IDs",dadosPedido.getKey());
                    IDs.add(dadosPedido.getKey());

                    P =dadosPedido.getValue(Pedidos.class);


                    DataSnapshot produtos = dadosPedido.child("listaPedidos");
                    for(DataSnapshot produto : produtos.getChildren()){
                        Produtos produtoIns = produto.getValue(Produtos.class);
                        P.setProdutosList(produtoIns);

                    }
                    Pedido.add(P);
                }

                pedidosAdapter = new PedidosAdapter(Pedido,context,IDs);
                recyclerView.setAdapter(pedidosAdapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home.this,"Erro de consulta de Pedidos",Toast.LENGTH_SHORT).show();

            }
        });

    }

}