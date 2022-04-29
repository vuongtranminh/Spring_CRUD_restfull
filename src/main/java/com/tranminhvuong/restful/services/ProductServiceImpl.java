package com.tranminhvuong.restful.services;

import com.tranminhvuong.restful.common.Slug;
import com.tranminhvuong.restful.entities.Product;
import com.tranminhvuong.restful.exceptions.BadRequestException;
import com.tranminhvuong.restful.payload.ResponseObject;
import com.tranminhvuong.restful.repositories.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired // được tạo ra 1 lần ngay khi ứng dụng được chạy. gần giống singleton
    private IProductRepo iProductRepo;

    @Override
    public ResponseEntity<?> getAllProducts() {

        List<Product> products = iProductRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject(200, "Get all products successfully", products));

    }

    @Override
    public ResponseEntity<?> getProductByID(Long id) {

        Optional<Product> product = iProductRepo.findById(id);

        return product.isPresent() ?

                ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject(200, "Query product successfully", product)) :

                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject(404, "Cannot find product with id = " + id, ""));

    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<?> insertProduct(Product newProduct) {

        List<Product> products = iProductRepo.findByProductName(newProduct.getProductName().trim());

        if(products.size() > 0) {
            throw new BadRequestException("Product name already taken");
        } else {
            newProduct.setIsDeleted(0);
            newProduct.setSlug(Slug.setSlugify(newProduct.getProductName()));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseObject(201, "Insert product successfully", iProductRepo.save(newProduct)));
        }

    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<?> updateProduct(Product newProduct, Long id) {

        Product product = iProductRepo.findById(id).get();
        List<Product> products = iProductRepo.findByProductName(newProduct.getProductName().trim());
        if(products.size() > 0) {
            throw new BadRequestException("Product name already taken");
        } else {

            String productName = newProduct.getProductName();
            product.setProductName(productName);
            product.setPrice(newProduct.getPrice());
            product.setAvatar(newProduct.getAvatar());

            product.setCover(newProduct.getCover());
            product.setDescription(newProduct.getDescription());
            product.setFeatures(newProduct.getFeatures());
            product.setUpdatedAt(Calendar.getInstance().getTime());

            product.setSlug(Slug.setSlugify(productName));

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(200, "Update product successfully", iProductRepo.save(product)));
        }

    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<?> destroyProduct(Long id) {

        boolean isExistProduct = iProductRepo.existsById(id);
        if (isExistProduct) {
            iProductRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(200, "Destroy product successfully", ""));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(404, "Cannot find product with id = " + id, ""));
        }

    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<?> deleteProduct(Long id) {

        Product product = iProductRepo.findById(id)
                .map(pro -> {
                    pro.setIsDeleted(1);
                    pro.setDeletedAt(Calendar.getInstance().getTime());
                    return pro;
                })
                .orElseGet(() -> {
                    return null;
                });

        return product != null ?

                ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject(200, "Delete product successfully", iProductRepo.save(product))) :

                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject(404, "Cannot find product with id = " + id, ""));

    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<?> restoreProduct(Long id) {

        Product product = iProductRepo.findById(id)
                .map(pro -> {
                    pro.setIsDeleted(0);
                    pro.setDeletedAt(null);
                    return pro;
                })
                .orElseGet(() -> {
                    return null;
                });

        return product != null ?

                ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject(200, "Restore product successfully", iProductRepo.save(product))) :

                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject(404, "Cannot find product with id = " + id, ""));

    }

    @Override
    public ResponseEntity<?> findAllProducts() {

        List<Product> products = iProductRepo.findByIsDeleted(0);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject(200, "Get all products successfully", products));

    }

    @Override
    public ResponseEntity<?> findAllProductsDeleted() {

        List<Product> products = iProductRepo.findByIsDeleted(1);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject(200, "Get all products deleted successfully", products));

    }

}
