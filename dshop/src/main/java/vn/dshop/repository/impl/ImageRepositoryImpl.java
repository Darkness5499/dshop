package vn.dshop.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.dshop.entity.Image;
import vn.dshop.repository.ImageRepository;

@Repository
public class ImageRepositoryImpl implements ImageRepository {
    private SessionFactory sessionFactory;
    @Autowired
    public ImageRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Image image) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(image);
    }

    @Override
    public void delele(Image image) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(image);
    }
}
