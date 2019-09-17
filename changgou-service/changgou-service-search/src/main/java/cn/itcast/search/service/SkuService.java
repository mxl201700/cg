package cn.itcast.search.service;

import java.util.Map;

/**
 * Package: cn.itcast.search.service
 * Author: Mxl
 * Date: Created in 2019/8/24 20:20
 **/
public interface SkuService {

    /**
     * //1.调用 goods微服务的fegin 查询 符合条件的sku的数据
     //2.调用spring data elasticsearch的API 导入到ES中
     */
    void  importEs();


    /**
     *
     * @param searchMap
     * @return
     */
    Map search(Map<String,String> searchMap);

}
