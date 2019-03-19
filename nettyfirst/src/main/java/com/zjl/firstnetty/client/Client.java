package com.zjl.firstnetty.client;/**
 * @Auther: zhou
 * @Date: 2019/3/19 13:21
 * @Description:
 */

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 *@ClassName Client
 *@Description TODO
 *@Author zhou
 *Date 2019/3/19 13:21
 *@Version 1.0
 **/
public class Client {

    private static int port = 8081;
    private static String host = "127.0.0.1";

    public void run() throws InterruptedException {
        EventLoopGroup clientGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ClientHandler());
                        }
                    });
            ChannelFuture f = bootstrap.connect(host,port);
            f.channel().closeFuture().sync();
        }finally {
            clientGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws InterruptedException {
          new Client().run();
    }
}
