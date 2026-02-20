package com.example.appmoviles.ui.usuario;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmoviles.ApiRest;
import com.example.appmoviles.DatosUsuarioLogueado;
import com.example.appmoviles.R;
import com.example.appmoviles.Usuario;
import com.example.appmoviles.databinding.FragmentUsuarioBinding;

public class UsuarioFragment extends Fragment {

    private FragmentUsuarioBinding binding;

    Usuario u;
    ImageView ivUsuario;
    TextView tvNombre;
    TextView tvCorreo;
    Button btCNombre;
    Button btCContra;
    Button btCFoto;
    Button btCCorreo;
    Button btSalir;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DatosUsuarioLogueado viewModel = new ViewModelProvider(requireActivity()).get(DatosUsuarioLogueado.class);
        viewModel.getUsuario().observe(getViewLifecycleOwner(), usuario -> {
            this.u = usuario;
        });

        binding = FragmentUsuarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        ivUsuario = binding.ivUsuario;

        if(u.getFoto() != null && !u.getFoto().equals("") ){
            String stringImagen = new String(u.getFoto());
            byte[] byteImagen = android.util.Base64.decode(stringImagen, android.util.Base64.DEFAULT);
            ivUsuario.setImageBitmap(BitmapFactory.decodeByteArray(byteImagen, 0, byteImagen.length));
        }else {
            ivUsuario.setImageResource(R.drawable.img_usuario);
        }

        tvNombre = binding.tvUsuario;
        tvNombre.setText(u.getNombre());

        tvCorreo = binding.tvMail;
        tvCorreo.setText(u.getCorreo());





        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}