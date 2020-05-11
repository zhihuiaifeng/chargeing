package com.chargeingpile.netty.chargeingpilenetty;

import com.chargeingpile.netty.chargeingpilenetty.constans.DefaultConstans;
import com.chargeingpile.netty.chargeingpilenetty.netty.server.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;


@SpringBootApplication
public class ChargeingPileNettyApplication implements CommandLineRunner {


    @Autowired
    private NettyServer nettyServer;


    public static void main(String[] args) {

        SpringApplication.run(ChargeingPileNettyApplication.class, args);
    }



    public void run(String...strings) throws Exception{

        InetSocketAddress address = new InetSocketAddress(DefaultConstans.SOKET_IP,DefaultConstans.SOKET_PORT);
        nettyServer.start(address);




    }

}
