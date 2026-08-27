package vn.iotstar.config;
 
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iotstar.model.Category;
import vn.iotstar.model.Video;
 
public class Test {
    public static void main(String[] args) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
 
        Category cate = new Category();
        cate.setName("Test JPA - Tablet");
        cate.setIcon("tablet.jpg");
        cate.setStatus(1);
 
        Video video = new Video();
        video.setVideoId("v_test_01");
        video.setTitle("Video test JPA");
        video.setCategory(cate);
 
        try {
            trans.begin();
            enma.persist(cate);
            enma.persist(video);
            trans.commit();
            System.out.println("Test JPA thành công! Category id vừa tạo: " + cate.getId());
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }
}
 
