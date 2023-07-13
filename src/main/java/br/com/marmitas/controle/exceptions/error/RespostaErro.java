package br.com.marmitas.controle.exceptions.error;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RespostaErro {
    
    private Date timestamp;
    private String status;
    private String message;
    private String details;
}
