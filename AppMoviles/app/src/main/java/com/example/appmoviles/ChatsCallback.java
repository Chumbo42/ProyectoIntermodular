package com.example.appmoviles;

import java.util.ArrayList;

public interface ChatsCallback {
    void onLoginSuccess(ArrayList<Chat> chats);
    void onLoginFailure(String errorMessage);
}
