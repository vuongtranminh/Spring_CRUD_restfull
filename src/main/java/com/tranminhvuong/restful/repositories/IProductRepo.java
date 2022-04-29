package com.tranminhvuong.restful.repositories;

import com.tranminhvuong.restful.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepo extends JpaRepository<Product, Long>{

	List<Product> findByProductName(String productName);
	
	List<Product> findByIsDeleted(Integer isDeleted);
	
}
