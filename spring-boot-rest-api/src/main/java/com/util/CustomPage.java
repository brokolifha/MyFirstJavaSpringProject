package com.util;

import com.yardimcibaris.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
@Setter
@Getter
public class CustomPage<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private Sort sort;
    private int totalPages;
    private int totalElements;


    public CustomPage(Page page, List<T> list) {
        this.content=list;
        this.pageNumber=page.getNumber();
        this.pageSize=page.getSize();
        this.totalPages=page.getTotalPages();
        this.totalElements=page.getTotalPages();
        this.sort=page.getSort();




    }


}
