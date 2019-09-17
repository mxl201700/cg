package cn.itcast.pay.service;

import java.util.Map;

/**
 * Package: cn.itcast.pay.service
 * Author: Mxl
 * Date: Created in 2019/9/6 9:49
 **/
public interface WeixinPayService {
    Map<String,String> createNative(String out_trade_no, String total_fee);

    Map<String,String> queryStatus(String out_trade_no);
}
