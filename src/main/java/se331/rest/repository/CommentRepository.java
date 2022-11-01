package se331.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.rest.entity.Comment;
import se331.rest.entity.Event;


import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAll();
}
