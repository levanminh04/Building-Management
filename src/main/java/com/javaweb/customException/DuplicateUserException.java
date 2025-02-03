package com.javaweb.customException;

public class DuplicateUserException extends RuntimeException {
  public DuplicateUserException(String message) {
    super(message);
  }
}
