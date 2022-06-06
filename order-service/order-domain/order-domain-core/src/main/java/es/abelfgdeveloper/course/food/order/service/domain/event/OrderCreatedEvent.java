package es.abelfgdeveloper.course.food.order.service.domain.event;

import es.abelfgdeveloper.course.food.order.service.domain.entity.Order;

public class OrderCreatedEvent extends OrderEvent {

  public OrderCreatedEvent(Order order) {
    super(order);
  }

  @Override
  public void fire() {
    // TODO Auto-generated method stub

  }
}
