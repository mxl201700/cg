package cn.itcast.search.dao;

import cn.itcast.search.pojo.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Package: cn.itcast.search.dao
 * Author: Mxl
 * Date: Created in 2019/8/24 20:22
 **/
public interface SkuEsMapper extends ElasticsearchRepository<SkuInfo,Long> {

}
