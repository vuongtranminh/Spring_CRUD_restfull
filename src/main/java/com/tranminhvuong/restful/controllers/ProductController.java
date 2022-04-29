package com.tranminhvuong.restful.controllers;

import com.tranminhvuong.restful.entities.Product;
import com.tranminhvuong.restful.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {

    //	DI: Dependence Injection
    @Autowired // được tạo ra 1 lần ngay khi ứng dụng được chạy. gần giống singleton
    private IProductService iProductService;

    /**
     * authority có tiền tố ROLE_. gọi ADMIN cũng giống như một authority được gọi ROLE_ADMIN.
     */
    //	[GET] /api/v1/products/getall
    @GetMapping("/getall")
    public ResponseEntity<?> getAllProducts() {
        return iProductService.getAllProducts();
    }

    //	[GET] /api/v1/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductByID(@PathVariable Long id) {
        return iProductService.getProductByID(id);
    }

    //	[POST] /api/v1/products/insert
    @PostMapping("/insert")
    public ResponseEntity<?> insertProduct(@Valid @RequestBody Product newProduct,
                                           @RequestParam("avatar") MultipartFile avatar,
                                           @RequestParam("cover") MultipartFile cover,
                                           @RequestParam("images") MultipartFile images) {
        return iProductService.insertProduct(newProduct);
    }

    //	[PUT] /api/v1/products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Product newProduct, @PathVariable Long id) {
        return iProductService.updateProduct(newProduct, id);
    }

    //	[DELETE] /api/v1/products/{id}/destroy
    @DeleteMapping("/{id}/destroy")
    public ResponseEntity<?> destroyProduct(@PathVariable Long id) {
        return iProductService.destroyProduct(id);
    }

//	soft-deletion

    //	[GET] /api/v1/products
    @GetMapping("")
    public ResponseEntity<?> findAllProducts() {
        return iProductService.findAllProducts();
    }

    //	[GET] /api/v1/products/deleted
    @GetMapping("/deleted")
    public ResponseEntity<?> findAllProductsDeleted() {
        return iProductService.findAllProductsDeleted();
    }

    //	[PUT] /api/v1/products/delete/{id}
    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return iProductService.deleteProduct(id);
    }

    //	[PUT] /api/v1/product/restore/{id}
    @PutMapping("/restore/{id}")
    public ResponseEntity<?> restoreProduct(@PathVariable Long id) {
        return iProductService.restoreProduct(id);
    }
}
