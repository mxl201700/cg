package cn.itcast.pay.service.impl;

import cn.itcast.pay.service.WeixinPayService;
import com.github.wxpay.sdk.WXPayUtil;
import entity.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Package: cn.itcast.pay.service.impl
 * Author: Mxl
 * Date: Created in 2019/9/6 9:49
 **/
@Service
public class WeixinPayServiceImpl implements WeixinPayService {

    @Value("${weixin.appid}")
    private String appid;

    @Value("${weixin.partner}")
    private String partner;

    @Value("${weixin.partnerkey}")
    private String partnerkey;

    @Value("${weixin.notifyurl}")
    private String notifyurl;

    @Override
    public Map<String, String> createNative(String out_trade_no, String total_fee) {


        try {
            //1.创建参数对象(map) 用于组合参数
            Map<String,String> paramMap = new HashMap<>();

            //2.设置参数值(根据文档来写)
            paramMap.put("appid",appid);
            paramMap.put("mch_id",partner);
            paramMap.put("nonce_str",WXPayUtil.generateNonceStr());
            paramMap.put("body","畅购");
            paramMap.put("out_trade_no",out_trade_no);
            paramMap.put("total_fee",total_fee);
            paramMap.put("spbill_create_ip","127.0.0.1");
            paramMap.put("notify_url",notifyurl);
            paramMap.put("trade_type","NATIVE");

            //3.转成XML 字符串 自动签名
            String xmlParam = WXPayUtil.generateSignedXml(paramMap, partnerkey);
            //4.创建httpclient对象(模拟浏览器)
            HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            //5.设置https协议
            httpClient.setHttps(true);
            //6.设置请求体
            httpClient.setXmlParam(xmlParam);
            //7.发送请求
            httpClient.post();

            //8.获取微信支付系统返回的响应结果(XML格式的字符串)
            String content = httpClient.getContent();
            System.out.println(content);
            //9.转成Map  返回

            Map<String, String> allmap = WXPayUtil.xmlToMap(content);
//            Map<String, String> resultMap = new HashMap<>();
//
//            resultMap.put("out_trade_no",out_trade_no);
//            resultMap.put("total_fee",total_fee);
//            resultMap.put("code_url",allmap.get("code_url"));

            return allmap;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Map<String, String> queryStatus(String out_trade_no) {


        try {
            //1.封装参数

            Map<String,String> param = new HashMap<>();

            param.put("appid",appid);                            //应用ID
            param.put("mch_id",partner);                         //商户号
            param.put("out_trade_no",out_trade_no);              //商户订单编号
            param.put("nonce_str",WXPayUtil.generateNonceStr()); //随机字符
            //2、将参数转成xml字符，并携带签名

            String paramXml = WXPayUtil.generateSignedXml(param, partnerkey);
            //3、发送请求
            HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            httpClient.setHttps(true);
            httpClient.setXmlParam(paramXml);
            httpClient.post();

            //4、获取返回值，并将返回值转成Map
            String content = httpClient.getContent();

            return WXPayUtil.xmlToMap(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
