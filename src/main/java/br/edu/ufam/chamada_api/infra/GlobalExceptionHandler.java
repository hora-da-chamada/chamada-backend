package br.edu.ufam.chamada_api.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@RestControllerAdvice
public class GlobalExceptionHandler {

    public record ErroPadrao(LocalDateTime timestamp, String erro, int status) {}

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroPadrao> tratarRegrasDeNegocio(RuntimeException ex) {
        
        ErroPadrao erro = new ErroPadrao(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
