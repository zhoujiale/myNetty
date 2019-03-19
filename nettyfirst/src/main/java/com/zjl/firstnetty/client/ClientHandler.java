package com.zjl.firstnetty.client;/**
 * @Auther: zhou
 * @Date: 2019/3/19 13:31
 * @Description:
 */


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *@ClassName ClientHandler
 *@Description 客户端处理类
 *@Author zhou
 *Date 2019/3/19 13:31
 *@Version 1.0
 **/
public class ClientHandler extends SimpleChannelInboundHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(((String) msg).trim());
    }
}
