package cn.itcast.order.controller;


import cn.itcast.order.pojo.OrderItem;
import cn.itcast.order.service.CartService;
import entity.Result;
import entity.StatusCode;
import entity.TokenDecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Package: cn.itcast.user.controller
 * Author: Mxl
 * Date: Created in 2019/9/1 16:20
 **/
@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private TokenDecode tokenDecode;

    @RequestMapping("/add")
    public Result add(Long id, Integer num) {

        //当前用户名
        //String username = "szitheima";

        String username = tokenDecode.getUserInfo().get("username");

        //将商品加入购物车
        cartService.add(num, id, username);

        return new Result(true, StatusCode.OK, "加入购物车成功");
    }

    @RequestMapping("/list")
    public Result<List<OrderItem>> list() {
        //用户名
        //String username="szitheima";
        String username = tokenDecode.getUserInfo().get("username");
        List<OrderItem> orderItems = cartService.list(username);
        return new Result(true, StatusCode.OK, "购物车列表查询成功！", orderItems);
    }

}
