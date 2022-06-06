package es.abelfgdeveloper.course.food.order.service.domain.port.output.repository;

import java.util.Optional;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Order;
import es.abelfgdeveloper.course.food.order.service.domain.valueobject.TrackingId;

public interface OrderRepository {

  Order save(Order order);
  
  Optional<Order> findByTrackingId(TrackingId trackingId);
}
