package com.example.quiz3k;

import com.example.quiz3k.model.AppErrorMessage;
import lombok.Getter;

@Getter
public class AppApiException extends RuntimeException {
    private int responseStatus;

    public AppApiException(AppErrorMessage message, String ... params){
        super(String.format(message.getMessageTemplate(), (Object) params));
        this.responseStatus = message.getStatus();
    }

    public AppApiException(String userNotFound) {

    }
}


