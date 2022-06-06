package es.abelfgdeveloper.course.food.order.service.domain.dto.message;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import es.abelfgdeveloper.course.food.domain.valueobject.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class PaymentResponse {

  private String id;
  private String sagaId;
  private String orderId;
  private String paymentId;
  private String customerId;
  private BigDecimal price;
  private Instant createdAt;
  private PaymentStatus paymentStatus;
  private List<String> failureMessages;
}
