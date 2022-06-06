package es.abelfgdeveloper.course.food.order.service.domain;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import es.abelfgdeveloper.course.food.domain.event.publisher.DomainEventPublisher;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Order;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Product;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Restaurant;
import es.abelfgdeveloper.course.food.order.service.domain.event.OrderCancelledEvent;
import es.abelfgdeveloper.course.food.order.service.domain.event.OrderCreatedEvent;
import es.abelfgdeveloper.course.food.order.service.domain.event.OrderPaidEvent;
import es.abelfgdeveloper.course.food.order.service.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

  @Override
  public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
    validateRestaurant(restaurant);
    setOrderProductInformation(order, restaurant);
    order.validateInitialOrder();
    order.validateOrder();
    order.initializeOrder();
    log.info("Order with id: {} is initiated", order.getId().getValue());
    return new OrderCreatedEvent(order);
  }

  @Override
  public OrderPaidEvent payOrder(Order order) {
    order.pay();
    log.info("Order with id: {} is paid", order.getId().getValue());
    return new OrderPaidEvent(order);
  }

  @Override
  public void approveOrder(Order order) {
      order.approve();
      log.info("Order with id: {} is approved", order.getId().getValue());
  }

  @Override
  public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
    order.initCancel(failureMessages);
    log.info("Order payment is cancelling for order id: {}", order.getId().getValue());
    return new OrderCancelledEvent(order);
  }

  @Override
  public void cancelOrder(Order order, List<String> failureMessages) {
    order.cancel(failureMessages);
    log.info("Order with id: {} is cancelled", order.getId().getValue());
  }

  private void validateRestaurant(Restaurant restaurant) {
    if (!restaurant.isActive()) {
      throw new OrderDomainException(
          "Restaurant with id " + restaurant.getId().getValue() + " is currently not active!");
    }
  }

  private void setOrderProductInformation(Order order, Restaurant restaurant) {
    order
        .getItems()
        .forEach(
            orderItem ->
                restaurant
                    .getProducts()
                    .forEach(
                        restaurantProduct -> {
                          Product currentProduct = orderItem.getProduct();
                          if (currentProduct.equals(restaurantProduct)) {
                            currentProduct.updateWithConfirmedNameAndPrice(
                                restaurantProduct.getName(), restaurantProduct.getPrice());
                          }
                        }));
  }
}
