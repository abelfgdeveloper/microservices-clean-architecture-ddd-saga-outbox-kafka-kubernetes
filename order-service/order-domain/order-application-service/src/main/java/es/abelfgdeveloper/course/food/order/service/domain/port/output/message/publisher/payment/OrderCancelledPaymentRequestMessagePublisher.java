package es.abelfgdeveloper.course.food.order.service.domain.port.output.message.publisher.payment;

import es.abelfgdeveloper.course.food.domain.event.publisher.DomainEventPublisher;
import es.abelfgdeveloper.course.food.order.service.domain.event.OrderCancelledEvent;

public interface OrderCancelledPaymentRequestMessagePublisher
    extends DomainEventPublisher<OrderCancelledEvent> {}
