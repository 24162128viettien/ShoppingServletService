package vn.iotstar.service;
 
import java.util.List;
import vn.iotstar.model.Product;
 
public interface ProductService {
    void insert(Product product);
    void edit(Product product);
    void delete(int id);
    Product get(int id);
    List<Product> getAll();
    List<Product> getAll(int page, int pagesize);
    int count();
    List<Product> getNewest(int limit);
}