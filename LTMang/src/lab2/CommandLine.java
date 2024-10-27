package lab2;
 import java.io.*;
import java.util.StringTokenizer;
public class CommandLine{
	private File defaultFile;
	private boolean continueCMD;
	BufferedReader userIn;
	public CommandLine(String initPath) {
		this.defaultFile = new File(initPath);
		userIn = new BufferedReader(new InputStreamReader(System.in));
	}
	public void start() throws IOException {
		// get path
		System.out.println(getDefaultPath());
		// loop: 
		
		while(!continueCMD) {
		// get line 	
			String line = userIn.readLine();
			StringTokenizer userInTokenizer = new StringTokenizer(line);
			String com = userInTokenizer.nextToken().toUpperCase();
			String path = "";
			if(userInTokenizer.hasMoreElements()) path = userInTokenizer.nextToken();
		// execute 
			switch (com) {
			case "EXIT": 
				continueCMD = true;
			break;
			case "DIR": 
				System.out.println(dirFolder());
			break;
			case "CD": 
				System.out.println(this.cdCommandLine(path));
			break;
			case "DELETE":
				delete(defaultFile.getAbsolutePath());
			break;
			}
		// result 
		// get path
			System.out.print(getDefaultPath());
		}
	}
	private String cdCommandLine(String path) {
		String res = "";
		if(path.isBlank()) {
			
			return defaultFile.getAbsolutePath();
		}
		if("..".equals(path)) {
			defaultFile = defaultFile.getParentFile();
			return "";
			}
		else {
			File file = new File(defaultFile, path);
			if(!file.exists()) {
				res = "Folder not exists";
				return res;
			}
			defaultFile = file;
		}
		return res;
	}
	private boolean delete(String path) {
		File file = new File(path);
		if(!file.exists()) return true;
		File[] files = file.listFiles();
		if(files != null) {
			for(File f : files) {
				delete(f.getAbsolutePath());
			}
		}
		defaultFile = defaultFile.getParentFile();
		return file.delete();
		
	}
	private String dirFolder() {
		String res = "";
		String folder = "";
		String file = "";
		File[] files = this.defaultFile.listFiles();
		if(files == null) {
			return "";
		}else {
			for(File f : files) {
				if(f.isDirectory()) {
					folder+=f.getName()+"\n";
				}
				if(f.isFile()) {
					file+=f.getName()+"\n";
				}
			}
			res += folder.toUpperCase() + file.toLowerCase();
		}
		return res;
	}
	private  String getDefaultPath() {
		return this.defaultFile.getAbsolutePath()+">";
	}
	public static void main(String[] args) throws IOException {
		CommandLine cmd = new CommandLine("D:\\Test");
		cmd.start();
	}
}