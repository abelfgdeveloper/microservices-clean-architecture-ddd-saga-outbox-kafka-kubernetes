package es.abelfgdeveloper.course.food.order.service.domain.port.output.message.publisher.restaurant_approval;

import es.abelfgdeveloper.course.food.domain.event.publisher.DomainEventPublisher;
import es.abelfgdeveloper.course.food.order.service.domain.event.OrderPaidEvent;

public interface OrderPaidRestaurantRequestMessagePublisher
    extends DomainEventPublisher<OrderPaidEvent> {}
