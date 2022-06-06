package es.abelfgdeveloper.course.food.order.service.domain.exception;

import es.abelfgdeveloper.course.food.domain.exception.DomainException;

public class OrderNotFoundException extends DomainException {

  private static final long serialVersionUID = 2934816743185961330L;

  public OrderNotFoundException(String message) {
    super(message);
  }
}
