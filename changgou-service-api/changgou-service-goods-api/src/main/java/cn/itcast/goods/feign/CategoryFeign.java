package cn.itcast.goods.feign;

import cn.itcast.goods.pojo.Category;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Package: cn.itcast.goods.feign
 * Author: Mxl
 * Date: Created in 2019/8/28 21:34
 **/

@FeignClient(value="goods")
@RequestMapping("/category")
public interface CategoryFeign {

    /**
     * 获取分类的对象信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Category> findById(@PathVariable(name = "id") Integer id);
}
