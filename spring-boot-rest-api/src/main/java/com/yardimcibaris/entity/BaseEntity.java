package com.yardimcibaris.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass//BaseEntity clasını Extend eden bütün classlarda bu alanlar kullanılabilmesi için kullanılan anotasyon
@Setter
@Getter
@ToString
public class BaseEntity implements Serializable {
    private Date createdDate;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
}
