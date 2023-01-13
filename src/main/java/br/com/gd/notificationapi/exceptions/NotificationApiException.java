package br.com.gd.notificationapi.exceptions;

public class NotificationApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotificationApiException(String message) {
        super(message);
    }
}
