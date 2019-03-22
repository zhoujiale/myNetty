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
public class ClientHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        System.out.println(s.trim());
    }
}
