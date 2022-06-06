package es.abelfgdeveloper.course.food.order.service.domain;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import es.abelfgdeveloper.course.food.order.service.domain.dto.message.RestaurantApprovalResponse;
import es.abelfgdeveloper.course.food.order.service.domain.port.input.message.listener.restaurant_approval.RestaurantApprovalResponseMessageListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@Service
public class RestaurantApprovalResponseMessageListenerImpl
    implements RestaurantApprovalResponseMessageListener {

  @Override
  public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {

  }

  @Override
  public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {

  }
}
