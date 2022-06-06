package es.abelfgdeveloper.course.food.order.service.domain;

import javax.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import es.abelfgdeveloper.course.food.order.service.domain.dto.create.CreateOrderCommand;
import es.abelfgdeveloper.course.food.order.service.domain.dto.create.CreateOrderResponse;
import es.abelfgdeveloper.course.food.order.service.domain.dto.track.TrackOrderQuery;
import es.abelfgdeveloper.course.food.order.service.domain.dto.track.TrackOrderResponse;
import es.abelfgdeveloper.course.food.order.service.domain.port.input.service.OrderApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RequiredArgsConstructor
@Service
class OrderApplicationServiceImpl implements OrderApplicationService {

  private final OrderCreateCommandHandler orderCreateCommandHandler; 
  private final OrderTrackCommandHandler orderTrackCommandHandler;
  
  @Override
  public CreateOrderResponse createOrder(@Valid CreateOrderCommand command) {
    return orderCreateCommandHandler.createOrder(command);
  }

  @Override
  public TrackOrderResponse trackOrder(@Valid TrackOrderQuery query) {
    return orderTrackCommandHandler.trackOrder(query);
  }
}
