package com.example.appmoviles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

public class DatosUsuarioLogueado extends ViewModel {
    private MutableLiveData<Integer> userId = new MutableLiveData<>();
    private MutableLiveData<Usuario> usuario = new MutableLiveData<>();
    public void setUserId(int id) {
        userId.setValue(id);
    }
    public LiveData<Integer> getUserId() {
        return userId;
    }
    public void setUsuario(Usuario u){
        usuario.setValue(u);
    }
    public LiveData<Usuario> getUsuario(){
        return usuario;
    }


}
