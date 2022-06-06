//package es.abelfgdeveloper.course.food.order.service.domain;
//
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.ApplicationEventPublisherAware;
//import org.springframework.stereotype.Component;
//import es.abelfgdeveloper.course.food.domain.event.publisher.DomainEventPublisher;
//import es.abelfgdeveloper.course.food.order.service.domain.event.OrderCreatedEvent;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Component
//public class ApplicationDomainEventPublisher
//    implements ApplicationEventPublisherAware, DomainEventPublisher<OrderCreatedEvent> {
//
//  private ApplicationEventPublisher applicationEventPublisher;
//
//  @Override
//  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
//    this.applicationEventPublisher = applicationEventPublisher;
//  }
//
//  @Override
//  public void publish(OrderCreatedEvent event) {
//    this.applicationEventPublisher.publishEvent(event);
//    log.info(
//        "OrderCreatedEvent is published for order id: {}", event.getOrder().getId().getValue());
//  }
//}
