package com.example.appmoviles;

public interface LoginCallback {
    void onLoginSuccess(Usuario usuario);
    void onLoginFailure(String errorMessage);
}
