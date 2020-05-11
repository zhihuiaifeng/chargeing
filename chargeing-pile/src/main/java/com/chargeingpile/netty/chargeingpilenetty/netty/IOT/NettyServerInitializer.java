package com.chargeingpile.netty.chargeingpilenetty.netty.IOT;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
//        ChannelPipeline pipeline = ch.pipeline();
//        //自定义切割符
//        //ByteBuf delimiter = Unpooled.copiedBuffer(new byte[] {16});
//        ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
//
//        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, delimiter));
//        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
//        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
//        pipeline.addLast(new NettyServerHandler());

        ChannelPipeline pipeline = ch.pipeline();
        //添加自定义编解码器
        pipeline.addLast(new SmartIotEncoder());
        pipeline.addLast(new SmartIotDecoder());
        //处理网络IO
        pipeline.addLast(new SmartIotHandler());
    }

}
