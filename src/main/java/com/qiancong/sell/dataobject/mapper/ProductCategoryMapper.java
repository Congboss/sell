package com.qiancong.sell.dataobject.mapper;

import com.qiancong.sell.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.Map;

/**
 * @program: sell
 * @description:
 * @author: Cong.Qian
 * @create: 2018-06-18 18:26
 **/
public interface ProductCategoryMapper {
    @Insert("insert into product_category(category_name,category_type) values (#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=Integer})")
int insertByMap(Map<String,Object> map);

    @Insert("insert into product_category(category_name,category_type) values (#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=Integer})")
    int insertByObject(ProductCategory productCategory);

@Select("select * from product_category where category_type=#{categoryType}")
@Results({
        @Result(column = "category_type",property = "categoryType"),
        @Result(column = "category_name",property = "categoryName"),
        @Result(column = "category_id",property = "categoryId"),

})
   ProductCategory  findByCategoryType(Integer categoryType);

@Update("update product_category set category_name=#{categoryName} where category_type=#{categoryType}")
    int updateCategoryType(@Param("categoryName") String categoryName,@Param("categoryType")Integer categoryType);

    @Update("update product_category set category_name=#{categoryName} where category_type=#{categoryType}")
    int updateByObject(ProductCategory productCategory);

    @Delete("delete from product_category where cayegory_type=#{categoryType}")
    int deleteByCategoryType(Integer categoryType);


}
