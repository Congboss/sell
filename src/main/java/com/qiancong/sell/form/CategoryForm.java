package com.qiancong.sell.form;

import lombok.Data;

/**
 * @program: sell
 * @description:
 * @author: Cong.Qian
 * @create: 2018-06-17 18:29
 **/
@Data
public class CategoryForm {
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
