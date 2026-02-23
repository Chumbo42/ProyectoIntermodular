package com.example.appmoviles;


import android.content.Context;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

                    String imgStr = obj.getString("foto");
                    if (!imgStr.equals("")){
                        foto = imgStr.getBytes();
                    } else {
                        foto = null;
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

                        try {
                            String imgStr = obj.getString("foto");
                            if (!imgStr.equals("")){
                                foto = imgStr.getBytes();
                            } else {
                                foto = null;
                            }

                        }catch (NullPointerException e){
                            foto = null;
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

    public static ArrayList<Mensaje> getMsgs(int idChat, int idUsuario, boolean isPrivado, MsgsCallback callback) {
        new Thread(() -> {
            ArrayList<Mensaje> mensajes = new ArrayList<>();
            String errorMsg = null;

            if (isPrivado){
                try {
                    URL url = new URL("http://" + ip + "/CommsServerConsultas/rest/usuarios/mensajespriv?idc=" + idChat + "&idu=" + idUsuario);
                    Log.i("Url", url.toString());
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


            }

            else{
                try {
                    URL url = new URL("http://" + ip + "/CommsServerConsultas/rest/usuarios/mensajes?id=" + idChat);
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


            }



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


    public static ArrayList<Chat> getListaUsuarios(String nombre,int idUsuario, Context contexto, ChatsCallback callback) {
        // Ejecutar en hilo secundario
        new Thread(() -> {
            ArrayList<Chat> chats = new ArrayList<Chat>();
            String errorMsg = null;

            try {
                URL url = new URL("http://" + ip + "/CommsServerConsultas/rest/usuarios/pornombre?nombre=" + nombre + "&id=" + idUsuario );
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

                        byte[] foto;

                        try {
                            String imgStr = obj.getString("foto");
                            if (!imgStr.equals("")){
                                foto = imgStr.getBytes();
                            } else {
                                foto = null;
                            }

                        }catch (NullPointerException e){
                            foto = null;
                        }

                        chats.add(new Chat(idChat, nom, foto, true));
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

    public static void crearPrivado(int idUsuario1, int idUsuario2, VoidCallback callback){
        new Thread(() -> {
            String errorMsg = null;
            try {
                URL url = new URL("http://" + ip + "/CommsServerConsultas/rest/usuarios/crearmd?ua=" + idUsuario1 + "&ub=" + idUsuario2);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    errorMsg = "Error del servidor: " + responseCode;
                }

            } catch (Exception e) {
                Log.e("Login", "Error en crearMD", e);
                errorMsg = e.getMessage() != null ? e.getMessage() : "Error desconocido";
            }


            final String error = errorMsg;

            new Handler(Looper.getMainLooper()).post(() -> {
                if (error == null) {
                    callback.onLoginSuccess();
                } else {
                    callback.onLoginFailure(error);
                }
            });
        }).start();
    }

    public static void crearGrupo(String nombre, ArrayList<Chat> integrantes, int idCreador, VoidCallback callback) {
        new Thread(() -> {
            String errorMsg = null;
            try {
                URL url = new URL("http://" + ip + "/CommsServerConsultas/rest/usuarios/creargr?nombre=" + nombre);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    errorMsg = "Error del servidor: " + responseCode;
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

                    int idGrupo = Integer.parseInt(response.toString());

                    // Añadir creador e integrantes de forma síncrona dentro del hilo
                    addUsuarioGrupoSync(idGrupo, idCreador);
                    for (Chat c : integrantes) {
                        addUsuarioGrupoSync(idGrupo, c.getId());
                    }
                }

            } catch (Exception e) {
                Log.e("crearGrupo", "Error", e);
                errorMsg = e.getMessage() != null ? e.getMessage() : "Error desconocido";
            }

            final String error = errorMsg;
            new Handler(Looper.getMainLooper()).post(() -> {
                if (error == null) {
                    callback.onLoginSuccess();
                } else {
                    callback.onLoginFailure(error);
                }
            });
        }).start();
    }

    private static void addUsuarioGrupoSync(int idGrupo, int idUsuario) {
        try {
            URL url = new URL("http://" + ip + "/CommsServerConsultas/rest/usuarios/addUsuarioGrupo?idGrupo=" + idGrupo + "&idUsuario=" + idUsuario);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.getResponseCode();
            conn.disconnect();
        } catch (Exception e) {
            Log.e("addUsuarioGrupoSync", "Error", e);
        }
    }

    public static void addUsuarioGrupo(int idGrupo, int idUsuario, VoidCallback callback) {
        new Thread(() -> {
            String errorMsg = null;
            try {
                URL url = new URL("http://" + ip + "/CommsServerConsultas/rest/usuarios/addUsuarioGrupo?idGrupo=" + idGrupo + "&idUsuario=" + idUsuario);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    errorMsg = "Error del servidor: " + responseCode;
                }
                conn.disconnect();

            } catch (Exception e) {
                Log.e("addUsuarioGrupo", "Error", e);
                errorMsg = e.getMessage() != null ? e.getMessage() : "Error desconocido";
            }

            final String error = errorMsg;
            new Handler(Looper.getMainLooper()).post(() -> {
                if (error == null) {
                    callback.onLoginSuccess();
                } else {
                    callback.onLoginFailure(error);
                }
            });
        }).start();
    }


    public static void borrarUsuario(String usuario, String contra, VoidCallback callback){
        new Thread(() -> {
            String errorMsg = null;
            try {
                URL url = new URL("http://" + ip + "/CommsServerConsultas/rest/usuarios/borrar?nombre=" + usuario + "&contra=" + contra);
                Log.d("BorrarUsuario", "URL: " + url.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    errorMsg = "Error del servidor: " + responseCode;
                }

            } catch (Exception e) {
                Log.e("Login", "Error al borrar usuario", e);
                errorMsg = e.getMessage() != null ? e.getMessage() : "Error desconocido";
            }


            final String error = errorMsg;

            new Handler(Looper.getMainLooper()).post(() -> {
                if (error == null) {
                    callback.onLoginSuccess();
                } else {
                    callback.onLoginFailure(error);
                }
            });
        }).start();
    }

    public static void getNombreUsuario(int id, StringCallback callback) {
        new Thread(() -> {
            String errorMsg = null;
            String nombre = null;

            try {
                URL url = new URL("http://" + ip + "/CommsServerConsultas/rest/usuarios/nombreusuario?id=" + id);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    errorMsg = "Error del servidor: " + responseCode;
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

                    nombre = response.toString();
                }

            } catch (Exception e) {
                Log.e("API", "Error en getNombreUsuario", e);
                errorMsg = e.getMessage() != null ? e.getMessage() : "Error desconocido";
            }

            final String error = errorMsg;
            final String result = nombre;

            new Handler(Looper.getMainLooper()).post(() -> {
                if (error == null) {
                    callback.onLoginSuccess(result);
                } else {
                    callback.onLoginFailure(error);
                }
            });

        }).start();
    }

    public static void editarUsuario(int id, String nombre, String correo, String contra, VoidCallback callback) {
        new Thread(() -> {
            String errorMsg = null;
            try {
                URL url = new URL("http://" + ip + "/CommsServerConsultas/rest/usuarios/editar?id=" + id
                        + "&nombre=" + nombre + "&correo=" + correo + "&contra=" + contra);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    errorMsg = "Error del servidor: " + responseCode;
                }
                conn.disconnect();

            } catch (Exception e) {
                Log.e("editarUsuario", "Error", e);
                errorMsg = e.getMessage() != null ? e.getMessage() : "Error desconocido";
            }

            final String error = errorMsg;
            new Handler(Looper.getMainLooper()).post(() -> {
                if (error == null) callback.onLoginSuccess();
                else callback.onLoginFailure(error);
            });
        }).start();
    }

    public interface MensajeEnviadoCallback {
        void onSuccess();
        void onFailure(String error);
    }


}

