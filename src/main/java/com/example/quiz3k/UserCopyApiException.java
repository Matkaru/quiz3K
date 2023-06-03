package com.example.quiz3k;

import com.example.quiz3k.model.AppErrorMessage;
import lombok.Getter;

@Getter
public class UserCopyApiException extends RuntimeException {
    private int responseStatus;
    //String detailedMessage, niejawny wynika z dziedziczenia

    public UserCopyApiException(AppErrorMessage message, String ... params){
        super(String.format(message.getMessageTemplate(), params));
        this.responseStatus = message.getStatus();
    }
}


