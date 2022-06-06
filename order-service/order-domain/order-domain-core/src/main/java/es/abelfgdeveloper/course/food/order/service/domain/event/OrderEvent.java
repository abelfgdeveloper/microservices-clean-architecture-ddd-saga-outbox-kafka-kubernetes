package es.abelfgdeveloper.course.food.order.service.domain.event;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import es.abelfgdeveloper.course.food.domain.DomainConstants;
import es.abelfgdeveloper.course.food.domain.event.DomainEvent;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Order;

public abstract class OrderEvent implements DomainEvent<Order> {
  private final Order order;
  private final ZonedDateTime createdAt;

  public OrderEvent(Order order) {
    this.order = order;
    this.createdAt = ZonedDateTime.now(ZoneId.of(DomainConstants.UTC));
  }

  public Order getOrder() {
    return order;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }
}
