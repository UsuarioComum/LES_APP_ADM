package com.example.app_firebase.ModelView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_firebase.Model.Funcionario;
import com.example.app_firebase.R;
import com.example.app_firebase.config.config;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    EditText login;
    EditText senha;
    Button btnlogar;
    private Funcionario funcionario;
    private FirebaseAuth auth;

    DatabaseReference bd = config.getbd();
    DatabaseReference fd = bd.child("Funcionarios");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        senha = findViewById( R.id.edtSenha);
        login = findViewById(R.id.edtEmail);
        btnlogar= findViewById(R.id.btnLogar);
    }


    public void btnLogarOnclick(View view){
        String email = login.getText().toString();
        String pass = senha.getText().toString();

        if(email.isEmpty()||pass.isEmpty()){
            Toast.makeText(this,"Dados não preenchidos",Toast.LENGTH_SHORT).show();
        }
        else{
            funcionario = new Funcionario();
            funcionario.setEmail(email);
            funcionario.setNome("Giga");
            fd = bd.child("Funcionarios");
            Query funcionario = fd.orderByChild("email").equalTo(email);

            funcionario.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot!=null){
                        logarFunc();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Dados incorretos",Toast.LENGTH_SHORT).show();
                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });


        }


    }

    public void logarFunc(){
        auth = config.getAutenticacao();
        auth.signInWithEmailAndPassword(funcionario.getEmail(),senha.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(MainActivity.this,"Dados aceitos",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this , Home.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this,"Dados não aceitos",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}