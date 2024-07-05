package ry.edu.Magalu.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ry.edu.Magalu.controllers.DTOs.ScheduleNotificationDTO;
import ry.edu.Magalu.entities.Notification;
import ry.edu.Magalu.services.NotificationService;

import java.util.Optional;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Void> scheduleNotification(@RequestBody ScheduleNotificationDTO dto){
        this.notificationService.scheduleNotification(dto);

        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<Notification> findNotificationById(@PathVariable("notificationId") Long notificationId){
        Optional<Notification> notification = this.notificationService.findNotificationById(notificationId);

        if(notification.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(notification.get());
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteNotificationById(@PathVariable("notificationId") Long notificationId){
        this.notificationService.cancelNotification(notificationId);
        return ResponseEntity.noContent().build();
    }
}
