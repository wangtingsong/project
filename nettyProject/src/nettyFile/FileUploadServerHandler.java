package nettyFile;

import java.io.File;
import java.io.RandomAccessFile;


import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class FileUploadServerHandler extends ChannelHandlerAdapter{
	private int byteRead;
    private volatile int start = 0;
    private String file_dir = "F:";
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	// TODO Auto-generated method stub
    	super.channelActive(ctx);
    	System.out.println("����ˣ�channelActive()");
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	// TODO Auto-generated method stub
    	super.channelInactive(ctx);
    	System.out.println("����ˣ�channelInactive()");
    	ctx.flush();
    	ctx.close();
    }
    
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FileUploadFile) {
            FileUploadFile ef = (FileUploadFile) msg;
            byte[] bytes = ef.getBytes();
            byteRead = ef.getEndPos();
            String md5 = ef.getFile_md5();//�ļ���
            String path = file_dir + File.separator + md5;
            File file = new File(path);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(start);
            randomAccessFile.write(bytes);
            start = start + byteRead;
            System.out.println("path:"+path+","+byteRead);
            if (byteRead > 0) {
                ctx.writeAndFlush(start);
                randomAccessFile.close();
                if(byteRead!=1024 * 10){
                	Thread.sleep(1000);
                	channelInactive(ctx);
                }
            } else {
            	//System.out.println("�ļ��������");
            	//ctx.flush(); 
                ctx.close();
            }
            
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
        System.out.println("FileUploadServerHandler--exceptionCaught()");
    }

}
