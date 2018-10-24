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

	//被坑的地方 这儿 我不知道是我的没有理解到还是本来就是 空的，这儿的大小直接0，需要在handlerAdded方法中去添加
	public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	public static Map<String,Channel> map =new HashMap<String,Channel>();

	/* 
	 * 本方法是重写了channelRead 方法 接受到信息时做出的处理
	 * ctx 通道处理的上下文信息
	 * msg 接受的消息
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
	        System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"异常");
	        // 当出现异常就关闭连接
	        cause.printStackTrace();
	        ctx.close();
	    }

	    //一旦有客服端连接服务端就触发这个方法
	    @Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
	        Channel incoming = ctx.channel();
	       
			System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"在线");
		}
	    
	    @Override
	    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
	        Channel incoming = ctx.channel();
	        for (Channel channel : group) {
	            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
	        }
	        group.add(ctx.channel());
	    }

	    @Override
	    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
	        Channel incoming = ctx.channel();
	        for (Channel channel : group) {
	            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
	        }
	        group.remove(ctx.channel());
	    }
	
	    
	    @Override
	    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
	        Channel incoming = ctx.channel();
	        System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"掉线");
	    }
	    
	  
	
}
