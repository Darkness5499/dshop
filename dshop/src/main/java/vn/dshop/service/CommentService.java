package vn.dshop.service;

import vn.dshop.entity.Comment;

import java.util.List;

public interface CommentService {
    void save(Comment comment);
    void deleteComment(Comment comment);
    List<Comment> getAllComment(int productid);
}
