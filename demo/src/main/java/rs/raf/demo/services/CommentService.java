package rs.raf.demo.services;

import rs.raf.demo.entities.Comment;
import rs.raf.demo.repositories.comment.CommentRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    public CommentService() {
    }

    @Inject
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment){
        return this.commentRepository.addComment(comment);
    }

    public List<Comment> allComments(Integer id){
        return this.commentRepository.allComments(id);
    }

    public Comment findComment(Integer id){
        return this.commentRepository.findComment(id);
    }
}
