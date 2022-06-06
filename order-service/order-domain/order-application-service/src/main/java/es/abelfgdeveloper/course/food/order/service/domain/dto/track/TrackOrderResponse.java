package es.abelfgdeveloper.course.food.order.service.domain.dto.track;

import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import es.abelfgdeveloper.course.food.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class TrackOrderResponse {

  @NotNull private final UUID orderTrackingId;

  @NotNull private final OrderStatus orderStatus;

  private final List<String> failureMessages;
}
