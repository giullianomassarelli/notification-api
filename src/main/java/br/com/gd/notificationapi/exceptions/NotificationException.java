package br.com.gd.notificationapi.exceptions;

import br.com.gd.notificationapi.exceptions.enums.NotificationEnum;
import br.com.gd.notificationapi.exceptions.enums.SheetEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class NotificationException extends NotificationApiException{

    private static final long serialVersionUID = -4589179341768493322L;

    public NotificationException(NotificationEnum error) {
        super(error.getMessage());
        this.error = error;
    }

    private final NotificationEnum error;
}
