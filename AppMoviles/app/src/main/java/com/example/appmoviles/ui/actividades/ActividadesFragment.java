package com.example.appmoviles.ui.actividades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmoviles.DatosUsuarioLogueado;
import com.example.appmoviles.databinding.FragmentActividadesBinding;

public class ActividadesFragment extends Fragment {

    private FragmentActividadesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DatosUsuarioLogueado viewModel = new ViewModelProvider(requireActivity()).get(DatosUsuarioLogueado.class);
        viewModel.getUserId().observe(getViewLifecycleOwner(), idUsuario -> {

        });

        binding = FragmentActividadesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}