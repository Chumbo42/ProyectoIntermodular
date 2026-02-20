package com.example.appmoviles;

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

public class AdaptadorAddPrivado extends RecyclerView.Adapter<AdaptadorAddPrivado.MyViewHolder>{

    int selectedPos = RecyclerView.NO_POSITION;
    int usuario;
    ArrayList<Usuario> usuarios;
    Context contexto;

    public AdaptadorAddPrivado(ArrayList<Usuario> usuarios, Context contexto)
    {
        this.usuarios = usuarios;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public AdaptadorAddPrivado.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_add_privado,parent,false);
        MyViewHolder mvh = new MyViewHolder(elemento);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorAddPrivado.MyViewHolder holder, int position) {
        Usuario u = this.usuarios.get(position);
        String stringImagen = new String(u.getFoto());
        byte[] byteImagen = android.util.Base64.decode(stringImagen, android.util.Base64.DEFAULT);
        holder.obtenerFoto().setImageBitmap(BitmapFactory.decodeByteArray(byteImagen,0,byteImagen.length));
        holder.obtenerNombre().setText(u.getNombre().toString());
    }

    @Override
    public int getItemCount() {
        return (usuarios != null) ? usuarios.size() : 0;
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

            this.iv = viewElemento.findViewById(R.id.ivAddPrivado);
            this.tv = viewElemento.findViewById(R.id.tvAddPrivado);




            viewElemento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // getAdapterPosition devuelve la posición del view en el adaptador
                    int posPulsada = getAdapterPosition();
                    setSelectedPos(posPulsada);

                    // Si hay una posición marcada se muestra un Toast
                    if (selectedPos > RecyclerView.NO_POSITION) {
//                        Intent intent = new Intent(contexto, conversacion.class);
//                        intent.putExtra("usuario", idUsuario);
//                        intent.putExtra("chat",chats.get(posPulsada));
//                        intent.putExtra("isPrivado",chats.get(posPulsada).getPrivado());
//                        contexto.startActivity(intent);

                    }
                }
            });



        }
        public ImageView obtenerFoto(){return iv;}
        public TextView obtenerNombre(){return tv;}

    }
}
