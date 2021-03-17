package vn.dshop.repository;

import vn.dshop.entity.ProductComment;

import java.util.List;

public interface ProductCommentRepository {
    void save(ProductComment productComment);
    void deleteComment(ProductComment productComment);
    List<ProductComment> getAllComment();
}
