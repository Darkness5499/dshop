package vn.dshop.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(productComment);
    }

    @Override
    public void deleteComment(ProductComment productComment) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(productComment);
    }

    @Override
    public List<ProductComment> getAllComment(int productid) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<ProductComment> query = session.createQuery("select p from ProductComment p where p.product.productId =: productid", ProductComment.class);
        query.setParameter("productid",productid);
        return query.getResultList();
    }

}
