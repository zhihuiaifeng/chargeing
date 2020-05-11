package com.chargeingpile.netty.chargeingpilenetty.netty.IOT;
import com.chargeingpile.netty.chargeingpilenetty.constans.DefaultConstans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.InetSocketAddress;


@SpringBootApplication
public class IoTNettyPlatFormApplication {
    private static final Logger log = LoggerFactory.getLogger(IoTNettyPlatFormApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(IoTNettyPlatFormApplication.class, args);
        run();
    }

    private static NettyServer nettyServer = new NettyServer();

    private static void run(){
        Thread thread = new Thread(new Runnable() {


            @Override
            public void run() {
                InetSocketAddress address = new InetSocketAddress(DefaultConstans.SOKET_IP,DefaultConstans.SOKET_PORT);
                nettyServer.run(address);
            }
        });
        thread.start();
    }
}
