package br.com.gd.notificationapi.exceptions;

import br.com.gd.notificationapi.exceptions.enums.SheetEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class SheetException extends NotificationApiException{

    private static final long serialVersionUID = -4589179341768493322L;

    public SheetException(SheetEnum error) {
        super(error.getMessage());
        this.error = error;
    }

    private final SheetEnum error;
}
