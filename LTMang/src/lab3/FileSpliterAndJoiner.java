package lab3;

import java.io.*;

public class FileSpliterAndJoiner {
	private static String createPath(String source, int i) {
		if(i<10) return source + ".00" + i;
		if(i>=10 && i < 100) return source + ".0" + i;
		if(i>=100 && i < 1000) return source + "." + i;
		return null;
	}
	public static void split(String source, long pSize) throws IOException {
		FileInputStream fis = new FileInputStream(source);
		int i = 1;
		boolean hasMoreData = true;
		while(hasMoreData) {
			String dest = createPath(source , i);
			FileOutputStream fos = new FileOutputStream(dest);
			hasMoreData =  coppyData(fis, fos, pSize);
			fos.close();
			i++;
		}
		fis.close();
	}
	
	private static boolean coppyData(FileInputStream fis, FileOutputStream fos, long pSize) throws IOException {
		byte[] buff = new byte[102400];
		long remainder = pSize;
		while(remainder != 0) {
			int byteMustRead = (int) ((remainder > buff.length) ? buff.length : remainder);
			int execute = fis.read(buff);
			if(execute == -1) {
				return false; // hết dữ liệu
			}
			fos.write(buff, 0, byteMustRead);
			remainder -= byteMustRead;
		}
		return true;
	}
	
	public static void join(String partFileName) throws IOException {
		String dest = partFileName.substring(0, partFileName.lastIndexOf("."));
		FileOutputStream fos = new FileOutputStream(dest);
		int i = 1;
		while(true) {
			String source = createPath(dest, i);
			File file = new File(source);
			if(!file.exists()) {
				break;
			}
			FileInputStream fis = new FileInputStream(file);
			coppyData(fis, fos, file.length());
			fis.close();
			i++;
		}
		fos.close();
	}
	
	public static void main(String[] args) throws IOException {
		split("D:\\3 - Module 2- SocketProgramming.pdf", 1000000);
		join("D:\\3 - Module 2- SocketProgramming.pdf.001");
	}
}
