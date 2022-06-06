package es.abelfgdeveloper.course.food.kafka.producer.exception;

public class KafkaProducerException extends RuntimeException {

  private static final long serialVersionUID = 4457095905954042876L;

  public KafkaProducerException(String message) {
    super(message);
  }
}
