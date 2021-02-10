package com.gh.consumer.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.gh.common.toolsclass.ResultData;
import com.gh.consumer.config.SentinelBlockHandler;
import com.gh.consumer.feign.ProviderService;
import com.gh.consumer.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    //    @Autowired
    private RestTemplate restTemplate;

    //    @Autowired
    private final ProviderService service;

    private final TestService testService;

    ConsumerController(ProviderService service, RestTemplate restTemplate, TestService testService) {
        this.service = service;
        this.restTemplate = restTemplate;
        this.testService = testService;
    }

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/test")
    @SentinelResource(value = "test", blockHandlerClass = {ConsumerController.class}, blockHandler = "testBlockHandler")
    public String test(){
        return "接口测试";
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
    public ResultData one(@PathVariable("id") String id) {
        return service.one(id);
    }

    /**
     * sentinel流控
     * @return
     */
    @PostMapping(value = "/all")
    @SentinelResource(value = "all", blockHandlerClass = {SentinelBlockHandler.class}, blockHandler = "flowHandler")
    public ResultData all() {
        return service.all();
    }

    /**
     * sentinel熔断降级示例
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getError")
    public String getError() throws Exception {
        String temp = "";
        for (int i = 0; i < 10; i++) {
            Thread.sleep(100);
            temp += testService.getUserName(i);
        }
        return temp;
    }
}
