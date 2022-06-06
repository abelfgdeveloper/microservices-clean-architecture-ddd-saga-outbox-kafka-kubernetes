package es.abelfgdeveloper.course.food.order.service.domain;

import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import es.abelfgdeveloper.course.food.order.service.domain.dto.track.TrackOrderQuery;
import es.abelfgdeveloper.course.food.order.service.domain.dto.track.TrackOrderResponse;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Order;
import es.abelfgdeveloper.course.food.order.service.domain.exception.OrderNotFoundException;
import es.abelfgdeveloper.course.food.order.service.domain.mapper.OrderDataMapper;
import es.abelfgdeveloper.course.food.order.service.domain.port.output.repository.OrderRepository;
import es.abelfgdeveloper.course.food.order.service.domain.valueobject.TrackingId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderTrackCommandHandler {

  private final OrderDataMapper orderDataMapper;
  private final OrderRepository orderRepository;

  @Transactional(readOnly = true)
  public TrackOrderResponse trackOrder(TrackOrderQuery query) {
    Optional<Order> order =
        orderRepository.findByTrackingId(new TrackingId(query.getOrderTrackingId()));

    if (order.isEmpty()) {
      log.warn("Could not find order with tracking id: {}", query.getOrderTrackingId());
      throw new OrderNotFoundException(
          "Could not find order with tracking id: " + query.getOrderTrackingId());
    }

    return orderDataMapper.orderToTrackOrderResponse(order.get());
  }
}
