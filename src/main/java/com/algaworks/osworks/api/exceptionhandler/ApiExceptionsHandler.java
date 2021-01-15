package com.algaworks.osworks.api.exceptionhandler;

import com.algaworks.osworks.domain.exception.EntityNaoEncontradaException;
import com.algaworks.osworks.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionsHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(EntityNaoEncontradaException.class)
    public ResponseEntity<Object> handleEntityNaoEncontrada(NegocioException ex, WebRequest request){
        var status = HttpStatus.NOT_FOUND;

        var errors = new ResponseErrors();
        errors.setStatus(status.value());
        errors.setMessage(ex.getMessage());
        errors.setData(OffsetDateTime.now());

        return super.handleExceptionInternal(ex, errors ,new HttpHeaders(), status, request);

    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){
        var status = HttpStatus.BAD_REQUEST;

        var errors = new ResponseErrors();
        errors.setStatus(status.value());
        errors.setMessage(ex.getMessage());
        errors.setData(OffsetDateTime.now());

        return super.handleExceptionInternal(ex, errors ,new HttpHeaders(), status, request);

    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        var bodies =  new ArrayList<ResponseErrors.Body>();

        for(ObjectError error : ex.getBindingResult().getAllErrors()){
            String nome = ((FieldError)error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            bodies.add(new ResponseErrors.Body(nome, mensagem));
        }

        var errors = new ResponseErrors();
        errors.setStatus(status.value());
        errors.setMessage("Campos Inválidos");
        errors.setData(OffsetDateTime.now());
        errors.setBodys(bodies);

        return super.handleExceptionInternal(ex, errors ,headers, status, request);
    }
}
