package es.abelfgdeveloper.course.food.order.service.domain.entity;

import es.abelfgdeveloper.course.food.domain.entity.BaseEntity;
import es.abelfgdeveloper.course.food.domain.valueobject.Money;
import es.abelfgdeveloper.course.food.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
  private String name;
  private Money price;

  public Product(ProductId productId, String name, Money price) {
    super.setId(productId);
    this.name = name;
    this.price = price;
  }

  public Product(ProductId productId) {
    super.setId(productId);
  }

  public void updateWithConfirmedNameAndPrice(String name, Money price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public Money getPrice() {
    return price;
  }
}
