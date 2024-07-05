package ry.edu.Magalu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ry.edu.Magalu.entities.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
