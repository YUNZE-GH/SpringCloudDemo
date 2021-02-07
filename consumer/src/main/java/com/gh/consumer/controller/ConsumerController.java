package com.gh.consumer.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.gh.common.toolsclass.ResultData;
import com.gh.consumer.config.SentinelBlockHandler;
import com.gh.consumer.feign.ProviderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/test")
    @SentinelResource(value = "test", blockHandlerClass = {ConsumerController.class}, blockHandler = "testBlockHandler")
    public String test(){
        return "9020测试接口";
    }

    public static String testBlockHandler (BlockException e) {
        return "被限流降级";
    }

    @RequestMapping("/query")
    public String getEmpInfo() {
//        String info = restTemplate.getForObject("http://provider/providerAPI", String.class);
        String info = service.getEmpInfo();
        return "消费者服务获取 " + info;
    }

    @PostMapping(value = "one/{id}")
    @SentinelResource(value = "one", fallbackClass = {SentinelBlockHandler.class}, fallback = "fuseHandler")
    public ResultData one(@PathVariable("id") String id) {
        if (Integer.parseInt(id) > 10) {
            throw new RuntimeException("凉凉！");
        }
        return service.one(id);
    }

    @PostMapping(value = "/all")
    @SentinelResource(value = "all", blockHandlerClass = {SentinelBlockHandler.class}, blockHandler = "flowHandler")
    public ResultData all() {
        return service.all();
    }
}
