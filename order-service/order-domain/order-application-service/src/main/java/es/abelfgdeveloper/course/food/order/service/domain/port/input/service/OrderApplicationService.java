package es.abelfgdeveloper.course.food.order.service.domain.port.input.service;

import javax.validation.Valid;
import es.abelfgdeveloper.course.food.order.service.domain.dto.create.CreateOrderCommand;
import es.abelfgdeveloper.course.food.order.service.domain.dto.create.CreateOrderResponse;
import es.abelfgdeveloper.course.food.order.service.domain.dto.track.TrackOrderQuery;
import es.abelfgdeveloper.course.food.order.service.domain.dto.track.TrackOrderResponse;

public interface OrderApplicationService {
  
  CreateOrderResponse createOrder(@Valid CreateOrderCommand command);
  
  TrackOrderResponse trackOrder(@Valid TrackOrderQuery query);
  
}
