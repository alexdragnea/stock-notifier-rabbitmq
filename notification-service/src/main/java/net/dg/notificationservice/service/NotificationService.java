package net.dg.notificationservice.service;

import net.dg.notificationservice.model.Notification;

public interface NotificationService {

    void sendEmail(Notification notification);
}
