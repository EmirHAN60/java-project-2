package pl.edu.vistula.firstrestapispring.product.support.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long targetId) {
        super(String.format("Product with %d not found", targetId));
    }
}
