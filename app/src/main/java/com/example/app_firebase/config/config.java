package com.example.app_firebase.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class config {
    private static FirebaseAuth autenticacao;
    private static DatabaseReference bd;

    public static FirebaseAuth getAutenticacao() {
        if(autenticacao==null){
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }

    public static DatabaseReference getbd() {
        if(bd==null){
            bd = FirebaseDatabase.getInstance().getReference();
        }
        return bd;
    }
}
