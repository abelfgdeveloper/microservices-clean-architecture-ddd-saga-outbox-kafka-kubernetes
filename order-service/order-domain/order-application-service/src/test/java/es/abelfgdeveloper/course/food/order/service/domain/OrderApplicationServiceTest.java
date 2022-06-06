package es.abelfgdeveloper.course.food.order.service.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import es.abelfgdeveloper.course.food.domain.valueobject.CustomerId;
import es.abelfgdeveloper.course.food.domain.valueobject.Money;
import es.abelfgdeveloper.course.food.domain.valueobject.OrderId;
import es.abelfgdeveloper.course.food.domain.valueobject.OrderStatus;
import es.abelfgdeveloper.course.food.domain.valueobject.ProductId;
import es.abelfgdeveloper.course.food.domain.valueobject.RestaurantId;
import es.abelfgdeveloper.course.food.order.service.domain.dto.create.CreateOrderCommand;
import es.abelfgdeveloper.course.food.order.service.domain.dto.create.CreateOrderResponse;
import es.abelfgdeveloper.course.food.order.service.domain.dto.create.OrderAddress;
import es.abelfgdeveloper.course.food.order.service.domain.dto.create.OrderItem;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Customer;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Order;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Product;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Restaurant;
import es.abelfgdeveloper.course.food.order.service.domain.exception.OrderDomainException;
import es.abelfgdeveloper.course.food.order.service.domain.mapper.OrderDataMapper;
import es.abelfgdeveloper.course.food.order.service.domain.port.input.service.OrderApplicationService;
import es.abelfgdeveloper.course.food.order.service.domain.port.output.repository.CustomerRepository;
import es.abelfgdeveloper.course.food.order.service.domain.port.output.repository.OrderRepository;
import es.abelfgdeveloper.course.food.order.service.domain.port.output.repository.RestaurantRepository;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(classes = OrderTestConfiguration.class)
class OrderApplicationServiceTest {

  @Autowired private OrderApplicationService orderApplicationService;

  @Autowired private OrderDataMapper orderDataMapper;

  @Autowired private OrderRepository orderRepository;

  @Autowired private CustomerRepository customerRepository;

  @Autowired private RestaurantRepository restaurantRepository;

  private CreateOrderCommand createOrderCommand;
  private CreateOrderCommand createOrderCommandWrongPrice;
  private CreateOrderCommand createOrderCommandWrongProductPrice;
  private final UUID CUSTOMER_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41");
  private final UUID RESTAURANT_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb45");
  private final UUID PRODUCT_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb48");
  private final UUID ORDER_ID = UUID.fromString("15a497c1-0f4b-4eff-b9f4-c402c8c07afb");

  @BeforeAll
  public void init() {
      createOrderCommand = CreateOrderCommand.builder()
              .customerId(CUSTOMER_ID)
              .restaurantId(RESTAURANT_ID)
              .address(OrderAddress.builder()
                      .street("street_1")
                      .postalCode("1000AB")
                      .city("Paris")
                      .build())
              .price(new BigDecimal("200.00"))
              .items(List.of(OrderItem.builder()
                              .productId(PRODUCT_ID)
                              .quantity(1)
                              .price(new BigDecimal("50.00"))
                              .subTotal(new BigDecimal("50.00"))
                              .build(),
                      OrderItem.builder()
                              .productId(PRODUCT_ID)
                              .quantity(3)
                              .price(new BigDecimal("50.00"))
                              .subTotal(new BigDecimal("150.00"))
                              .build()))
              .build();

      createOrderCommandWrongPrice = CreateOrderCommand.builder()
              .customerId(CUSTOMER_ID)
              .restaurantId(RESTAURANT_ID)
              .address(OrderAddress.builder()
                      .street("street_1")
                      .postalCode("1000AB")
                      .city("Paris")
                      .build())
              .price(new BigDecimal("250.00"))
              .items(List.of(OrderItem.builder()
                              .productId(PRODUCT_ID)
                              .quantity(1)
                              .price(new BigDecimal("50.00"))
                              .subTotal(new BigDecimal("50.00"))
                              .build(),
                      OrderItem.builder()
                              .productId(PRODUCT_ID)
                              .quantity(3)
                              .price(new BigDecimal("50.00"))
                              .subTotal(new BigDecimal("150.00"))
                              .build()))
              .build();

      createOrderCommandWrongProductPrice = CreateOrderCommand.builder()
              .customerId(CUSTOMER_ID)
              .restaurantId(RESTAURANT_ID)
              .address(OrderAddress.builder()
                      .street("street_1")
                      .postalCode("1000AB")
                      .city("Paris")
                      .build())
              .price(new BigDecimal("210.00"))
              .items(List.of(OrderItem.builder()
                              .productId(PRODUCT_ID)
                              .quantity(1)
                              .price(new BigDecimal("60.00"))
                              .subTotal(new BigDecimal("60.00"))
                              .build(),
                      OrderItem.builder()
                              .productId(PRODUCT_ID)
                              .quantity(3)
                              .price(new BigDecimal("50.00"))
                              .subTotal(new BigDecimal("150.00"))
                              .build()))
              .build();

      Customer customer = new Customer();
      customer.setId(new CustomerId(CUSTOMER_ID));

      Restaurant restaurantResponse = Restaurant.builder()
              .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
              .products(List.of(new Product(new ProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("50.00"))),
                      new Product(new ProductId(PRODUCT_ID), "product-2", new Money(new BigDecimal("50.00")))))
              .active(true)
              .build();

      Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
      order.setId(new OrderId(ORDER_ID));

      Mockito.when(customerRepository.findCustomer(CUSTOMER_ID)).thenReturn(Optional.of(customer));
      Mockito.when(restaurantRepository.findRestaurantInformation(orderDataMapper.createOrderCommandToRestaurant(createOrderCommand)))
              .thenReturn(Optional.of(restaurantResponse));
      Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
  }

  @Test
  void testCreateOrder() {
     CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
     assertEquals(createOrderResponse.getOrderStatus(), OrderStatus.PENDING);
     assertEquals(createOrderResponse.getMessage(), "Order created successfully");
     assertNotNull(createOrderResponse.getOrderTrackingId());
  }

  @Test
  void testCreateOrderWithWrongTotalPrice() {
     OrderDomainException orderDomainException = assertThrows(OrderDomainException.class,
              () -> orderApplicationService.createOrder(createOrderCommandWrongPrice));
     assertEquals(orderDomainException.getMessage(),
             "Total price: 250.00 is not equal to Order items total: 200.00!");
  }

  @Test
  void testCreateOrderWithWrongProductPrice() {
     OrderDomainException orderDomainException = assertThrows(OrderDomainException.class,
              () -> orderApplicationService.createOrder(createOrderCommandWrongProductPrice));
     assertEquals(orderDomainException.getMessage(),
             "Order item price: 60.00 is not valid for product " + PRODUCT_ID);
  }

  @Test
  void testCreateOrderWithPassiveRestaurant() {
     Restaurant restaurantResponse = Restaurant.builder()
              .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
              .products(List.of(new Product(new ProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("50.00"))),
                      new Product(new ProductId(PRODUCT_ID), "product-2", new Money(new BigDecimal("50.00")))))
              .active(false)
              .build();
     Mockito.when(restaurantRepository.findRestaurantInformation(orderDataMapper.createOrderCommandToRestaurant(createOrderCommand)))
             .thenReturn(Optional.of(restaurantResponse));
     OrderDomainException orderDomainException = assertThrows(OrderDomainException.class,
             () -> orderApplicationService.createOrder(createOrderCommand));
     assertEquals(orderDomainException.getMessage(),
             "Restaurant with id " + RESTAURANT_ID + " is currently not active!");
  }
}
