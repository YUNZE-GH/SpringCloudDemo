package com.gh.consumer.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.gh.common.toolsclass.ResultData;
import com.gh.consumer.config.SentinelHandler;
import com.gh.consumer.feign.ProviderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

//    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
    private final ProviderService service;

    ConsumerController(ProviderService service, RestTemplate restTemplate) {
        this.service = service;
        this.restTemplate = restTemplate;
    }

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/query")
    public String getEmpInfo(){
//        String info = restTemplate.getForObject("http://provider/providerAPI", String.class);
        String info = service.getEmpInfo();
        return "消费者服务获取 " + info;
    }

    @PostMapping(value = "one/{id}")
    @SentinelResource(value = "/one", fallbackClass = SentinelHandler.class, fallback = "fallbackHandler")
    public ResultData one(@PathVariable("id") String id) {
        return service.one(id);
    }

    @PostMapping(value = "/all")
    @SentinelResource(value = "/all", blockHandlerClass = SentinelHandler.class, blockHandler = "blockHandler")
    public ResultData all() {
        return service.all();
    }

}
