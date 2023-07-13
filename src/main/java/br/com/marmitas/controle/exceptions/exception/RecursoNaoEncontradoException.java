package br.com.marmitas.controle.exceptions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecursoNaoEncontradoException extends Exception {
    public RecursoNaoEncontradoException(String message){
        super(message);
    }
}
