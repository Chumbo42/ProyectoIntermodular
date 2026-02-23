package com.example.appmoviles;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdaptadorMensajes extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    int selectedPos = RecyclerView.NO_POSITION;
    int usuario;
    ArrayList<Mensaje> mensajes;
    Context contexto;

    Boolean privado;
    private static final int MENSAJE_ENVIADO = 0;
    private static final int MENSAJE_RECIBIDO = 1;


    public AdaptadorMensajes(ArrayList<Mensaje> msgs, Context contexto, int usuario, boolean privado)
    {
        this.mensajes = msgs;
        this.contexto = contexto;
        this.usuario = usuario;
        this.privado = privado;
    }

    @Override
    public int getItemViewType(int position) {
        Mensaje mensaje = mensajes.get(position);

        if (mensaje.getAutor() == usuario){
            return MENSAJE_ENVIADO;
        } else {
            return MENSAJE_RECIBIDO;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == MENSAJE_ENVIADO) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mensaje_propio, parent, false);
            return new MensajeEnviadoViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mensaje_otro, parent, false);
            return new MensajeRecibidoViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Mensaje mensaje = mensajes.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
//        String hora = sdf.format(mensaje.getFecha());

        if (privado){
            Log.i("Privado", privado.toString());

            if (holder instanceof MensajeEnviadoViewHolder) {
                ((MensajeEnviadoViewHolder) holder).tvContenido.setText(mensaje.getContenido());
            } else if (holder instanceof MensajeRecibidoViewHolder) {
                ((MensajeRecibidoViewHolder) holder).tvContenido.setText(mensaje.getContenido());
            }
        }else{
            if (holder instanceof MensajeEnviadoViewHolder) {
                ((MensajeEnviadoViewHolder) holder).tvContenido.setText(mensaje.getContenido());
            } else if (holder instanceof MensajeRecibidoViewHolder) {
                ApiRest.getNombreUsuario(mensaje.getAutor(), new StringCallback() {
                    @Override
                    public void onLoginSuccess(String cadena) {
                        ((MensajeRecibidoViewHolder) holder).tvContenido.setText(cadena + ": \n" + mensaje.getContenido());
                    }

                    @Override
                    public void onLoginFailure(String errorMessage) {
                        Toast.makeText(contexto, "No se ha podido cargar un mensaje", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        }


    }

    @Override
    public int getItemCount() {
        return (mensajes != null) ? mensajes.size() : 0;
    }

//    public int getSelectedPos(){
//        return selectedPos;
//    }
//
//    public void setSelectedPos(int selectedPos){
//
//        if (this.selectedPos >= 0){
//
//            notifyItemChanged(this.selectedPos);
//
//
//
//        }
//        this.selectedPos = selectedPos;
//        notifyItemChanged(selectedPos);
//
//    }



    public class MensajeEnviadoViewHolder extends RecyclerView.ViewHolder {
        TextView tvContenido;

        public MensajeEnviadoViewHolder(View view) {
            super(view);
            tvContenido = view.findViewById(R.id.contenido_propio);

        }
    }

    public class MensajeRecibidoViewHolder extends RecyclerView.ViewHolder {
        TextView tvContenido;

        public MensajeRecibidoViewHolder(View view) {
            super(view);
            tvContenido = view.findViewById(R.id.contenido_otro);

        }
    }
}
