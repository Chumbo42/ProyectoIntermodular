package com.example.appmoviles.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmoviles.DatosUsuarioLogueado;
import com.example.appmoviles.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DatosUsuarioLogueado viewModel = new ViewModelProvider(requireActivity()).get(DatosUsuarioLogueado.class);
        viewModel.getUserId().observe(getViewLifecycleOwner(), idUsuario -> {

        });

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}