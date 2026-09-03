package vn.iotstar.dao.impl;
 
import java.sql.Timestamp;
import java.util.List;
 
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
 
import vn.iotstar.config.JPAConfig;
import vn.iotstar.dao.UserDao;
import vn.iotstar.model.User;
 
public class UserDaoImpl implements UserDao {
 
    @Override
    public User get(String username) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.username = :username";
            TypedQuery<User> query = enma.createQuery(jpql, User.class);
            query.setParameter("username", username);
            List<User> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            enma.close();
        }
    }
 
    @Override
    public User getById(int id) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            return enma.find(User.class, id);
        } finally {
            enma.close();
        }
    }
 
    @Override
    public User getByUsernameOrEmail(String input) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.username = :input OR u.email = :input";
            TypedQuery<User> query = enma.createQuery(jpql, User.class);
            query.setParameter("input", input);
            List<User> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            enma.close();
        }
    }
 
    @Override
    public void insert(User user) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(user);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            enma.close();
        }
    }
 
    @Override
    public void update(User user) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(user);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            enma.close();
        }
    }
 
    @Override
    public boolean checkExistUsername(String username) {
        return get(username) != null;
    }
 
    @Override
    public boolean checkExistEmail(String email) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.email = :email";
            TypedQuery<User> query = enma.createQuery(jpql, User.class);
            query.setParameter("email", email);
            return !query.getResultList().isEmpty();
        } finally {
            enma.close();
        }
    }
 
    @Override
    public void saveOtp(String username, String otp, Timestamp expiry) {
        User user = get(username);
        if (user != null) {
            user.setOtpCode(otp);
            user.setOtpExpiry(expiry);
            update(user);
        }
    }
 
    @Override
    public void activateAccount(String username) {
        User user = get(username);
        if (user != null) {
            user.setActive(1);
            user.setOtpCode(null);
            user.setOtpExpiry(null);
            update(user);
        }
    }
 
    @Override
    public void updatePassword(String username, String newPassword) {
        User user = get(username);
        if (user != null) {
            user.setPassword(newPassword);
            user.setOtpCode(null);
            user.setOtpExpiry(null);
            update(user);
        }
    }
 
    @Override
    public void clearOtp(String username) {
        User user = get(username);
        if (user != null) {
            user.setOtpCode(null);
            user.setOtpExpiry(null);
            update(user);
        }
    }
}
 
