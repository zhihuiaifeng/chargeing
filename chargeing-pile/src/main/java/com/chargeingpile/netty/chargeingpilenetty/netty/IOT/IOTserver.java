package com.chargeingpile.netty.chargeingpilenetty.netty.IOT;

import com.chargeingpile.netty.chargeingpilenetty.constans.DefaultConstans;
import com.chargeingpile.netty.chargeingpilenetty.netty.car.Server;
import com.chargeingpile.netty.chargeingpilenetty.netty.car.ServerHandler;
import com.chargeingpile.netty.chargeingpilenetty.netty.car.SmartCarDecoder;
import com.chargeingpile.netty.chargeingpilenetty.netty.car.SmartCarEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

public class IOTserver {

    public IOTserver() {
    }

    public void bind(InetSocketAddress address) throws Exception {
        // 配置NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 服务器辅助启动类配置
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChildChannelHandler())//
                    .option(ChannelOption.SO_BACKLOG, 1024) // 设置tcp缓冲区 // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
            // 绑定端口 同步等待绑定成功
            ChannelFuture f = b.bind(address).sync(); // (7)
            // 等到服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            // 优雅释放线程资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    /**
     * 网络事件处理器
     */
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            // 添加自定义协议的编解码工具
            ch.pipeline().addLast(new SmartIotEncoder());
            ch.pipeline().addLast(new SmartIotDecoder());
            // 处理网络IO
            ch.pipeline().addLast(new SmartIotHandler());
        }
    }

    public static void main(String[] args) throws Exception {
        InetSocketAddress address = new InetSocketAddress(DefaultConstans.SOKET_IP,DefaultConstans.SOKET_PORT);
        new IOTserver().bind(address);
    }
}
