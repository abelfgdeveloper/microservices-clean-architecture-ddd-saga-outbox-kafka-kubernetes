package es.abelfgdeveloper.course.food.order.service.domain.port.input.message.listener.payment;

import es.abelfgdeveloper.course.food.order.service.domain.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {
  
  void paymentCompleted(PaymentResponse paymentResponse);
  
  void paymentCancelled(PaymentResponse paymentResponse);
}
