package com.zjl.firstnetty.server;/**
 * @Auther: zhou
 * @Date: 2019/3/19 13:14
 * @Description:
 */


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


/**
 *@ClassName ServerHandler
 *@Description TODO
 *@Author zhou
 *Date 2019/3/19 13:14
 *@Version 1.0
 **/
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
