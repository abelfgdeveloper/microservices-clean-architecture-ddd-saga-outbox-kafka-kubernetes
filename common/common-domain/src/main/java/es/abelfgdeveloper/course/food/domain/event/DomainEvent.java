package es.abelfgdeveloper.course.food.domain.event;

public interface DomainEvent<T> {

  void fire();
}
