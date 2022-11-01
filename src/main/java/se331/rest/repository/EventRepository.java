package se331.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.rest.entity.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findAll();
    Page<Event> findByNameIgnoreCaseContainingOrOrganizer_NameIgnoreCaseContaining(String name, String organizerName, Pageable pageRequest);
}
