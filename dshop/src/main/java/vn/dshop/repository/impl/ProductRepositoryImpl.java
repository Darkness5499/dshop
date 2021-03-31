package vn.dshop.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import vn.dshop.entity.Product;
import vn.dshop.repository.ProductRepository;
import java.util.List;
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public ProductRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> getAllProducts(int position) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("select p from Product p ",Product.class);
        int pageSize = 20;
        query.setFirstResult(position);
        query.setMaxResults(pageSize);
        return query.getResultList();

    }

    @Override

    public void save(Product p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
    }

    @Override
    public void delete(Product p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(p);

    }

    @Override
    public void update(Product p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(p);
    }

    @Override
    public Product getProductById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Product) session.get(Product.class,id);
    }

    @Override

    public List<Product> getProductsByCategory(int categoryId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("select p from Product p where p.category.categoryId =:categoryId ",Product.class);
        query.setParameter("categoryId", categoryId);
        return query.getResultList();
    }

    @Override
    public List<Product> getProductsByName(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("select p from Product p where p.name =:name",Product.class);
        query.setParameter("name",name);
        return query.getResultList();
    }

    @Override
    public List<Product> getProductByCategoryName(String categoryName) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("select p from Product p where p.category.name =: categoryName",Product.class);
        query.setParameter("categoryName", categoryName);
        return query.getResultList();
    }

}
