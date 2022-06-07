package es.abelfgdeveloper.course.food.domain.service.application.rest.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import es.abelfgdeveloper.course.food.application.handler.ErrorDTO;
import es.abelfgdeveloper.course.food.application.handler.GlobalExceptionHandler;
import es.abelfgdeveloper.course.food.order.service.domain.exception.OrderDomainException;
import es.abelfgdeveloper.course.food.order.service.domain.exception.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class OrderGlobalExceptionHandler extends GlobalExceptionHandler {

  @ExceptionHandler(value = {OrderDomainException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorDTO handleException(OrderDomainException orderDomainException) {
    log.error(orderDomainException.getMessage(), orderDomainException);
    return ErrorDTO.builder()
        .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message(orderDomainException.getMessage())
        .build();
  }

  @ExceptionHandler(value = {OrderNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorDTO handleException(OrderNotFoundException orderNotFoundException) {
    log.error(orderNotFoundException.getMessage(), orderNotFoundException);
    return ErrorDTO.builder()
        .code(HttpStatus.NOT_FOUND.getReasonPhrase())
        .message(orderNotFoundException.getMessage())
        .build();
  }
}
