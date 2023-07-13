package br.com.marmitas.controle.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.marmitas.controle.exceptions.error.RespostaErro;
import br.com.marmitas.controle.exceptions.exception.EntidadeJaExisteException;
import br.com.marmitas.controle.exceptions.exception.RecursoNaoEncontradoException;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<?> recursoNaoEncontradoException(
            RecursoNaoEncontradoException ex, WebRequest request) {
        RespostaErro errorDetails =
                new RespostaErro(new Date(), HttpStatus.NOT_FOUND.toString(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

@ExceptionHandler(EntidadeJaExisteException.class)
    public ResponseEntity<?> entidadeJaExisteException(
            RecursoNaoEncontradoException ex, WebRequest request) {
        RespostaErro errorDetails =
                new RespostaErro(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.toString(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
    }

@ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
       RespostaErro errorDetails =
                new RespostaErro(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.toString() ,ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
