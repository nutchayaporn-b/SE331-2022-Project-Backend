package se331.rest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.rest.entity.Comment;
import se331.rest.entity.Event;
import se331.rest.service.CommentService;
import se331.rest.service.EventService;
import se331.rest.util.LabMapper;
@Controller
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    EventService eventService;

    @GetMapping("/comment")
    ResponseEntity<?> getComments() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getCommentDto(commentService.getAllComment()));
    }

    @PostMapping("/comment/event/{id}")
    ResponseEntity<?> addComment(@PathVariable("id") Long id,@RequestBody Comment comment){
        Event output = eventService.getEvent(id);
        output.getCommentList().add(comment);
        Comment output2 = commentService.save(comment);
        return ResponseEntity.ok(LabMapper.INSTANCE.getCommentDto(output2));
    }
}
