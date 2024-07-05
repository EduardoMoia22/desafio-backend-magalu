package ry.edu.Magalu.services;

import org.springframework.stereotype.Service;
import ry.edu.Magalu.controllers.DTOs.ScheduleNotificationDTO;
import ry.edu.Magalu.entities.Notification;
import ry.edu.Magalu.entities.Status;
import ry.edu.Magalu.repositories.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void scheduleNotification(ScheduleNotificationDTO dto) {
        this.notificationRepository.save(dto.toNotification());
    }

    public Optional<Notification> findNotificationById(Long id) {
        return this.notificationRepository.findById(id);
    }

    public void cancelNotification(Long id){
        Optional<Notification> notification = this.notificationRepository.findById(id);

        if(notification.isPresent()) {
            notification.get().setStatus(Status.Values.CANCELED.toStatus());
            this.notificationRepository.save(notification.get());
        }
    }

    public void checkAndSend(LocalDateTime dateTime){
        List<Notification> notifications = this.notificationRepository.findByStatusInAndDateTimeBefore(List.of(
                Status.Values.PENDING.toStatus(),
                Status.Values.ERROR.toStatus()
        ), dateTime);

        notifications.forEach(sendNotification());

    }

    private Consumer<Notification> sendNotification() {
        return notification -> {
            //TODO - REALIZAR ENVIO DA NOTIFICAÇÃO

            notification.setStatus(Status.Values.SUCCESS.toStatus());
            this.notificationRepository.save(notification);
        };
    }

}
