package br.com.gd.notificationapi.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum SheetEnum {

    ERROR_WHEN_IMPORT_SHEET("NTF_002", "error when tryng import sheet to api", 400);

    private String code;
    private String message;
    private Integer statusCode;

}
