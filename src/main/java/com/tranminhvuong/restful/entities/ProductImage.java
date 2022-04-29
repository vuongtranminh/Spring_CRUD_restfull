package com.tranminhvuong.restful.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductImage extends BaseEntity {

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

}
