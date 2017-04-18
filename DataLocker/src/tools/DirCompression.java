package tools;
import java.io.*;
import java.net.URI;
import java.util.Deque;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DirCompression {

	public DirCompression() {
		// TODO Auto-generated constructor stub
		this.copyDir(new File("/Users/Jake/Downloads"), new File("/Users/Jake/Documents/temp12"));
	}
	
	public static void main(String args[]){
		new DirCompression();
	}
	
	 public static void zip(File directory, File zipfile) throws IOException {
		    URI base = directory.toURI();
		    Deque<File> queue = new LinkedList<File>();
		    queue.push(directory);
		    OutputStream out = new FileOutputStream(zipfile);
		    Closeable res = out;
		    try {
		      ZipOutputStream zout = new ZipOutputStream(out);
		      res = zout;
		      while (!queue.isEmpty()) {
		        directory = queue.pop();
		        for (File kid : directory.listFiles()) {
		          String name = base.relativize(kid.toURI()).getPath();
		          if (kid.isDirectory()) {
		            queue.push(kid);
		            name = name.endsWith("/") ? name : name + "/";
		            zout.putNextEntry(new ZipEntry(name));
		          } else {
		            zout.putNextEntry(new ZipEntry(name));
		         //   copy(kid, zout);1
		            zout.closeEntry();
		          }
		        }
		      }
		    } finally {
		      res.close();
		    }
		  }
	
	public void copyDir(File src, File tar){
		
		if(!src.isDirectory()){
			System.out.println("source folder is invalid, program exit");
			return;
		}
		if(!tar.exists()){
			boolean created=tar.mkdir();
		}
		
		File[] files= src.listFiles();
		for(int i=0;i<files.length;i++){
			if(files[i].isFile()){
				this.copySingleFile(files[i], tar);
			}else{
				System.out.println( files[i].getAbsolutePath());
				System.out.println(files[i].getName());
				String tarurl=tar.getAbsolutePath();
				File nTarDir=new File(tarurl+"/"+files[i].getName());
				nTarDir.mkdirs();
				this.copyDir(files[i], nTarDir);
			}
		}
		
		
		
	}
	
	public void copySingleFile(File src, File dir){
		//System.out.println("[copy single file]"+src.getName());
		try {
			FileInputStream fin=new FileInputStream(src);
			FileOutputStream fout=new FileOutputStream(dir.getAbsolutePath()+"/"+src.getName());
			int len=-1;
			byte[] buffer=new byte[1024*8];
			
			while((len=fin.read(buffer))!=-1  ){
				fout.write(buffer, 0, len);
			}
			
			fout.flush();
			fout.close();
			fin.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
