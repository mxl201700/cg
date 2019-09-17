package cn.itcast.order.listener;

import cn.itcast.order.service.OrderService;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Package: cn.itcast.order.listener
 * Author: Mxl
 * Date: Created in 2019/9/7 11:18
 **/
@Component
@RabbitListener(queues = "${mq.pay.queue.order}")
public class OrderPayMessageListener {
    @Autowired
    private OrderService orderService;

    @RabbitHandler
    public void handlerData(String msg) {
    //1.接收消息(有订单的ID  有transaction_id )

        Map<String,String> map = JSON.parseObject(msg,Map.class);

        System.out.println(666);
        //2.更新对应的订单的状态
        if (map!=null){
            //参照微信支付文档
            if (map.get("return_code").equalsIgnoreCase("success")){

                orderService.updateStatus(map.get("out_trade_no"),map.get("transaction_id"));
            }else {
                //删除订单 支付失败.....
            }

        }
    }
}
