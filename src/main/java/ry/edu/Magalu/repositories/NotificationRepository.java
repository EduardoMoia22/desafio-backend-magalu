package ry.edu.Magalu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ry.edu.Magalu.entities.Notification;
import ry.edu.Magalu.entities.Status;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification>findByStatusInAndDateTimeBefore(List<Status> status, LocalDateTime dateTime);
}
