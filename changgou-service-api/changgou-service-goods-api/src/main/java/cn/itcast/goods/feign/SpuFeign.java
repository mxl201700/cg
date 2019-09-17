package cn.itcast.goods.feign;

import cn.itcast.goods.pojo.Spu;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Package: cn.itcast.goods.feign
 * Author: Mxl
 * Date: Created in 2019/8/28 21:44
 **/
@FeignClient(value="goods")
@RequestMapping("/spu")
public interface SpuFeign {

    /***
     * 根据SpuID查询Spu信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Spu> findById(@PathVariable(name = "id") Long id);
}
