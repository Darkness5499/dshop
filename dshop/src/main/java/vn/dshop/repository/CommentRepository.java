package vn.dshop.repository;

import vn.dshop.entity.Comment;

import java.util.List;

public interface CommentRepository {
    void save(Comment comment);
    void deleteComment(Comment comment);
    List<Comment> getAllComment(int productid);
}
