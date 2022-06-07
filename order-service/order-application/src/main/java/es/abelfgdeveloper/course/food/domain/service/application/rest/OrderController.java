package es.abelfgdeveloper.course.food.domain.service.application.rest;

import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.abelfgdeveloper.course.food.order.service.domain.dto.create.CreateOrderCommand;
import es.abelfgdeveloper.course.food.order.service.domain.dto.create.CreateOrderResponse;
import es.abelfgdeveloper.course.food.order.service.domain.dto.track.TrackOrderQuery;
import es.abelfgdeveloper.course.food.order.service.domain.dto.track.TrackOrderResponse;
import es.abelfgdeveloper.course.food.order.service.domain.port.input.service.OrderApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/orders", produces = "application/vdn.api.v1+json")
public class OrderController {

  private final OrderApplicationService orderApplicationService;

  @PostMapping
  public ResponseEntity<CreateOrderResponse> createOrder(
      @RequestBody CreateOrderCommand createOrderCommand) {
    log.info(
        "Create order for customer: {} at restaurant: {}",
        createOrderCommand.getCustomerId(),
        createOrderCommand.getRestaurantId());
    CreateOrderResponse orderResponse = orderApplicationService.createOrder(createOrderCommand);
    log.info("Order created with tacking id: {}", orderResponse.getOrderTrackingId());
    return ResponseEntity.ok(orderResponse);
  }

  @GetMapping("/{trackingId}")
  public ResponseEntity<TrackOrderResponse> getOrderByTrackingId(@PathVariable UUID trackingId) {
    TrackOrderResponse trackOrder =
        orderApplicationService.trackOrder(
            TrackOrderQuery.builder().orderTrackingId(trackingId).build());
    log.info("Returning order status with tracking id: {}", trackOrder.getOrderTrackingId());
    return ResponseEntity.ok(trackOrder);
  }
}
