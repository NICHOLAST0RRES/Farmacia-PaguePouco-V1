package com.paguepouco.api.infra.exception;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;


// controlador dos erros da Api
@RestControllerAdvice
public class TratadorDeErros {


    // Trata o Erro 404, retornando da melhor forma
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){

            return ResponseEntity.notFound().build();
    }

    //Trata o erro 400 e explica oq veio errado da uma maneira mais organizada
    @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(formatadorErro::new).toList());

    }



    public record formatadorErro(String campo ,String mensagem){

        public formatadorErro(FieldError error){
                this(error.getField(),error.getDefaultMessage());
        }
    }


}
