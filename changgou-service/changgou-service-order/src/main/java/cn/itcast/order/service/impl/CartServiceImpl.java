package cn.itcast.order.service.impl;

import cn.itcast.goods.feign.SkuFeign;
import cn.itcast.goods.feign.SpuFeign;
import cn.itcast.goods.pojo.Sku;
import cn.itcast.goods.pojo.Spu;
import cn.itcast.order.pojo.OrderItem;
import cn.itcast.order.service.CartService;

import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: cn.itcast.user.service.impl
 * Author: Mxl
 * Date: Created in 2019/9/1 16:26
 **/
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SpuFeign spuFeign;

    @Override
    public void add(Integer num, Long id, String username) {

        if(num<=0){
            //删除掉原来的商品
            redisTemplate.boundHashOps("Cart_" + username).delete(id);
            return;
        }

        //查询SKU
        Result<Sku> skuResult = skuFeign.findById(id);
        Sku data = skuResult.getData();

        if (data!=null){
            //2.根据sku的数据对象 获取 该SKU对应的SPU的数据
            Long spuId = data.getSpuId();
            Result<Spu> spuResult = spuFeign.findById(spuId);
            Spu spu = spuResult.getData();

            //3.将数据存储到 购物车对象(order_item)中
            OrderItem orderItem = new OrderItem();

            orderItem.setCategoryId1(spu.getCategory1Id());
            orderItem.setCategoryId2(spu.getCategory2Id());
            orderItem.setCategoryId3(spu.getCategory3Id());
            orderItem.setSpuId(spu.getId());
            orderItem.setSkuId(id);
            orderItem.setName(data.getName());//商品的名称  sku的名称
            orderItem.setPrice(data.getPrice());//sku的单价
            orderItem.setNum(num);//购买的数量
            orderItem.setMoney(orderItem.getNum() * orderItem.getPrice());//单价* 数量
            orderItem.setPayMoney(orderItem.getNum() * orderItem.getPrice());//单价* 数量
            orderItem.setImage(data.getImage());//商品的图片dizhi
            //4.数据添加到redis中  key:用户名 field:sku的ID  value:购物车数据(order_item)

            redisTemplate.boundHashOps("Cart_" + username).put(id,orderItem);// hset key field value   hget key field
        }
    }

    @Override
    public List<OrderItem> list(String username) {

        //查询所有购物车数据
        List<OrderItem> orderItems = redisTemplate.boundHashOps("Cart_"+username).values();
        return orderItems;
    }
}
