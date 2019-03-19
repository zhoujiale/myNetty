package com.zjl.firstnetty.server;/**
                                  * @Auther: zhou
                                  * @Date: 2019/3/19 13:14
                                  * @Description:
                                  */

import java.util.List;
import java.util.ArrayList;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName ServerHandler
 * @Description 服务端处理类
 * @Author zhou Date 2019/3/19 13:14
 * @Version 1.0
 **/
public class ServerHandler extends SimpleChannelInboundHandler {
     
    public static List<Channel> channels = new ArrayList<Channel>();

    // 通道就绪
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel inChannel = ctx.channel();
        channels.add(inChannel);
        System.out.println("server:"+inChannel.remoteAddress().toString().substring(1) + "上线");
    }

    // 通道未就绪
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
       Channel inChannel = ctx.channel();
       channels.remove(inChannel);
       System.out.println("server:"+inChannel.remoteAddress().toString().substring(1)+"离线");
    }

    // 异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    //读取数据
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel inChannel = ctx.channel();
        for(Channel channel:channels){
             if(channel != inChannel){
                 channel.writeAndFlush("{"+inChannel.remoteAddress().toString().substring(1)+"}"+"say:"+msg+"\n");
             }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    
}
