package se331.rest.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import  se331.rest.entity.Comment;
import se331.rest.repository.CommentRepository;

@Repository
public class CommentDaoDbImpl implements CommentDao{
    @Autowired
    CommentRepository commentRepository;
    @Override
public Comment save(Comment comment){
        return commentRepository.save(comment);

    }
    @Override
    public Page<Comment> getComment(Pageable pageRequest) {
        return commentRepository.findAll(pageRequest);
    }
}
