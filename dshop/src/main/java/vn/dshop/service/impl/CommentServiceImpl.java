package vn.dshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.dshop.entity.Comment;
import vn.dshop.repository.CommentRepository;
import vn.dshop.service.CommentService;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void save(Comment comment) {
        this.commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        this.commentRepository.deleteComment(comment);
    }

    @Override
    public List<Comment> getAllComment(int productid) {
        return this.commentRepository.getAllComment(productid);
    }
}
