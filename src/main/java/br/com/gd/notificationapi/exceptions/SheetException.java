package br.com.gd.notificationapi.exceptions;

import br.com.gd.notificationapi.exceptions.enums.SheetEnum;

public class SheetException extends NotificationApiException{

    private static final long serialVersionUID = -4589179341768493322L;

    public SheetException(SheetEnum error) {
        super(error.getMessage());
        this.error = error;
    }

    private final SheetEnum error;
}
