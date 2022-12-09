package com.example.app_firebase.ModelView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app_firebase.Model.Produtos;
import com.example.app_firebase.R;
import com.example.app_firebase.config.config;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class CadastarProdutos extends AppCompatActivity {
    EditText txtnome;
    EditText txtvalor;
    EditText txtData;
    EditText txtdescricao;
    Button btnPhoto;
    Button btnCadastrar;
    Uri uriselect;
    ImageView imgPhoto;

    DatabaseReference bd = config.getbd();
    DatabaseReference pd = bd.child("Produtos");

    String nome;
    String valor;
    String descricao;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastar_produtos);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnPhoto = findViewById(R.id.btnselectPhoto);
        imgPhoto=findViewById(R.id.imgPhoto);
        txtnome = findViewById(R.id.txtNome);
        txtdescricao = findViewById(R.id.txtDescricao);
        txtvalor = findViewById(R.id.txtvalorProduto);
        txtData = findViewById(R.id.txtData);






        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPhoto();
            }

        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = txtnome.getText().toString();
                valor = txtvalor.getText().toString();
                descricao = txtdescricao.getText().toString();
                data = txtData.getText().toString();

                if(nome.isEmpty() || valor.isEmpty() || descricao.isEmpty() || data.isEmpty()){
                    Toast.makeText(CadastarProdutos.this,"Dados incompletos", Toast.LENGTH_SHORT).show();
                }
                else{
                    Produtos produto = new Produtos();
                    produto.setNome(nome);
                    produto.setSabor(descricao);
                    produto.setValor(valor);
                    produto.setData(data);
                    StorageReference ref = FirebaseStorage.getInstance().getReference("/images/"+uriselect.getLastPathSegment());
                    UploadTask uploadTask = ref.putFile(uriselect);

                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("Foto", "Falha no upload ");
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Log.i("Foto", "Falha no upload ");

                        }
                    });

                    Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if(!task.isSuccessful()){
                                throw task.getException();
                            }
                            return ref.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if(task.isSuccessful()){
                                produto.setImagem(task.getResult().toString());
                                pd.push().setValue(produto);
                                Toast.makeText(CadastarProdutos.this,"Cadastro feito com sucesso ", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(CadastarProdutos.this,"Cadastro cancelado ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            uriselect = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriselect);
                imgPhoto.setImageDrawable(new BitmapDrawable(bitmap));
                btnPhoto.setAlpha(0);
            } catch (IOException e) {

            }
        }
    }


    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }
}