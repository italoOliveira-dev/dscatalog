package br.com.projeto.dscatalog.web.handlerException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.projeto.dscatalog.models.services.exceptions.DataBaseException;
import br.com.projeto.dscatalog.models.services.exceptions.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class HandlerApiExceptions {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<MessageError> methodArgumentNotValidException(MethodArgumentNotValidException ex,
      HttpServletRequest request, BindingResult result) {
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

    log.error("Error - ", ex);
    return ResponseEntity
        .status(status)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new MessageError(request, status, "Campo(s) inválido(s)", result));
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<MessageError> entityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    
    log.error("Error - ", ex);
    return ResponseEntity
          .status(status)
          .contentType(MediaType.APPLICATION_JSON)
          .body(new MessageError(request, status, ex.getMessage()));
  }

  @ExceptionHandler(DataBaseException.class)
  public ResponseEntity<MessageError> dataBaseException(DataBaseException ex, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;

    log.error("Error - ", ex);
    return ResponseEntity
          .status(status)
          .contentType(MediaType.APPLICATION_JSON)
          .body(new MessageError(request, status, ex.getMessage()));
  }
}
