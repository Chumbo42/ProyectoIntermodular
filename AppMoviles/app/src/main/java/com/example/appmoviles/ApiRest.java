package com.example.appmoviles;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ApiRest {

    static final String ip = android.os.Build.FINGERPRINT.contains("generic")
            ? "10.0.2.2:8080"        // Emulador
            : "192.168.1.209:8080";  // Teléfono físico
    public static void subirUsuario(Usuario u, LoginCallback callback){
        new Thread(() -> {
            String errorMsg = null;
            try {
                URL url = new URL("http://" + ip + "/CommsServerConsultas/rest/usuarios/registrar");
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


    public static void obtenerUsuario(String username, String contra,Context contexto, LoginCallback callback) {
        // Ejecutar en hilo secundario
        new Thread(() -> {
            Usuario u = null;
            String errorMsg = null;

            try {
                URL url = new URL("http://" + ip + "/CommsServerConsultas/rest/usuarios/login?nombre="
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
                    byte[] foto;
                    try{
                        foto = obj.optString("foto",null).getBytes();
                    }catch (NullPointerException e){
                        Bitmap bitmap = BitmapFactory.decodeResource(contexto.getResources(),R.drawable.img_usuario);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        foto = baos.toByteArray();
                    }

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
            URL url = new URL("http://" + ip + "/CommsServerConsultas/rest/usuarios/nombres/nombre=" + username);

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

    public static ArrayList<Chat> getChats(int id, Context contexto, ChatsCallback callback) {
        // Ejecutar en hilo secundario
        new Thread(() -> {
            ArrayList<Chat> chats = new ArrayList<Chat>();
            String errorMsg = null;

            try {
                URL url = new URL("http://" + ip + "/CommsServerConsultas/rest/usuarios/chats?id=" + id );
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

                        Boolean privado = obj.getBoolean("privado");

                        byte[] foto;
                        try{
                            foto = obj.optString("foto",null).getBytes();
                        }catch (NullPointerException e){
                            Bitmap bitmap = BitmapFactory.decodeResource(contexto.getResources(),R.drawable.img_usuario);
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                            foto = baos.toByteArray();
                        }


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

    public static ArrayList<Mensaje> getMsgs(int id, boolean isPrivado, MsgsCallback callback) {
        new Thread(() -> {
            ArrayList<Mensaje> mensajes = new ArrayList<>();
            String errorMsg = null;

            try {
                URL url = new URL("http://" + ip + "/CommsServerConsultas/rest/usuarios/mensajes?id=" + id + "&privado=" + isPrivado);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                int responseCode = conn.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    if (responseCode != HttpURLConnection.HTTP_NOT_FOUND) {
                        errorMsg = "Error del servidor: " + responseCode;
                    } else {
                        errorMsg = "No se encontraron mensajes";
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

                        int idMensaje = obj.getInt("id");
                        int autor = obj.getInt("autor");
                        String contenido = obj.getString("contenido");
                        String fechaStr = obj.getString("fecha");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault());
                        Date fecha = sdf.parse(fechaStr);

                        mensajes.add(new Mensaje(idMensaje, autor, contenido, fecha));
                    }

                    Log.d("API", "Respuesta del servidor: " + response.toString());
                }

            } catch (Exception e) {
                Log.e("getMsgs", "Error al obtener mensajes", e);
                errorMsg = e.getMessage() != null ? e.getMessage() : "Error desconocido";
            }

            final String error = errorMsg;

            new Handler(Looper.getMainLooper()).post(() -> {
                if (error == null) {
                    callback.onLoginSuccess(mensajes);
                } else {
                    callback.onLoginFailure(error);
                }
            });



        }).start();
        return null;
    }

    public static void enviarMensaje(int conversacionId, int autor, String contenido, boolean isPrivado, MensajeEnviadoCallback callback) {
        new Thread(() -> {
            String errorMsg = null;

            try {
                URL url = new URL("http://" + ip + "/CommsServerConsultas/rest/usuarios/enviar?conversacion=" + conversacionId + "&privado=" + isPrivado + "&autor=" + autor);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                // Crear JSON del mensaje
                JSONObject mensaje = new JSONObject();
                mensaje.put("contenido", contenido);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault());
                mensaje.put("fecha", sdf.format(new Date()));

                OutputStream os = conn.getOutputStream();
                os.write(mensaje.toString().getBytes());
                os.flush();
                os.close();

                int responseCode = conn.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    errorMsg = "Error al enviar: " + responseCode;
                }

            } catch (Exception e) {
                Log.e("enviarMensaje", "Error", e);
                errorMsg = e.getMessage();
            }

            final String error = errorMsg;
            new Handler(Looper.getMainLooper()).post(() -> {
                if (error == null) {
                    callback.onSuccess();
                } else {
                    callback.onFailure(error);
                }
            });

        }).start();
    }

    public interface MensajeEnviadoCallback {
        void onSuccess();
        void onFailure(String error);
    }


}

