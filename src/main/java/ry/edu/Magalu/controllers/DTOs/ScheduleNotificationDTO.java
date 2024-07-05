package ry.edu.Magalu.controllers.DTOs;

import ry.edu.Magalu.entities.Channel;
import ry.edu.Magalu.entities.Notification;
import ry.edu.Magalu.entities.Status;

import java.time.LocalDateTime;

public record ScheduleNotificationDTO(
        LocalDateTime dateTime,
        String destination,
        String message,
        Channel.Values channel
) {
    public Notification toNotification() {
        return new Notification(
                dateTime,
                destination,
                message,
                channel.toChannel(),
                Status.Values.PENDING.toStatus()
        );
    }
}
