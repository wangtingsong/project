package nettyFile;

import java.io.File;

public class FileUploadFile {
	private static final long serialVersionUID = 1L;
	private File file;// �ļ�
	private String file_md5;// �ļ���
	private int starPos;// ��ʼλ��
	private byte[] bytes;// �ļ��ֽ�����
	private int endPos;// ��βλ��
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFile_md5() {
		return file_md5;
	}
	public void setFile_md5(String file_md5) {
		this.file_md5 = file_md5;
	}
	public int getStarPos() {
		return starPos;
	}
	public void setStarPos(int starPos) {
		this.starPos = starPos;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public int getEndPos() {
		return endPos;
	}
	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
