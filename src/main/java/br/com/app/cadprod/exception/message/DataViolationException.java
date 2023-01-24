package br.com.app.cadprod.exception.message;

public class DataViolationException extends RuntimeException {
  public DataViolationException(String message) {
    super(message);
  }
}
