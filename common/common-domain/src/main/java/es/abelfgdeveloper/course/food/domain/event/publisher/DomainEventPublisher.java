package es.abelfgdeveloper.course.food.domain.event.publisher;

import es.abelfgdeveloper.course.food.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

  void publish(T domainEvent);
}
