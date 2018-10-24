package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServer {
	
	public static void main(String[] args) throws Exception {
        //1 ��һ���߳��� �����ڽ���Client�����ӵ�
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //2 �ڶ����߳��� ������ʵ�ʵ�ҵ���������
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        //3 ����һ��������Bootstrap�����Ƕ����ǵ�Server����һϵ�е�����
        ServerBootstrap b = new ServerBootstrap(); 
        //�����������߳���������
        b.group(bossGroup, workerGroup)
        //��Ҫָ��ʹ��NioServerSocketChannel�������͵�ͨ��
         .channel(NioServerSocketChannel.class)
        //һ��Ҫʹ�� childHandler ȥ�󶨾���� �¼�������
         .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel sc) throws Exception {
            	sc.pipeline().addLast("docode",new StringDecoder());
                sc.pipeline().addLast("encode",new StringEncoder());
                sc.pipeline().addLast(new NettyServerHandler());
            }
        });

        //��ָ���Ķ˿� ���м���
        ChannelFuture f = b.bind(8765).sync(); 
        
        //Thread.sleep(1000000);
        f.channel().closeFuture().sync();
        
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
         
        
        
    }
}
