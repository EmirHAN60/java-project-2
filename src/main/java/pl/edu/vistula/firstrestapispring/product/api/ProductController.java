package pl.edu.vistula.firstrestapispring.product.api;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.vistula.firstrestapispring.product.api.request.ProductRequest;
import pl.edu.vistula.firstrestapispring.product.api.request.UpdateProductRequest;
import pl.edu.vistula.firstrestapispring.product.api.response.ProductResponse;
import pl.edu.vistula.firstrestapispring.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService myProdService;

    public ProductController(ProductService myProdService) {
        this.myProdService = myProdService;
    }

    @PostMapping
    @Operation(summary = "Create product")
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest productRequest) {
        ProductResponse resObj = myProdService.createNewProduct(productRequest);
        return new ResponseEntity<>(resObj, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find product by id")
    public ResponseEntity<ProductResponse> find(@PathVariable Long id) {
        ProductResponse resObj = myProdService.fetchById(id);
        return ResponseEntity.status(HttpStatus.OK).body(resObj);
    }

    @GetMapping
    @Operation(summary = "Find all products")
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<ProductResponse> resList = myProdService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(resList);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update product")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody UpdateProductRequest updateProductRequest) {
        ProductResponse resObj = myProdService.updateExistingProduct(id, updateProductRequest);
        return ResponseEntity.status(HttpStatus.OK).body(resObj);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete item")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        myProdService.removeProductById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
