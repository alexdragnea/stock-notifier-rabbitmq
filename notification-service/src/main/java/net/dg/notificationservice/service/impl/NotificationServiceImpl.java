package net.dg.notificationservice.service.impl;

import lombok.AllArgsConstructor;
import net.dg.notificationservice.model.Notification;
import net.dg.notificationservice.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

  private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

  private final JavaMailSender emailSender;

  @Override
  @RabbitListener(queues = {"${rabbitmq.queue.name}"})
  public void sendEmail(Notification notification) {

    LOGGER.info("Notification received, notification object: {}", notification);

    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("noreply@alexdragnea.com");
    message.setTo(notification.getEmail());
    message.setSubject("Price dropped for stock " + notification.getSymbol());
    message.setText(
        "Price dropped for stock "
            + notification.getSymbol()
            + " and it's lower than threshold set "
            + notification.getThreshold()
            + "$"
            + ", current price "
            + notification.getPrice()
            + "$.");
    emailSender.send(message);

    LOGGER.info("Message sent to: {}, message: {}", message.getTo(), message.getText());
  }
}
