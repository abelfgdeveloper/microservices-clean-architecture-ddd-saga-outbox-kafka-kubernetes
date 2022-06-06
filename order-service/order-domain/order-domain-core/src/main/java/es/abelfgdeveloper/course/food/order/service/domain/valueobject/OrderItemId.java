package es.abelfgdeveloper.course.food.order.service.domain.valueobject;

import es.abelfgdeveloper.course.food.domain.valueobject.BaseId;

public class OrderItemId extends BaseId<Long> {
  
  public OrderItemId(Long value) {
    super(value);
  }
}
