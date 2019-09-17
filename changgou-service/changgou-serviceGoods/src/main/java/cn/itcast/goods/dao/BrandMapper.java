package cn.itcast.goods.dao;
import cn.itcast.goods.pojo.Brand;
import cn.itcast.goods.pojo.Category;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Brand的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface BrandMapper extends Mapper<Brand> {

    @Select("select tb.* from tb_brand tb,tb_category_brand tcb where tb.id=tcb.brand_id and tcb.category_id=#{categoryid}")
    List<Brand> findBrandByCategory(Integer categoryid);
}