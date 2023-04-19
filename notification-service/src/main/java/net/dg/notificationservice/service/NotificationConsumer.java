package net.dg.notificationservice.service;

import net.dg.notificationservice.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumer.class);

  @RabbitListener(queues = {"${rabbitmq.queue.name}"})
  public void consume(Notification notification) {
    LOGGER.info("Notification received, notification object: {}", notification);
  }
}
