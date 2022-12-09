package com.example.app_firebase.helper;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

public class Base64Custom {

    public static String converte64(String texto){

        return Base64.encodeToString(texto.getBytes(), Base64.DEFAULT);
    }
    public String desconverte64(String textoCodificado){

        return new String(Base64.decode(textoCodificado, Base64.DEFAULT));
    }
}
