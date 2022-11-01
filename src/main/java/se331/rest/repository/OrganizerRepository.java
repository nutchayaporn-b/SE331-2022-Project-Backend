package se331.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.rest.entity.Organizer;

public interface OrganizerRepository extends JpaRepository<Organizer,Long> {
}

