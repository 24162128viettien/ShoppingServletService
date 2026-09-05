package vn.iotstar.dao.impl;
 
import java.util.List;
 
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
 
import vn.iotstar.config.JPAConfig;
import vn.iotstar.dao.ProductDao;
import vn.iotstar.model.Product;
 
public class ProductDaoImpl implements ProductDao {
 
    @Override
    public void insert(Product product) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(product);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            enma.close();
        }
    }
 
    @Override
    public void edit(Product product) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(product);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            enma.close();
        }
    }
 
    @Override
    public void delete(int id) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            Product product = enma.find(Product.class, id);
            if (product != null) {
                enma.remove(product);
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            enma.close();
        }
    }
 
    @Override
    public Product get(int id) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            return enma.find(Product.class, id);
        } finally {
            enma.close();
        }
    }
 
    @Override
    public List<Product> getAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            TypedQuery<Product> query = enma.createNamedQuery("Product.findAll", Product.class);
            return query.getResultList();
        } finally {
            enma.close();
        }
    }
 
    @Override
    public List<Product> getAll(int page, int pagesize) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            TypedQuery<Product> query = enma.createNamedQuery("Product.findNewest", Product.class);
            query.setFirstResult(page * pagesize); 
            query.setMaxResults(pagesize);
            return query.getResultList();
        } finally {
            enma.close();
        }
    }
 
    @Override
    public int count() {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            Query query = enma.createQuery("SELECT count(p) FROM Product p");
            return ((Long) query.getSingleResult()).intValue();
        } finally {
            enma.close();
        }
    }
 
    @Override
    public List<Product> getNewest(int limit) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            TypedQuery<Product> query = enma.createNamedQuery("Product.findNewest", Product.class);
            query.setMaxResults(limit);
            return query.getResultList();
        } finally {
            enma.close();
        }
    }
}
 
