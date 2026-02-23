package com.example.appmoviles;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorAddGrupo extends RecyclerView.Adapter<AdaptadorAddGrupo.MyViewHolder>{

    int selectedPos = RecyclerView.NO_POSITION;
    TextView tv;
    TextView tvTitulo;
    int idUsuario;
    ArrayList<Chat> chats;
    ArrayList<Chat> grupo;
    Context contexto;
    Usuario usuario;


    public AdaptadorAddGrupo(ArrayList<Chat> chats, Context contexto, int id, Usuario u, TextView tv)
    {
        this.chats = chats;
        this.contexto = contexto;
        idUsuario=id;
        this.usuario = u;
        this.tvTitulo = tv;
        this.grupo = new ArrayList<>();
    }

    public ArrayList<Chat> getIntegrantes(){
        return grupo;
    }

    @NonNull
    @Override
    public AdaptadorAddGrupo.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_add_privado,parent,false);
        MyViewHolder mvh = new MyViewHolder(elemento);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorAddGrupo.MyViewHolder holder, int position) {
        Chat c = this.chats.get(position);

        if(c.getFoto() != null && !c.getFoto().toString().trim().equals("") ){

            String stringImagen = new String(c.getFoto());
            byte[] byteImagen = android.util.Base64.decode(stringImagen, android.util.Base64.DEFAULT);
            holder.obtenerFoto().setImageBitmap(BitmapFactory.decodeByteArray(byteImagen, 0, byteImagen.length));
        }else {
            holder.obtenerFoto().setImageResource(R.drawable.img_usuario);
        }

        holder.obtenerNombre().setText(c.getNombre().toString());
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

            this.iv = viewElemento.findViewById(R.id.ivAddPrivado);
            this.tv = viewElemento.findViewById(R.id.tvAddPrivado);




            viewElemento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();

                    if (!grupo.contains(chats.get(pos))){
                        grupo.add(chats.get(pos));

                    } else {
                        grupo.remove(chats.get(pos));
                    }
                    tvTitulo.setText("Introduce un usuario\n(" + grupo.size() + ")");

                }
            });



        }
        public ImageView obtenerFoto(){return iv;}
        public TextView obtenerNombre(){return tv;}

    }
}
