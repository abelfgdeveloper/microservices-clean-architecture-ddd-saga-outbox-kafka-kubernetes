package es.abelfgdeveloper.course.food.order.service.domain.port.output.repository;

import java.util.Optional;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Restaurant;

public interface RestaurantRepository {

  Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
