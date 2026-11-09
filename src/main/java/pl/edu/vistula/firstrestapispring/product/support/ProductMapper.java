package pl.edu.vistula.firstrestapispring.product.support;

import org.springframework.stereotype.Component;
import pl.edu.vistula.firstrestapispring.product.api.request.ProductRequest;
import pl.edu.vistula.firstrestapispring.product.api.request.UpdateProductRequest;
import pl.edu.vistula.firstrestapispring.product.api.response.ProductResponse;
import pl.edu.vistula.firstrestapispring.product.domain.Product;

@Component
public class ProductMapper {

    public Product toProduct(ProductRequest reqData) {
        return new Product(reqData.getName());
    }

    public Product toProduct(Product currentItem, UpdateProductRequest updateData) {
        currentItem.setName(updateData.getName());
        return currentItem;
    }

    public ProductResponse toProductResponse(Product entityObj) {
        return new ProductResponse(entityObj.getId(), entityObj.getName());
    }
}
