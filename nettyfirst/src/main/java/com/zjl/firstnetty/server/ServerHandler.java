package com.zjl.firstnetty.server;



import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @ClassName ServerHandler
 * @Description 服务端处理类
 * @Author zhou Date 2019/3/19 13:14
 * @Version 1.0
 **/
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * @description 通道就绪
     * @author zhou
     * @created  2019/3/22 13:15
     * @param
     * @return
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        boolean active = channel.isActive();
        if (active) {
            System.out.println("[" + channel.remoteAddress() + "] is online");
        } else {
            System.out.println("[" + channel.remoteAddress() + "] is offline");
        }
        ctx.writeAndFlush("[server]: welcome");
    }

    /**
     * @description  通道退出
     * @author zhou
     * @created  2019/3/22 13:15
     * @param
     * @return
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        if (!channel.isActive()) {
            System.out.println("[" + channel.remoteAddress() + "] is offline");
        } else {
            System.out.println("[" + channel.remoteAddress() + "] is online");
        }
    }

    /**
     * @description 异常捕获
     * @author zhou
     * @created  2019/3/22 13:16
     * @param
     * @return
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("[" + channel.remoteAddress() + "] leave the room");
        ctx.close().sync();
    }



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        Channel inChannel = ctx.channel();
        for(Channel channel:channels){
            if(channel != inChannel){
                channel.writeAndFlush("{"+inChannel.remoteAddress().toString().substring(1)+"}"+"say:"+s+"\n");

            }else{
                channel.writeAndFlush("[you]" + s + "\n");
            }
        }
    }

    /**
     * @description 处理新加的消息通道
     * @author zhou
     * @created  2019/3/22 13:17
     * @param
     * @return
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
       Channel channel = ctx.channel();
       for(Channel ch :channels){
           if(ch == channel){
               ch.writeAndFlush("[" + channel.remoteAddress() + "] coming");
           }
       }
        channels.add(channel);
    }

    /**
     * @description 处理退出的通道
     * @author zhou
     * @created  2019/3/22 13:19
     * @param
     * @return
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        for(Channel ch:channels){
            if(ch == channel){
                ch.writeAndFlush("[" + channel.remoteAddress() + "] leaving");
            }
        }
        channels.remove(channel);
    }
}
