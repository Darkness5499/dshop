package vn.dshop.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.dshop.entity.ProductComment;
import vn.dshop.repository.ProductCommentRepository;

import java.util.List;

@Repository
public class ProductCommentRepositoryImpl implements ProductCommentRepository {
    private SessionFactory sessionFactory;
    @Autowired
    public ProductCommentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(ProductComment productComment) {

    }

    @Override
    public void deleteComment(ProductComment productComment) {

    }

    @Override
    public List<ProductComment> getAllComment() {
        return null;
    }
}
