package cn.itcast.pay.controller;

import cn.itcast.pay.service.WeixinPayService;
import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPayUtil;
import entity.Result;
import entity.StatusCode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Package: cn.itcast.pay
 * Author: Mxl
 * Date: Created in 2019/9/6 9:34
 **/
@RestController
@RequestMapping("/weixin/pay")
@CrossOrigin
public class WeixinPayController {

    @Autowired
    private WeixinPayService weixinPayService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment env;

    /**
     * 创建二维码连接地址返回给前端 生成二维码图片
     * @param out_trade_no
     * @param total_fee
     * @return
     */
    @RequestMapping("/create/native")
    public Result<Map> createNative(String out_trade_no, String total_fee){

        Map<String, String> resultMap = weixinPayService.createNative(out_trade_no,total_fee);
        return new Result<>(true, StatusCode.OK, "二维码生成成功",resultMap);
    }

    @GetMapping("/status/query")
    public Result queryStatus(String out_trade_no){
        Map<String,String> resultMap = weixinPayService.queryStatus(out_trade_no);
        return new Result(true, StatusCode.OK, "查询订单状态成功",resultMap);
    }


    /**
     * 接收 微信支付通知的结果  结果(以流的形式传递过来)
     */
    @RequestMapping("/notify/url")
    public String notifyUrl(HttpServletRequest request){


        try {
            //1.获取流信息
            ServletInputStream inputStream = request.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                bos.write(bytes,0,len);
            }
            inputStream.close();
            bos.close();
            //2.转换成XML字符串

            byte[] bytes1 = bos.toByteArray();
            //微信支付系统传递过来的XML的字符串
            String resultStrXML = new String(bytes1,"utf-8");
            //3.转成MAP
            Map<String, String> map = WXPayUtil.xmlToMap(resultStrXML);
            System.out.println(resultStrXML);
            //4.发送消息给Rabbitmq  .........
            String data = JSON.toJSONString(map);
            rabbitTemplate.convertAndSend(env.getProperty("mq.pay.exchange.order"),env.getProperty("mq.pay.routing.key"),data);

            //5.返回微信的接收请况(XML的字符串)

            Map<String, String> resultMap = new HashMap<>();
            resultMap.put("return_code", "SUCCESS");
            resultMap.put("return_msg", "OK");
            return WXPayUtil.mapToXml(resultMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
