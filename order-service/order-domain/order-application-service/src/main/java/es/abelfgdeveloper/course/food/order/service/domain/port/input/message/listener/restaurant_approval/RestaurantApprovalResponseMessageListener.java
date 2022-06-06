package es.abelfgdeveloper.course.food.order.service.domain.port.input.message.listener.restaurant_approval;

import es.abelfgdeveloper.course.food.order.service.domain.dto.message.RestaurantApprovalResponse;

public interface RestaurantApprovalResponseMessageListener {
  
  void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);
  
  void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
  
}
