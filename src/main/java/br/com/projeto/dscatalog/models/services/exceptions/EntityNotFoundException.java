package br.com.projeto.dscatalog.models.services.exceptions;

public class EntityNotFoundException extends RuntimeException{
  
  public EntityNotFoundException(String message) {
    super(message);
  }
}
