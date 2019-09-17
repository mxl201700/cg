package cn.itcast.order.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Package: cn.itcast.order.config
 * Author: Mxl
 * Date: Created in 2019/9/3 8:48
 **/
@Component
public class MyFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes!=null){
            //获取请求
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();

            if (headerNames!=null){
                //获取请求对象中的所有头信息
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();//头的名字
                    String header = request.getHeader(name);

                    System.out.println("name"+name+"header"+header);
                    requestTemplate.header(name, header);

                }

            }
        }
    }
}
