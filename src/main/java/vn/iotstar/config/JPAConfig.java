package vn.iotstar.config;
 
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
 
public class JPAConfig {
 
    // Chỉ tạo 1 lần duy nhất, dùng chung cho toàn ứng dụng (tốn tài nguyên nếu tạo mới mỗi lần)
    private static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("jpa-hibernate-sqlserver");
 
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
 
