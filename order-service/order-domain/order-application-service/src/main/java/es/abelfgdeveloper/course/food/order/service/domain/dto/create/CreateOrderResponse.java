package es.abelfgdeveloper.course.food.order.service.domain.dto.create;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import es.abelfgdeveloper.course.food.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class CreateOrderResponse {

  @NotNull private final UUID orderTrackingId;

  @NotNull private final OrderStatus orderStatus;

  @NotNull private final String message;
}
