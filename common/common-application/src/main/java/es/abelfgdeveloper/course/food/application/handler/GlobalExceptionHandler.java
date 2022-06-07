package es.abelfgdeveloper.course.food.application.handler;

import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = {Exception.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public ErrorDTO handleException(Exception exception) {
    log.error(exception.getMessage(), exception);
    return ErrorDTO.builder()
        .code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
        .message("Unexpected error!")
        .build();
  }

  @ExceptionHandler(value = {ValidationException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorDTO handleException(ValidationException validationException) {
    String message = null;
    if (validationException instanceof ConstraintViolationException) {
      message = extractViolationsFromException((ConstraintViolationException) validationException);
    } else {
      message = validationException.getMessage();
    }
    log.error(message, validationException);
    return ErrorDTO.builder()
        .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message(message)
        .build();
  }

  private String extractViolationsFromException(
      ConstraintViolationException constraintViolationException) {
    return constraintViolationException.getConstraintViolations().stream()
        .map(ConstraintViolation::getMessage)
        .collect(Collectors.joining("--"));
  }
}
