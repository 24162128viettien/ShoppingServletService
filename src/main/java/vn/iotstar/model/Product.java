package vn.iotstar.model;
 
import java.io.Serializable;
import java.sql.Timestamp;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
 
@Entity
@Table(name = "products")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
@NamedQuery(name = "Product.findNewest", query = "SELECT p FROM Product p ORDER BY p.createdDate DESC")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;
 
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Column(name = "product_name", columnDefinition = "NVARCHAR(200) NOT NULL")
    private String name;
 
    @Positive(message = "Giá phải lớn hơn 0")
    @Column(name = "price")
    private double price;
 
    @Column(name = "description", columnDefinition = "NVARCHAR(MAX) NULL")
    private String description;
 
    @Column(name = "image", columnDefinition = "NVARCHAR(255) NULL")
    private String image;
 
    @Min(value = 0, message = "Số lượng không được âm")
    @Column(name = "quantity")
    private int quantity;
 
    @Column(name = "created_date")
    private Timestamp createdDate;
 
    // Quan hệ nhiều-1 với Category (khóa ngoại cate_id)
    @ManyToOne
    @JoinColumn(name = "cate_id")
    private Category category;
 
    public Product() {
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
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public String getImage() {
        return image;
    }
 
    public void setImage(String image) {
        this.image = image;
    }
 
    public int getQuantity() {
        return quantity;
    }
 
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
 
    public Timestamp getCreatedDate() {
        return createdDate;
    }
 
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
 
    public Category getCategory() {
        return category;
    }
 
    public void setCategory(Category category) {
        this.category = category;
    }
}
 
