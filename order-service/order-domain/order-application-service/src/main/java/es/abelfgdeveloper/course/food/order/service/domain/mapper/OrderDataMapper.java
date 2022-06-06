package es.abelfgdeveloper.course.food.order.service.domain.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import es.abelfgdeveloper.course.food.domain.valueobject.CustomerId;
import es.abelfgdeveloper.course.food.domain.valueobject.Money;
import es.abelfgdeveloper.course.food.domain.valueobject.OrderId;
import es.abelfgdeveloper.course.food.domain.valueobject.ProductId;
import es.abelfgdeveloper.course.food.domain.valueobject.RestaurantId;
import es.abelfgdeveloper.course.food.order.service.domain.dto.create.CreateOrderCommand;
import es.abelfgdeveloper.course.food.order.service.domain.dto.create.CreateOrderResponse;
import es.abelfgdeveloper.course.food.order.service.domain.dto.create.OrderAddress;
import es.abelfgdeveloper.course.food.order.service.domain.dto.track.TrackOrderResponse;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Order;
import es.abelfgdeveloper.course.food.order.service.domain.entity.OrderItem;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Product;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Restaurant;
import es.abelfgdeveloper.course.food.order.service.domain.valueobject.OrderItemId;
import es.abelfgdeveloper.course.food.order.service.domain.valueobject.StreetAddress;

@Component
public class OrderDataMapper {

  public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
    return Restaurant.builder()
        .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
        .products(
            createOrderCommand.getItems().stream()
                .map(orderItem -> new Product(new ProductId(orderItem.getProductId())))
                .collect(Collectors.toList()))
        .build();
  }

  public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
    return Order.builder()
        .customerId(new CustomerId(createOrderCommand.getCustomerId()))
        .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
        .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
        .price(new Money(createOrderCommand.getPrice()))
        .items(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
        .build();
  }

  private StreetAddress orderAddressToStreetAddress(OrderAddress orderAddress) {
    return new StreetAddress(
        UUID.randomUUID(),
        orderAddress.getStreet(),
        orderAddress.getPostalCode(),
        orderAddress.getCity());
  }

  private List<OrderItem> orderItemsToOrderItemEntities(
      List<es.abelfgdeveloper.course.food.order.service.domain.dto.create.OrderItem> orderItems) {
    return orderItems.stream()
        .map(
            orderItem ->
                OrderItem.builder()
                    .product(new Product(new ProductId(orderItem.getProductId())))
                    .price(new Money(orderItem.getPrice()))
                    .quantity(orderItem.getQuantity())
                    .subTotal(new Money(orderItem.getSubTotal()))
                    .build())
        .collect(Collectors.toList());
  }

  public CreateOrderResponse orderToCreateOrderResponse(Order order, String message) {
    return CreateOrderResponse.builder()
        .orderTrackingId(order.getTrackingId().getValue())
        .orderStatus(order.getOrderStatus())
        .message(message)
        .build();
  }

  public TrackOrderResponse orderToTrackOrderResponse(Order order) {
    return TrackOrderResponse.builder()
        .orderTrackingId(order.getTrackingId().getValue())
        .orderStatus(order.getOrderStatus())
        .failureMessages(order.getFailureMessages())
        .build();
  }
}
