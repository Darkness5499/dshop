package vn.dshop.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.dshop.entity.Comment;
import vn.dshop.repository.CommentRepository;

import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
    private SessionFactory sessionFactory;
    @Autowired
    public CommentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Comment comment) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(comment);
    }

    @Override
    public List<Comment> getAllComment(int productid) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<Comment> query = session.createQuery("select p from Comment p where p.product.productId =: productid", Comment.class);
        query.setParameter("productid",productid);
        return query.getResultList();
    }

}
