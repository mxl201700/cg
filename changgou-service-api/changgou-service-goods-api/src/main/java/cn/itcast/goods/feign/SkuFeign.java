package cn.itcast.goods.feign;

import cn.itcast.goods.pojo.Sku;
import cn.itcast.order.pojo.OrderItem;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Package: cn.itcast.goods.feign
 * Author: Mxl
 * Date: Created in 2019/8/24 20:24
 **/
@FeignClient(name = "goods")
@RequestMapping(value = "/sku")
public interface SkuFeign {
    /***
     * 根据审核状态查询Sku
     * @param status
     * @return
     */
    @GetMapping("/status/{status}")
    public Result<List<Sku>> findByStatus(@PathVariable(value = "status") String status);

    /**
     * 根据条件搜索的SKU的列表
     * @param sku
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Sku>> findList(@RequestBody(required = false) Sku sku);

    @GetMapping("/{id}")
    public Result<Sku> findById(@PathVariable(name="id") Long id);

    @PostMapping(value = "/decr/count")
    public Result decrCount(@RequestBody OrderItem orderItem);
}
