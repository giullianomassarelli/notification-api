package br.com.gd.notificationapi.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum NotificationEnum {

    ERROR_TO_SEND_EMAIL("NTF_003", "error to send email", 400);

    private String code;
    private String message;
    private Integer statusCode;

}
