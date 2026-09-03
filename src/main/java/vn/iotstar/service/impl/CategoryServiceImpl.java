package vn.iotstar.service.impl;
import java.io.File;
import java.util.List;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.model.Category;
import vn.iotstar.service.CategoryService;
import vn.iotstar.util.Constant;
public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public void insert(Category category) {
        categoryDao.insert(category);
    }
    @Override
    public void edit(Category newCategory) {
        Category oldCategory = categoryDao.get(newCategory.getId());
        oldCategory.setName(newCategory.getName());
        oldCategory.setStatus(newCategory.getStatus()); 
        if (newCategory.getIcon() != null) {
            String oldIcon = oldCategory.getIcon();
            if (oldIcon != null) {
                File file = new File(Constant.DIR + "/" + oldIcon);
                if (file.exists()) {
                    file.delete();
                }
            }
            oldCategory.setIcon(newCategory.getIcon());
        }
        categoryDao.edit(oldCategory);
    }
    @Override
    public void delete(int id) {
        categoryDao.delete(id);
    }
    @Override
    public Category get(int id) {
        return categoryDao.get(id);
    }
    @Override
    public Category get(String name) {
        return categoryDao.get(name);
    }
    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }
    @Override
    public List<Category> search(String catename) {
        return categoryDao.search(catename);
    }
 
    @Override
    public List<Category> getAll(int page, int pagesize) {
        return categoryDao.getAll(page, pagesize);
    }
    @Override
    public int count() {
        return categoryDao.count();
    }
}
 
