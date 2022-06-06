package es.abelfgdeveloper.course.food.order.service.domain;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import es.abelfgdeveloper.course.food.order.service.domain.dto.create.CreateOrderCommand;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Customer;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Order;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Restaurant;
import es.abelfgdeveloper.course.food.order.service.domain.event.OrderCreatedEvent;
import es.abelfgdeveloper.course.food.order.service.domain.exception.OrderDomainException;
import es.abelfgdeveloper.course.food.order.service.domain.mapper.OrderDataMapper;
import es.abelfgdeveloper.course.food.order.service.domain.port.output.repository.CustomerRepository;
import es.abelfgdeveloper.course.food.order.service.domain.port.output.repository.OrderRepository;
import es.abelfgdeveloper.course.food.order.service.domain.port.output.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderCreateHelper {

  private final OrderDomainService orderDomainService;
  private final OrderRepository orderRepository;
  private final CustomerRepository customerRepository;
  private final RestaurantRepository restaurantRepository;
  private final OrderDataMapper orderDataMapper;

  @Transactional
  public OrderCreatedEvent persistOrder(CreateOrderCommand command) {
    checkCustomer(command.getCustomerId());
    Restaurant restaurant = checkRestaurant(command);
    Order order = orderDataMapper.createOrderCommandToOrder(command);
    OrderCreatedEvent orderCreatedEvent =
        orderDomainService.validateAndInitiateOrder(order, restaurant);
    Order orderSaved = saveOrder(order);
    log.info("Order is created with id: {}", orderSaved.getId().getValue());
    return orderCreatedEvent;
  }

  private void checkCustomer(UUID customerId) {
    Optional<Customer> customer = customerRepository.findCustomer(customerId);
    if (customer.isEmpty()) {
      log.warn("Could not find customer with customer id: {}", customerId);
      throw new OrderDomainException("Could not found customer with customer id: " + customerId);
    }
  }

  private Restaurant checkRestaurant(CreateOrderCommand command) {
    Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(command);
    Optional<Restaurant> restaurantOptional =
        restaurantRepository.findRestaurantInformation(restaurant);
    if (restaurantOptional.isEmpty()) {
      log.warn("Could not find customer with customer id: {}", restaurant.getId().getValue());
      throw new OrderDomainException(
          "Could not found customer with customer id: " + restaurant.getId().getValue());
    }
    return restaurantOptional.get();
  }

  private Order saveOrder(Order order) {
    Order orderResult = orderRepository.save(order);
    if (orderResult == null) {
      throw new OrderDomainException("Could not save order");
    }
    log.info("Order is saved with id: {}", orderResult.getId().getValue());
    return orderResult;
  }
}
