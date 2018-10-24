package netty;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClinet {
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup workgroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(workgroup)
        .channel(NioSocketChannel.class)
        .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel sc) throws Exception {
            	sc.pipeline().addLast("stringD", new StringDecoder());
            	sc.pipeline().addLast("stringC", new StringEncoder());
            	sc.pipeline().addLast("http", new HttpClientCodec());
                sc.pipeline().addLast(new NettyClientHandler());
            }
        });
        
        Channel channel = b.connect("127.0.0.1", 8765).sync().channel();
        
        System.out.println("zijidedizhi:"+channel.remoteAddress());
       // channel.writeAndFlush("nihao");
        try {
        	  while(true){
              	BufferedReader reader = new BufferedReader(
                          new InputStreamReader(System.in));
                  String input = reader.readLine();
                  if(input!=null){
                	  if("exit".equals(input)){
                		  System.exit(1);
                	  }
                  channel.writeAndFlush(input);
                  }
              }
		} catch(Exception e) {
			 System.exit(1);
		}
      
        //–¥»Î
        //cf1.channel().writeAndFlush(Unpooled.copiedBuffer("777".getBytes()));
        
        //cf1.channel().closeFuture().sync();
       // workgroup.shutdownGracefully();
        
    }
}
