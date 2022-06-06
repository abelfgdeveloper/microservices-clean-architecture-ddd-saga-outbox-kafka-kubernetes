package es.abelfgdeveloper.course.food.order.service.domain.entity;

import es.abelfgdeveloper.course.food.domain.entity.AggregateRoot;
import es.abelfgdeveloper.course.food.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {
  public Customer() {}

  public Customer(CustomerId customerId) {
    super.setId(customerId);
  }
}
