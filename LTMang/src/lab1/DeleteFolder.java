package lab1;

import java.io.File;

public class DeleteFolder {
	public static boolean delete(String path) {
		File file = new File(path);
		// file is not available
		if(!file.exists()) {
			System.out.println("File not exists");
			return true;
		}
		// file is file
		if(file.isFile()) {
			System.out.println("This is file");
			return file.delete();
		}
		if(file.isDirectory()) {
			System.out.println("This is folder");
			return deleteFolder(file);
		}
		
		return false;
		
	}
	// method delete folder
	private static boolean deleteFolder(File file) {
		// file children
		File[] files = file.listFiles();
		if(files == null) {
			System.out.println("This is a empty folder");
			return true;
		}
		else {
			for(File fi : files) {
				if(fi.isDirectory()) {
					if(!deleteFolder(fi)) { // if file children is folder then recursor delete file 
						return false;
					}
				}
				if(fi.isFile()) {
					if(!fi.delete()) { // can't delete file 
						return false;
					}
				}
			}
		}
		return file.delete(); // delete file root
	}
	public static void main(String[] args) {
		String path = "D:\\abc";
		System.out.println(delete(path));
	}
}
