package es.abelfgdeveloper.course.food.order.service.domain;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import es.abelfgdeveloper.course.food.order.service.domain.port.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import es.abelfgdeveloper.course.food.order.service.domain.port.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import es.abelfgdeveloper.course.food.order.service.domain.port.output.message.publisher.restaurant_approval.OrderPaidRestaurantRequestMessagePublisher;
import es.abelfgdeveloper.course.food.order.service.domain.port.output.repository.CustomerRepository;
import es.abelfgdeveloper.course.food.order.service.domain.port.output.repository.OrderRepository;
import es.abelfgdeveloper.course.food.order.service.domain.port.output.repository.RestaurantRepository;

@SpringBootApplication(scanBasePackages = "es.abelfgdeveloper.course")
public class OrderTestConfiguration {

  @Bean
  public OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher() {
    return Mockito.mock(OrderCreatedPaymentRequestMessagePublisher.class);
  }

  @Bean
  public OrderCancelledPaymentRequestMessagePublisher
      orderCancelledPaymentRequestMessagePublisher() {
    return Mockito.mock(OrderCancelledPaymentRequestMessagePublisher.class);
  }

  @Bean
  public OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher() {
    return Mockito.mock(OrderPaidRestaurantRequestMessagePublisher.class);
  }

  @Bean
  public OrderRepository orderRepository() {
    return Mockito.mock(OrderRepository.class);
  }

  @Bean
  public CustomerRepository customerRepository() {
    return Mockito.mock(CustomerRepository.class);
  }

  @Bean
  public RestaurantRepository restaurantRepository() {
    return Mockito.mock(RestaurantRepository.class);
  }

  @Bean
  public OrderDomainService orderDomainService() {
    return new OrderDomainServiceImpl();
  }
}
