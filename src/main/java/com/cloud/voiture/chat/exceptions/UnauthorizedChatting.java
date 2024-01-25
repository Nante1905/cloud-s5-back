package com.cloud.voiture.chat.exceptions;

public class UnauthorizedChatting extends Exception {

    public UnauthorizedChatting() {
        super("Vous n'êtes pas autorisés à accéder à cette discussion.");
    }
}
