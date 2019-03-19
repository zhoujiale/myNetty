package com.zjl.firstnetty.client;

import java.util.Scanner;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 *@ClassName Client
 *@Description 客户端启动类
 *@Author zhou
 *Date 2019/3/19 13:21
 *@Version 1.0
 **/
public class Client {

    private final int port;
    private final String host;

    public Client(String host,int port){
        this.host = host;
        this.port = port;
    }

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
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("decoder",new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());
                            pipeline.addLast(new ClientHandler());
                        }
                    });
            System.out.println("启动客户端");
            ChannelFuture f = bootstrap.connect(host,port);
            Channel cn = f.channel();
            System.out.println(cn.localAddress().toString().substring(1));
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNextLine()){
              String msg = scanner.nextLine();
              cn.writeAndFlush(msg);
            }

        }finally {
            clientGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws InterruptedException {
          new Client("127.0.0.1",9000).run();
    }
}
