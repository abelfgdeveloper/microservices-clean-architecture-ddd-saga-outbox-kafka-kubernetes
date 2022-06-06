package es.abelfgdeveloper.course.food.order.service.domain.event;

import es.abelfgdeveloper.course.food.order.service.domain.entity.Order;

public class OrderCancelledEvent extends OrderEvent {

  public OrderCancelledEvent(Order order) {
    super(order);
  }

  @Override
  public void fire() {
    // TODO Auto-generated method stub

  }
}
