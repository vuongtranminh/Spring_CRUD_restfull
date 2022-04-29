package com.tranminhvuong.restful.configurations;

import com.tranminhvuong.restful.entities.Product;
import com.tranminhvuong.restful.repositories.IProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * bên trong chứa các Bean method, các Bean này sẽ đc gọi ngay khi khởi chạy ứng dụng
 */
@Slf4j
@Configuration
public class Database {

    /**
     * CodeFirst. khởi chạy thêm vào DB
     * @param iProductRepo
     * @return CommandLineRunner
     */
    @Bean
    CommandLineRunner iniCommandLineRunner(IProductRepo iProductRepo) { // Code first
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

                Product productA = new Product("Product A", 34.5f, "abc", "abc", "Good job", "", 3.5f);
                Product productB = new Product("Product B", 35.1f, "abc", "abc", "Good", "", 3.5f);
                Product productC = new Product("Product C", 34.5f, "abc", "abc", "Good good", "", 3.5f);

                log.info("Insert data: " + iProductRepo.save(productA));
                log.info("Insert data: " + iProductRepo.save(productB));
                log.info("Insert data: " + iProductRepo.save(productC));

            }
        };
    }
}
