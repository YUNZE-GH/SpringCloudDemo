package com.gh.consumer;

import com.gh.common.SDK;
import com.gh.consumer.websocket.WebSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;

import javax.websocket.Session;

//@EnableDiscoveryClient //开启发现服务功能
@EnableFeignClients(basePackages = "com.gh.consumer.feign")
@ComponentScan(basePackages = "com.gh.consumer.*")
@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Scheduled(fixedDelay = 1000 * 5)
	public void jobInfo(Session session){
		System.err.println("===========> 定时信息");
		WebSocket webSocket = WebSocket.getClients().get("张三");
//		webSocket.session.getAsyncRemote().sendText(SDK.getDateUtils().getDateTime() + " 你好！");
	}
}
