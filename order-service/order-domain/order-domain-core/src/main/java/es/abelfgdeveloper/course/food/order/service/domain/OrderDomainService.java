package es.abelfgdeveloper.course.food.order.service.domain;

import java.util.List;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Order;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Restaurant;
import es.abelfgdeveloper.course.food.order.service.domain.event.OrderCancelledEvent;
import es.abelfgdeveloper.course.food.order.service.domain.event.OrderCreatedEvent;
import es.abelfgdeveloper.course.food.order.service.domain.event.OrderPaidEvent;

public interface OrderDomainService {
  
  OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);
  
  OrderPaidEvent payOrder(Order order);
  
  void approveOrder(Order order);
  
  OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);
  
  void cancelOrder(Order order, List<String> failureMessages);
}
