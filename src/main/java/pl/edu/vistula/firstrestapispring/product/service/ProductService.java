package pl.edu.vistula.firstrestapispring.product.service;

import org.springframework.stereotype.Service;
import pl.edu.vistula.firstrestapispring.product.api.request.ProductRequest;
import pl.edu.vistula.firstrestapispring.product.api.request.UpdateProductRequest;
import pl.edu.vistula.firstrestapispring.product.api.response.ProductResponse;
import pl.edu.vistula.firstrestapispring.product.domain.Product;
import pl.edu.vistula.firstrestapispring.product.repository.ProductRepository;
import pl.edu.vistula.firstrestapispring.product.support.ProductExceptionSupplier;
import pl.edu.vistula.firstrestapispring.product.support.ProductMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository mainRepo;
    private final ProductMapper customMapper;

    public ProductService(ProductRepository mainRepo, ProductMapper customMapper) {
        this.mainRepo = mainRepo;
        this.customMapper = customMapper;
    }

    public ProductResponse createNewProduct(ProductRequest inputReq) {
        Product newEntity = customMapper.toProduct(inputReq);
        Product savedEntity = mainRepo.save(newEntity);
        return customMapper.toProductResponse(savedEntity);
    }

    public ProductResponse fetchById(Long productId) {
        Product foundItem = mainRepo.findById(productId)
                .orElseThrow(ProductExceptionSupplier.productNotFound(productId));
        return customMapper.toProductResponse(foundItem);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> allEntities = mainRepo.findAll();
        List<ProductResponse> responseList = new ArrayList<>();
        for (Product item : allEntities) {
            responseList.add(customMapper.toProductResponse(item));
        }
        return responseList;
    }

    public ProductResponse updateExistingProduct(Long productId, UpdateProductRequest updateReq) {
        Product targetProduct = mainRepo.findById(productId)
                .orElseThrow(ProductExceptionSupplier.productNotFound(productId));
        mainRepo.save(customMapper.toProduct(targetProduct, updateReq));
        return customMapper.toProductResponse(targetProduct);
    }

    public void removeProductById(Long productId) {
        Product targetProduct = mainRepo.findById(productId)
                .orElseThrow(ProductExceptionSupplier.productNotFound(productId));
        mainRepo.deleteById(targetProduct.getId());
    }
}
