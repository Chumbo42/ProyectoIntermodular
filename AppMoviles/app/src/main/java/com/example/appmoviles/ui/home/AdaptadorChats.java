package com.example.appmoviles.ui.home;

import static java.util.Base64.*;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmoviles.Chat;
import com.example.appmoviles.R;
import com.example.appmoviles.conversacion;

import java.util.ArrayList;
import java.util.Base64;

public class AdaptadorChats extends RecyclerView.Adapter<AdaptadorChats.MyViewHolder>{

    int selectedPos = RecyclerView.NO_POSITION;
    int usuario;
    ArrayList<Chat> chats;
    Context contexto;

    public AdaptadorChats(ArrayList<Chat> chats, Context contexto, int usuario)
    {
        this.chats = chats;
        this.contexto = contexto;
        this.usuario = usuario;
    }

    @NonNull
    @Override
    public AdaptadorChats.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_chat,parent,false);
        MyViewHolder mvh = new MyViewHolder(elemento);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorChats.MyViewHolder holder, int position) {
        Chat chat = this.chats.get(position);
        String stringImagen = new String(chat.getFoto());
        byte[] byteImagen = android.util.Base64.decode(stringImagen, android.util.Base64.DEFAULT);
        holder.obtenerFoto().setImageBitmap(BitmapFactory.decodeByteArray(byteImagen,0,byteImagen.length));
        holder.obtenerNombre().setText(chat.getNombre().toString());
    }

    @Override
    public int getItemCount() {
        return (chats != null) ? chats.size() : 0;
    }

    public int getSelectedPos(){
        return selectedPos;
    }

    public void setSelectedPos(int selectedPos){

            if (this.selectedPos >= 0){

                notifyItemChanged(this.selectedPos);



            }
            this.selectedPos = selectedPos;
            notifyItemChanged(selectedPos);
        
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tv;
        public MyViewHolder(View viewElemento) {
            super(viewElemento);

            this.iv = viewElemento.findViewById(R.id.imageView3);
            this.tv = viewElemento.findViewById(R.id.textView2);




            viewElemento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // getAdapterPosition devuelve la posición del view en el adaptador
                    int posPulsada = getAdapterPosition();
                    setSelectedPos(posPulsada);

                    // Si hay una posición marcada se muestra un Toast
                    if (selectedPos > RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(contexto, conversacion.class);
                        intent.putExtra("usuario", usuario);
                        intent.putExtra("chat",chats.get(posPulsada));
                        contexto.startActivity(intent);

                    }
                }
            });



        }
        public ImageView obtenerFoto(){return iv;}
        public TextView obtenerNombre(){return tv;}

    }
}
