package com.example.nacos.nacosdemo;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @from: https://www.cnblogs.com/java333/
 * @desc: TODO
 * @author: jar luo
 * @date: 2019/8/4 12:52
 */
@NacosPropertySource(dataId = "example",groupId = "DEFAULT_GROUP",autoRefreshed=true)
@RestController
public class NacosConfigController {
    /**
     * 当前的这个info属性，会去nacos-server上找到对应的info这个属性
     * value值要考虑高可用性，如果服务断掉，或者超时，要给一个默认值hello Nacos
     *
     */
    @NacosValue(value = "${info:hello Nacos}",autoRefreshed = true)
    //application.properties --对比思考
    //@Value("${nacos.config.server-addr}")
    private String info;

    @GetMapping("/get")
    public String get(){
        return info;
    }
}
