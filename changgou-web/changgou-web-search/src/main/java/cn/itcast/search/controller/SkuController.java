package cn.itcast.search.controller;

import cn.itcast.search.feign.SkuFeign;
import cn.itcast.search.pojo.SkuInfo;
import entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Map;

/**
 * Package: cn.itcast.search.controller
 * Author: Mxl
 * Date: Created in 2019/8/27 21:51
 **/
@Controller
@RequestMapping(value = "/search")
public class SkuController {

    @Autowired
    private SkuFeign skuFeign;

    /**
     * 搜索
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/list")
    public String search(@RequestParam(required = false)  Map<String, String> searchMap, Model model){
        //调用changgou-service-search微服务
        Map resultMap = skuFeign.search(searchMap);
        model.addAttribute("result",resultMap);

        //设置搜索条件的回显
        model.addAttribute("searchMap",searchMap);
        //4.记住之前的URL
        //拼接url

        String url = url(searchMap);
        model.addAttribute("url",url);

        //创建一个分页的对象  可以获取当前页 和总个记录数和显示的页码(以当前页为中心的5个页码)
        Page<SkuInfo> infoPage = new Page<SkuInfo>(
        Long.valueOf(resultMap.get("total").toString()),
                Integer.valueOf(resultMap.get("pageNum").toString()),
                Integer.valueOf(resultMap.get("pageSize").toString())
        );

        model.addAttribute("page",infoPage);

        return "search";
    }

    private String url(Map<String,String> searchMap) {
        String url = "/search/list";

        if (searchMap!=null&&searchMap.size()>0){
            url+="?";

            for (Map.Entry<String, String> stringStringEntry : searchMap.entrySet()) {
                String key = stringStringEntry.getKey();// keywords / brand  / category
                String value = stringStringEntry.getValue();//华为  / 华为  / 笔记本

                if(key.equals("pageNum")){
                    continue;
                }
                url += key+"="+value+"&";
            }
            //去掉多余的&
            if (url.lastIndexOf("&")!=-1){
                url =  url.substring(0,url.lastIndexOf("&"));
            }
        }

        return url;
    }
}
