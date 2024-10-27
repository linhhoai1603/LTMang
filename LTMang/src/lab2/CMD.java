package lab2;

import java.io.*;

public class CMD {
	// method cd back folder parent
	public static String cdFolder(File file, String statement) {
		String result = "";
		statement.trim(); // remove space
		if (statement.equalsIgnoreCase("cd ..")) {
			file = file.getParentFile();
			return result; // success
		}
		if (statement.startsWith("cd /")) {
			String path = statement.substring(4);
			File f = new File(path);
			if (f.exists()) {
				file = f;
				return result; // success
			}
		}
		return result;
	}

	// method dir list file in folder level 1
	public static String dirFolder(File file, String statement) {
		String resultFile = "";
		String resultDirector = "";
		StringBuilder result = new StringBuilder();
		statement.trim(); // remove space
		if (statement.equalsIgnoreCase("dir")) {
			File[] files = file.listFiles();
			for (File f : files) {
				if (f.isDirectory()) {
					resultDirector += f.getName() + "\n";
				}
				if (f.isFile()) {
					resultFile += f.getName() + "\n";
				}
			}
			result.append(resultDirector.toUpperCase());
			result.append(resultFile.toLowerCase());
			return result.toString(); // success
		}

		return null;
	}

	// method delete file or folder current
	public static String delFolder(File file, String statement) {
		String result = "";
		statement.trim(); // remove space
		if (statement.startsWith("del ")) {
			String fileName = statement.substring(4);
			File f = new File(file, fileName);
			if (f.exists()) {
				f.delete();
				return result; // success
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// test method dir
		File file = new File("D:\\LTWeb");
		String statement = "dir";
		System.out.println(dirFolder(file, statement));
	}
}
