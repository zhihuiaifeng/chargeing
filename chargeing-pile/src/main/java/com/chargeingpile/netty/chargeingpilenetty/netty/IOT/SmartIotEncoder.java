package com.chargeingpile.netty.chargeingpilenetty.netty.IOT;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
public class SmartIotEncoder extends MessageToByteEncoder<SmartIotProtocol>{

    @Override
    protected void encode(ChannelHandlerContext ctx, SmartIotProtocol msg, ByteBuf out) throws Exception {
        //写入消息SmartIot具体内容
        out.writeShort(SmartIotProtocol.START);
        out.writeShort(msg.getFlowid());
        out.writeByte(msg.getVersion_major());
        out.writeByte(msg.getVersion_minor());
        out.writeByte(msg.getSecond());
        out.writeByte(msg.getMinute());
        out.writeByte(msg.getHour());
        out.writeByte(msg.getDay());
        out.writeByte(msg.getMonth());
        out.writeByte(msg.getYear());
        out.writeBytes(msg.getSrc());
        out.writeBytes(msg.getDest());
        out.writeShort(msg.getData_len());
        out.writeByte(msg.getCmd());
        out.writeBytes(msg.getData());
        out.writeByte(msg.getChecksum());
        out.writeShort(SmartIotProtocol.END);
    }
}
