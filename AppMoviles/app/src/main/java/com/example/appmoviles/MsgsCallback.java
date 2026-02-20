package com.example.appmoviles;

import java.util.ArrayList;

public interface MsgsCallback {
    void onLoginSuccess(ArrayList<Mensaje> msg);
    void onLoginFailure(String errorMessage);
}
