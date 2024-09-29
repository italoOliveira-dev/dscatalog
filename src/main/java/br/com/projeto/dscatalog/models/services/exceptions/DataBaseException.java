package br.com.projeto.dscatalog.models.services.exceptions;

public class DataBaseException extends RuntimeException{
  public DataBaseException(String message) {
    super(message);
  }
}
