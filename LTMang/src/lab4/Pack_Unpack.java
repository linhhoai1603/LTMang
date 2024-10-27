package lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pack_Unpack {
	// method dong goi cac file trong 1 thu muc
	public static void pack(String folder, String packedFile) throws IOException {
		// TODO Auto-generated method stub
		RandomAccessFile raf = new RandomAccessFile(packedFile, "rw");
		// tao file
		File directory = new File(folder);
		// kiem tra la directory
		if(directory.isDirectory()) {
			// lay cac file 1 cap cua folder do
			List<File> files = getFileLevelOne(directory);
			// ghi do dai cua directory
			raf.writeInt(files.size());
			// ghi header
			long location = 0;
			// ghi header cua file packed
			long[] headLocation = new long[files.size()];
			int index = 0;
			// dùng 1 mảng long để lưu vị trí của các file
			for(File file : files) {
				headLocation[index++] = raf.getFilePointer();
				raf.writeLong(location);
				raf.writeLong(file.length());
				raf.writeUTF(file.getName());
				
			}
			 index = 0;
			 byte[] buff = new byte[102400];
			 int byteRead;
			// ghi content
			for(File file : files) {
				location = raf.getFilePointer();
				raf.seek(headLocation[index++]);
				raf.writeLong(location);
				raf.seek(location);
				FileInputStream fis = new FileInputStream(file);
				while((byteRead = fis.read(buff)) != -1) {
					raf.write(buff, 0, byteRead);
				}
				fis.close();
			}
			raf.close();
		}
	}
	public static List<File> getFileLevelOne(File directory) {
		// TODO Auto-generated method stub
		List<File> result = new ArrayList<File>();
		for(File file : directory.listFiles()) {
			if(file.isFile()) {
				result.add(file);
			}
		}
		return result;
	}
	
	public static void unpack(String packedFile, String extractFile, String destFile) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(packedFile, "rw");
		int size = raf.readInt();
		long location, length;
		String name ;
		for (int i = 0; i < size; i++) {
			location = raf.readLong();
			length = raf.readLong();
			name = raf.readUTF();
			if(name.equalsIgnoreCase(extractFile)) {
				raf.seek(location);
				FileOutputStream fos = new FileOutputStream(destFile);
				coppyData(raf, fos, length);
				fos.close();
			}
		}
		raf.close();
	}
	private static boolean coppyData(RandomAccessFile raf, FileOutputStream fos, long pSize) throws IOException {
		byte[] buff = new byte[102400];
		long remainder = pSize;
		while(remainder != 0) {
			int byteMustRead = (int) ((remainder > buff.length) ? buff.length : remainder); // find min
			int execute = raf.read(buff);
			if(execute == -1) {
				return false; // hết dữ liệu
			}
			fos.write(buff, 0, byteMustRead);
			remainder -= byteMustRead;
		}
		return true;
	}
	public static void main(String[] args) throws IOException {
//		pack("D:\\Test", "D:\\packedFile");
		unpack("D:\\packedFile", "anh1", "D:\\Test\\anhManHinh.png");
		
	}

	

}
