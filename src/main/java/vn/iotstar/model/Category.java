package vn.iotstar.model;
 
import java.io.Serializable;
import java.util.List;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "category")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cate_id")
    private int id;
 
    @Column(name = "cate_name", columnDefinition = "NVARCHAR(100) NOT NULL")
    private String name;
 
    @Column(name = "icons", columnDefinition = "NVARCHAR(255) NULL")
    private String icon;
 
    @Column(name = "status")
    private int status = 1; 
 
    @OneToMany(mappedBy = "category")
    private List<Video> videos;
 
    public Category() {
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getIcon() {
        return icon;
    }
 
    public void setIcon(String icon) {
        this.icon = icon;
    }
 
    public int getStatus() {
        return status;
    }
 
    public void setStatus(int status) {
        this.status = status;
    }
 
    public List<Video> getVideos() {
        return videos;
    }
 
    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
 
    public Video addVideo(Video video) {
        getVideos().add(video);
        video.setCategory(this);
        return video;
    }
 
    public Video removeVideo(Video video) {
        getVideos().remove(video);
        video.setCategory(null);
        return video;
    }
}
 
