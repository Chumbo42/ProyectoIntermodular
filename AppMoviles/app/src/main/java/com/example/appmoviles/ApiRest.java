package com.example.appmoviles;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ApiRest {

    public static void subirUsuario(Usuario u, LoginCallback callback){
        new Thread(() -> {
            String errorMsg = null;
            try {
                URL url = new URL("http://10.0.2.2:8080/CommsServerConsultas/rest/usuarios/registrar");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);

                JSONObject json = new JSONObject();
                json.put("nombre", u.getNombre());
                json.put("contraseña", u.getContra());

                System.out.println(json);

                try(OutputStream os = con.getOutputStream()){
                    os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                } catch (IOException e){
                    Log.i("COD API", "MAL");
                }

                int code = con.getResponseCode();
                Log.i("COD API", "El codigo es: " + code);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            final Usuario usuario = u;
            final String error = errorMsg;

            new Handler(Looper.getMainLooper()).post(() -> {
                if (usuario != null) {
                    callback.onLoginSuccess(usuario);
                } else {
                    callback.onLoginFailure(error != null ? error : "Error al obtener usuario");
                }
            });

        }).start();
    }


    public static void obtenerUsuario(String username, String contra, LoginCallback callback) {
        // Ejecutar en hilo secundario
        new Thread(() -> {
            Usuario u = null;
            String errorMsg = null;

            try {
                URL url = new URL("http://10.0.2.2:8080/CommsServerConsultas/rest/usuarios/login?nombre="
                        + username + "&contra=" + contra);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                // Verificar código de respuesta
                int responseCode = conn.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    if (responseCode != HttpURLConnection.HTTP_NOT_FOUND){
                        errorMsg = "Error del servidor: " + responseCode;
                    } else {
                        errorMsg = "Credenciales Incorrectas";
                    }
                } else {
                    InputStream stream = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line.trim());
                    }

                    reader.close();
                    conn.disconnect();

                    JSONObject obj = new JSONObject(response.toString());

                    String nom = obj.getString("nombre");
                    int id = Integer.parseInt(obj.getString("id"));
                    String correo = obj.getString("correo");
                    byte[] foto = obj.getString("imagen").getBytes();

                    u = new Usuario(id, username, contra, correo, foto);
                }

            } catch (Exception e) {
                Log.e("Login", "Error en obtenerUsuario", e);
                errorMsg = e.getMessage() != null ? e.getMessage() : "Error desconocido";
            }

            // Volver al hilo principal para llamar al callback
            final Usuario usuario = u;
            final String error = errorMsg;

            new Handler(Looper.getMainLooper()).post(() -> {
                if (usuario != null) {
                    callback.onLoginSuccess(usuario);
                } else {
                    callback.onLoginFailure(error != null ? error : "Error al obtener usuario");
                }
            });

        }).start();
    }

    public static boolean usuarioLibre(String username) {
        try {
            URL url = new URL("http://10.0.2.2:8080/CommsServerConsultas/rest/usuarios/nombres/nombre=" + username);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int code = conn.getResponseCode();
            System.out.println("Código HTTP: " + code);

            InputStream stream = conn.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8) );

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            { response.append(line.trim());
            }

            if (code == 200) {
                if (username.equals(response.toString())){
                    return false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true; // si hay error
    }

    public static ArrayList<Chat> getChats(int id, ChatsCallback callback) {
        // Ejecutar en hilo secundario
        new Thread(() -> {
            ArrayList<Chat> chats = new ArrayList<Chat>();
            String errorMsg = null;

            try {
                URL url = new URL("http://10.0.2.2:8080/CommsServerConsultas/rest/usuarios/chats?id=" + id );
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                // Verificar código de respuesta
                int responseCode = conn.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    if (responseCode != HttpURLConnection.HTTP_NOT_FOUND){
                        errorMsg = "Error del servidor: " + responseCode;
                    } else {
                        errorMsg = "Credenciales Incorrectas";
                    }
                } else {
                    InputStream stream = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line.trim());
                    }

                    reader.close();
                    conn.disconnect();

                    JSONArray jsonArray = new JSONArray(response.toString());

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        int idChat = obj.getInt("id");
                        String nom = obj.getString("nombre");
                        byte[] foto = obj.getString("foto").getBytes();
                        Boolean privado = obj.getBoolean("privado");


                        chats.add(new Chat(idChat, nom, foto, privado));
                    }
                    Log.d("API", "Respuesta del servidor: " + response.toString());
                }

            } catch (Exception e) {
                Log.e("Login", "Error en obtenerUsuario", e);
                errorMsg = e.getMessage() != null ? e.getMessage() : "Error desconocido";
            }

            // Volver al hilo principal para llamar al callback

            final String error = errorMsg;

            new Handler(Looper.getMainLooper()).post(() -> {
                if (error == null) {
                    callback.onLoginSuccess(chats);
                } else {
                    callback.onLoginFailure(error);
                }
            });

        }).start();
        return null;
    }


}

