package com.example.appmoviles.ui.chats;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmoviles.ApiRest;
import com.example.appmoviles.Chat;
import com.example.appmoviles.ChatsCallback;
import com.example.appmoviles.DatosUsuarioLogueado;
import com.example.appmoviles.add;
import com.example.appmoviles.databinding.FragmentChatsBinding;

import java.util.ArrayList;

public class ChatsFragment extends Fragment {

    ArrayList<Chat> chats;
    int idUsuario;
    RecyclerView rv;
    AdaptadorChats miAdaptador;
    RecyclerView.LayoutManager miLayoutManager;
    private FragmentChatsBinding binding;

    ImageButton ibAdd;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentChatsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rv = binding.rvChats;

        chats = new ArrayList<>();


        DatosUsuarioLogueado viewModel = new ViewModelProvider(requireActivity()).get(DatosUsuarioLogueado.class);
        viewModel.getUserId().observe(getViewLifecycleOwner(), idUsuario -> {
            this.idUsuario = idUsuario;

        });


        miAdaptador = new AdaptadorChats(chats, this.getContext(),idUsuario);
        miLayoutManager = new GridLayoutManager(this.getContext(), 1);
        rv.setAdapter(miAdaptador);
        rv.setLayoutManager(miLayoutManager);

        ApiRest.getChats(idUsuario, this.getContext(), new ChatsCallback() {
            @Override
            public void onLoginSuccess(ArrayList<Chat> chat) {
                Log.i("Chats","Hay " + chats.size() + " chats");
                chats.clear();
                chats.addAll(chat);

                miAdaptador.notifyDataSetChanged();
            }

            @Override
            public void onLoginFailure(String errorMessage) {
                Log.i("Chats","login failure");
                chats.clear();
                miAdaptador.notifyDataSetChanged();
            }
        });
        Log.d("HomeFragment", "Llamada a getMensajes ejecutada");

        ibAdd = binding.ibAdd;

        ibAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), add.class);
                intent.putExtra("idUsuario",idUsuario);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}