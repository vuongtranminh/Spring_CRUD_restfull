package com.tranminhvuong.restful.entities;

import com.tranminhvuong.restful.common.Slug;
import com.tranminhvuong.restful.validator.DescriptionAnnotation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

// POJO Plan Old Java Object
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tbl_products")
public class Product extends BaseEntity {

    @NotEmpty(message = "Product name must not be empty")
    @Column(name = "product_name", nullable = false, unique = true)
    private String productName;

    @PositiveOrZero(message = "Price must be positive or zero")
    @Column(name = "price", nullable = false)
    private Float price;

    @NotEmpty(message = "Avatar must not be empty")
    @Column(name = "avatar", nullable = false)
    private String avatar;

    @NotEmpty(message = "Cover must not be empty")
    @Column(name = "cover", nullable = false)
    private String cover;

    @DescriptionAnnotation
    @Lob
    @Column(name = "description", columnDefinition = "LONGTEXT", nullable = true)
    private String description;

    @Lob
    @Column(name = "features", columnDefinition = "LONGTEXT", nullable = true)
    private String features;

    @Column(name = "rating", nullable = true)
    private Float rating;

    @Column(name = "slug", nullable = false)
    private String slug;

    public Product(String productName, Float price, String avatar, String cover, String description, String features,
                   Float rating) {
        super();
        this.productName = productName;
        this.price = price;
        this.avatar = avatar;
        this.cover = cover;
        this.description = description;
        this.features = features;
        this.rating = rating;
        this.slug = Slug.setSlugify(productName);
    }
}
