package com.example.appmoviles.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmoviles.ApiRest;
import com.example.appmoviles.Chat;
import com.example.appmoviles.DatosUsuarioLogueado;
import com.example.appmoviles.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<Chat> chats;
    int idUsuario;
    RecyclerView rv;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DatosUsuarioLogueado viewModel = new ViewModelProvider(requireActivity()).get(DatosUsuarioLogueado.class);
        viewModel.getUserId().observe(getViewLifecycleOwner(), idUsuario -> {
            this.idUsuario = idUsuario;
        });
        viewModel.getRvChats().observe(getViewLifecycleOwner(), rvChats -> {
            this.rv = rvChats;
        });

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        chats = ApiRest.getMensajes(idUsuario);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}