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
    public void subirUsuario(String nombre, String contraseña){
        new Thread(() -> {
            try {
                URL url = new URL("http://192.130.0.26:8080/CommsServerConsultas/rest/deportistas/");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);

                JSONObject json = new JSONObject();
                json.put("nombre", nombre);
                json.put("contraseña", contraseña);

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

    public Usuario obtenerUsuario(String username, String contraseña) {
        try {
            URL url = new URL(
                    "http://10.0.2.2:8080/Comms/rest/deportistas?username=" + username );

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
                JSONObject obj = new JSONObject(response.toString());

                String user = obj.getString("username");
                String email = obj.getString("email");

                return new Usuario(user, email);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // si hay error
    }


}

