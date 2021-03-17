package vn.dshop.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import vn.dshop.entity.Category;
import vn.dshop.repository.CategoryRepository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    private SessionFactory sessionFactory;
    public CategoryRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Category> getAllCategory() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Category> query = session.createQuery("select c from Category c");
        session.getTransaction().commit();
        return query.getResultList();


    }

    @Override
    public void delete(Category category) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(category);
    }

    @Override
    public void save(Category c) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(c);
    }

    @Override
    public Category getCategoryById(int id) {
        Session session = this.sessionFactory.getCurrentSession();

        return session.get(Category.class,id);
    }

    @Override
    public Category getCategoryByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Category where name=:name");
        query.setParameter("name",name);
        return (Category) query.uniqueResult();
    }
}
