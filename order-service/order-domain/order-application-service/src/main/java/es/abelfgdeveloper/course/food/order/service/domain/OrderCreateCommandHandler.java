package es.abelfgdeveloper.course.food.order.service.domain;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import es.abelfgdeveloper.course.food.order.service.domain.dto.create.CreateOrderCommand;
import es.abelfgdeveloper.course.food.order.service.domain.dto.create.CreateOrderResponse;
import es.abelfgdeveloper.course.food.order.service.domain.event.OrderCreatedEvent;
import es.abelfgdeveloper.course.food.order.service.domain.mapper.OrderDataMapper;
import es.abelfgdeveloper.course.food.order.service.domain.port.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderCreateCommandHandler {

  private final OrderCreateHelper orderCreateHelper;
  private final OrderDataMapper orderDataMapper;
  private final OrderCreatedPaymentRequestMessagePublisher
      orderCreatedPaymentRequestMessagePublisher;
//  private final ApplicationDomainEventPublisher applicationDomainEventPublisher;

  
  @Transactional
  public CreateOrderResponse createOrder(CreateOrderCommand command) {
    OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(command);
    orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
//    applicationDomainEventPublisher.publish(orderCreatedEvent);
    return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder(), "Order created successfully");
  }
}
