package es.abelfgdeveloper.course.food.order.service.domain.dto.message;

import java.time.Instant;
import java.util.List;
import es.abelfgdeveloper.course.food.domain.valueobject.OrderApprovalStatus;

public class RestaurantApprovalResponse {

  private String id;
  private String sagaId;
  private String orderId;
  private String restaurantId;
  private Instant createdAt;
  private OrderApprovalStatus orderApprovalStatus;
  private List<String> failureMessages;
}
