package br.com.gd.notificationapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("Sheets")
public class SheetEntity {

    @Id
    private String id;
    private String month;
    private Double input;
    private Double output;
    private Double amount;

}
