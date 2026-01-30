package com.example.appmoviles;


import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiRest {
    public static void subirUsuario(Usuario u){
        new Thread(() -> {
            try {
                URL url = new URL("http://192.130.0.26:8080/CommsServerConsultas/rest/deportistas/");
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
        }).start();
    }

    public static Usuario obtenerUsuario(String username, String contra) {
        try {
            Log.i("Flag", "Hola");
            URL url = new URL("http://192.130.0.26:8080/CommsServerConsultas/rest/usuarios/login/usuario=" + username + "&contra=" + contra);

            Log.i("Flag", "UrlBien");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            Log.i("Flag", "Conexion");

            int code = conn.getResponseCode();
            Log.i("Codigo rest", "Código HTTP: " + code);
            InputStream stream = conn.getInputStream();


            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8) );

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            { response.append(line.trim());
            }
            JSONObject obj = new JSONObject(response.toString());
            Log.i("Info", obj.getString("nombre"));

            if (code == 200) {


                String id = obj.getString("id");
                String correo = obj.getString("correo");
                byte[] foto = obj.getString("foto").getBytes();


                return new Usuario();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // si hay error
    }

    public static boolean usuarioLibre(String username) {
        try {
            URL url = new URL("http://192.130.0.26:8080/CommsServerConsultas/rest/usuarios/nombres/nombre=" + username);

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


}

