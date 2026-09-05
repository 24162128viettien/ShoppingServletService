package vn.iotstar.service.impl;
 
import java.io.File;
import java.util.List;
 
import vn.iotstar.dao.ProductDao;
import vn.iotstar.dao.impl.ProductDaoImpl;
import vn.iotstar.model.Product;
import vn.iotstar.service.ProductService;
import vn.iotstar.util.Constant;
 
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();
 
    @Override
    public void insert(Product product) {
        productDao.insert(product);
    }
 
    @Override
    public void edit(Product newProduct) {
        Product oldProduct = productDao.get(newProduct.getId());
        if (oldProduct == null) {
            return;
        }
        oldProduct.setName(newProduct.getName());
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setDescription(newProduct.getDescription());
        oldProduct.setQuantity(newProduct.getQuantity());
        oldProduct.setCategory(newProduct.getCategory());
 
        if (newProduct.getImage() != null && !newProduct.getImage().equals(oldProduct.getImage())) {
            String oldImage = oldProduct.getImage();
            if (oldImage != null) {
                File file = new File(Constant.DIR + "/" + oldImage);
                if (file.exists()) {
                    file.delete();
                }
            }
            oldProduct.setImage(newProduct.getImage());
        }
 
        productDao.edit(oldProduct);
    }
 
    @Override
    public void delete(int id) {
        productDao.delete(id);
    }
 
    @Override
    public Product get(int id) {
        return productDao.get(id);
    }
 
    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }
 
    @Override
    public List<Product> getAll(int page, int pagesize) {
        return productDao.getAll(page, pagesize);
    }
 
    @Override
    public int count() {
        return productDao.count();
    }
 
    @Override
    public List<Product> getNewest(int limit) {
        return productDao.getNewest(limit);
    }
}