package es.abelfgdeveloper.course.food.order.service.domain.valueobject;

import java.util.UUID;
import es.abelfgdeveloper.course.food.domain.valueobject.BaseId;

public class TrackingId extends BaseId<UUID> {

  public TrackingId(UUID value) {
    super(value);
  }
}
