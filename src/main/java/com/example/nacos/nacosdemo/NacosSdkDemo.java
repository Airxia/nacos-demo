package com.example.nacos.nacosdemo;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import javax.security.auth.login.Configuration;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @from: https://www.cnblogs.com/java333/
 * @desc: TODO
 * @author: jar luo
 * @date: 2019/8/4 13:11
 */
public class NacosSdkDemo {
    public static void main(String[] args) {
        //连接到目标服务器地址
        //指定dataId和groupId
        String serverAddr = "localhost:8848";
        String dataId = "example";
        String groupId = "DEFAULT_GROUP";
        Properties properties = new Properties();
        properties.put("serverAddr",serverAddr);

        try {
            ConfigService configService = NacosFactory.createConfigService(properties);
            String  content = configService.getConfig(dataId,groupId,3000);
            System.out.println(content);
            //改变监听
            configService.addListener(dataId, groupId, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String s) {
                    System.out.println("configInfo:"+s);
                }
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }
}
