package com.example.app_firebase.ModelView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

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

public class Produtos_activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;
    List<Produtos> produtosList;
    ProdutosAdapter produtosAdapter;


    private DatabaseReference bd = config.getbd();
    private DatabaseReference pd = bd.child("Produtos");
    Query produtos = pd.orderByChild("nome");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.menu_produtos);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.menu_adm:
                        startActivity(new Intent(getApplicationContext(),Adm_activity.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;

                    case R.id.menu_agenda:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;
                }
                return false;
            }
        });


        recyclerView= findViewById(R.id.recycleProdutos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        produtosList = new ArrayList<>();
        List<String>IDS = new ArrayList<>();
        Context context = this;

        produtos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Produtos p;
                for(DataSnapshot produto : snapshot.getChildren()){

                    p = produto.getValue(Produtos.class);
                    produtosList.add(p);
                    IDS.add(produto.getKey());
                }

                produtosAdapter=new ProdutosAdapter(produtosList,context,IDS);
                recyclerView.setAdapter(produtosAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

}