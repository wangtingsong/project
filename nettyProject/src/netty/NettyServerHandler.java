package netty;

import java.util.HashMap;
import java.util.Map;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class NettyServerHandler  extends ChannelHandlerAdapter{

	//���ӵĵط� ��� �Ҳ�֪�����ҵ�û����⵽���Ǳ������� �յģ�����Ĵ�Сֱ��0����Ҫ��handlerAdded������ȥ���
	public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	public static Map<String,Channel> map =new HashMap<String,Channel>();

	/* 
	 * ����������д��channelRead ���� ���ܵ���Ϣʱ�����Ĵ���
	 * ctx ͨ���������������Ϣ
	 * msg ���ܵ���Ϣ
	 */
	 @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	    
	            //do something msg
	            
	            /*byte[] data = new byte[buf.readableBytes()];	
	            buf.readBytes(data);
	            String request = new String(data, "utf-8");*/
		       Channel channel = ctx.channel();
		       System.out.println(group.size());
	            for(Channel ch: group){
	            	if(ch==channel){
	            		ch.writeAndFlush("[you say]:"+msg+"\n");
	            	}else{
	            		ch.writeAndFlush("["+channel.remoteAddress() +"say]:"+msg+"\n");
	            	}
	            }
	           
	                                                                                                 

	    }

	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	    	Channel incoming = ctx.channel();
	        System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"�쳣");
	        // �������쳣�͹ر�����
	        cause.printStackTrace();
	        ctx.close();
	    }

	    //һ���пͷ������ӷ���˾ʹ����������
	    @Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
	        Channel incoming = ctx.channel();
	       
			System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"����");
		}
	    
	    @Override
	    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
	        Channel incoming = ctx.channel();
	        for (Channel channel : group) {
	            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " ����\n");
	        }
	        group.add(ctx.channel());
	    }

	    @Override
	    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
	        Channel incoming = ctx.channel();
	        for (Channel channel : group) {
	            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " �뿪\n");
	        }
	        group.remove(ctx.channel());
	    }
	
	    
	    @Override
	    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
	        Channel incoming = ctx.channel();
	        System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"����");
	    }
	    
	  
	
}
