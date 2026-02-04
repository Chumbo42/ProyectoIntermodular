package com.example.appmoviles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

public class DatosUsuarioLogueado extends ViewModel {
    private MutableLiveData<Integer> userId = new MutableLiveData<>();
    private MutableLiveData<RecyclerView> rvChats = new MutableLiveData<>() ;
    public void setUserId(int id) {
        userId.setValue(id);
    }
    public LiveData<Integer> getUserId() {
        return userId;
    }
    public void setRvChats(RecyclerView rv){
        rvChats.setValue(rv);
    }
    public LiveData<RecyclerView> getRvChats(){
        return rvChats;
    }


}
