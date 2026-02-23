package com.example.appmoviles.ui.usuario;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmoviles.ApiRest;
import com.example.appmoviles.DatosUsuarioLogueado;
import com.example.appmoviles.EditarCuenta;
import com.example.appmoviles.LogIn;
import com.example.appmoviles.Principal;
import com.example.appmoviles.R;
import com.example.appmoviles.Usuario;
import com.example.appmoviles.VoidCallback;
import com.example.appmoviles.databinding.FragmentUsuarioBinding;

public class UsuarioFragment extends Fragment {

    boolean flag = false;
    private FragmentUsuarioBinding binding;

    Usuario u;
    ImageView ivUsuario;
    TextView tvNombre;
    TextView tvCorreo;

    Button btEditar;
    Button btSalir;
    Button btBorrar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentUsuarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        ivUsuario = binding.ivUsuario;

        tvNombre = binding.tvUsuario;

        tvCorreo = binding.tvMail;

        Intent mod = new Intent(getContext(), EditarCuenta.class);

        DatosUsuarioLogueado viewModel = new ViewModelProvider(requireActivity()).get(DatosUsuarioLogueado.class);
        viewModel.getUsuario().observe(getViewLifecycleOwner(), usuario -> {
            this.u = usuario;

            try {

                if(u.getFoto() != null && !u.getFoto().toString().trim().equals("") ){
                    String stringImagen = new String(u.getFoto());
                    byte[] byteImagen = android.util.Base64.decode(stringImagen, android.util.Base64.DEFAULT);
                    ivUsuario.setImageBitmap(BitmapFactory.decodeByteArray(byteImagen, 0, byteImagen.length));
                }else {
                    ivUsuario.setImageResource(R.drawable.img_usuario);
                }

                tvNombre.setText(u.getNombre());

                tvCorreo.setText(u.getCorreo());

            } catch (NullPointerException e){}
            mod.putExtra("idUsuario", u.getId());
            mod.putExtra("usuario",usuario);

        });

        flag = false;

        btEditar = binding.btEditarCuenta;
        btSalir = binding.btCerrar;
        btBorrar = binding.btBorrar;


        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(mod);
                getActivity();
            }
        });

        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), LogIn.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag){
                    ApiRest.borrarUsuario(u.getNombre(), u.getContra(), new VoidCallback() {
                        @Override
                        public void onLoginSuccess() {
                            Intent i = new Intent(getContext(), LogIn.class);
                            startActivity(i);
                            getActivity().finish();
                        }

                        @Override
                        public void onLoginFailure(String errorMessage) {

                            Toast.makeText(getContext(), "No se ha podido eliminar tu cuenta", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(getContext(), "Pulsa otra vez para eliminar tu cuenta", Toast.LENGTH_SHORT).show();
                    flag = true;
                }
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