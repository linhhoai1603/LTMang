package fix.lab1;

import java.io.File;

public class DeleteFolder {
	public static boolean delete(String path) {
		File file = new File(path);
		if(!file.exists()) return true;
		File[] files = file.listFiles();
		if(files != null) {
			for(File f : files) {
				delete(f.getAbsolutePath());
			}
		}
		return file.delete();
		
	}
	public static void main(String[] args) {
		String path = "D:\\abc";
		System.out.println(delete(path));
	}
}
