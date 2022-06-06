package es.abelfgdeveloper.course.food.order.service.domain.exception;

import es.abelfgdeveloper.course.food.domain.exception.DomainException;

public class OrderDomainException extends DomainException {

  private static final long serialVersionUID = -1734094524641950288L;

  public OrderDomainException(String message) {
    super(message);
  }

  public OrderDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
