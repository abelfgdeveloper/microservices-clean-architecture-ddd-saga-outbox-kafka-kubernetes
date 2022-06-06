package es.abelfgdeveloper.course.food.order.service.domain.port.output.repository;

import java.util.Optional;
import java.util.UUID;
import es.abelfgdeveloper.course.food.order.service.domain.entity.Customer;

public interface CustomerRepository {

  Optional<Customer> findCustomer(UUID customerId);
}
