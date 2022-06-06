package es.abelfgdeveloper.course.food.order.service.domain.dto.track;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class TrackOrderQuery {
  
  @NotNull
  private final UUID orderTrackingId;
  
}
