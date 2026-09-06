package vn.iotstar.util;
 
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
 
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
 
public class ValidationUtil {
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
 
    // Validate cả object (dùng khi đã có đủ 1 entity, ví dụ Category, Product)
    public static <T> Map<String, String> validate(T object) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<T> v : violations) {
            errors.put(v.getPropertyPath().toString(), v.getMessage());
        }
        return errors;
    }
 
    // Validate 1 giá trị đơn lẻ theo đúng ràng buộc đã khai báo trên field của beanClass
    // Dùng khi chỉ có tham số String rời rạc (ví dụ trong RegisterController), chưa dựng thành object đầy đủ
    public static String validateField(Class<?> beanClass, String propertyName, Object value) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<?>> violations = validator.validateValue((Class) beanClass, propertyName, value);
        if (!violations.isEmpty()) {
            return violations.iterator().next().getMessage();
        }
        return null;
    }
}
 
