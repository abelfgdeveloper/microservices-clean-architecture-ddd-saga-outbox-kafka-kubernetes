package es.abelfgdeveloper.course.food.domain.exception;

public class DomainException extends RuntimeException {

  private static final long serialVersionUID = 2599840210894799938L;

  public DomainException(String message) {
    super(message);
  }

  public DomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
