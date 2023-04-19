package net.dg.thresholdservice.service.rabbitmq;

import net.dg.notificationservice.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

  private static final Logger LOGGER = LoggerFactory.getLogger(NotificationProducer.class);

  @Value("${rabbitmq.exchange.name}")
  private String exchange;

  @Value("${rabbitmq.routing.key}")
  private String routingKey;

  private final RabbitTemplate rabbitTemplate;

  public NotificationProducer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendMessage(Notification notification) {
    LOGGER.info("Notification sent to RabbitMQ queue, with notification object: {}", notification);
    rabbitTemplate.convertAndSend(exchange, routingKey, notification);
  }
}
