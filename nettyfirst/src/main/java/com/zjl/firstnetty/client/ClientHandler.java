package com.zjl.firstnetty.client;/**
 * @Auther: zhou
 * @Date: 2019/3/19 13:31
 * @Description:
 */


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 *@ClassName ClientHandler
 *@Description TODO
 *@Author zhou
 *Date 2019/3/19 13:31
 *@Version 1.0
 **/
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
