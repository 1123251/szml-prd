package com.yt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class NacosConfig {

    @Value("${config}")
    private String config;

    /**
     * 获取配置内容
     *
     * @return
     */
    @GetMapping( "/get")
    public String getConfig() {
        return config;
    }

}
