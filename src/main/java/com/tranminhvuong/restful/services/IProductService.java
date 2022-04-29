package com.tranminhvuong.restful.services;

import com.tranminhvuong.restful.entities.Product;
import org.springframework.http.ResponseEntity;

public interface IProductService {

    ResponseEntity<?> getAllProducts();

    ResponseEntity<?> getProductByID(Long id);

    ResponseEntity<?> insertProduct(Product newProduct);

    ResponseEntity<?> updateProduct(Product newProduct, Long id);

    ResponseEntity<?> destroyProduct(Long id);

    ResponseEntity<?> deleteProduct(Long id);

    ResponseEntity<?> restoreProduct(Long id);

    ResponseEntity<?> findAllProducts();

    ResponseEntity<?> findAllProductsDeleted();

}
