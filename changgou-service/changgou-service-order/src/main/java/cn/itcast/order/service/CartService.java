package cn.itcast.order.service;

import cn.itcast.order.pojo.OrderItem;

import java.util.List;

/**
 * Package: cn.itcast.user.service
 * Author: Mxl
 * Date: Created in 2019/9/1 16:25
 **/
public interface CartService {

    void add(Integer num, Long id, String username);

    List<OrderItem> list(String username);

}
