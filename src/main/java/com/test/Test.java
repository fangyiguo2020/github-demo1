package com.test;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class Test {
	public static void main(String[] args) throws Exception {
		 System.setProperty("hadoop.home.dir", "D:\\hadoop-2.6.0-cdh5.15.1");	
		 System.out.println("-----开始-----");
		 demo5();
		 System.out.println("-----ok-----");
		 
		 String x=";";
	
	}
	
	
	//查询文件列表 
	static void demo5()throws Exception {
		URI uri=new URI("hdfs://linda:9000");  
		Configuration conf=new Configuration();
		
		FileSystem fs=FileSystem.get(uri, conf);
		
		FileStatus [] fileList=fs.listStatus(new Path("/"));
		for(FileStatus f:  fileList) {
			System.out.println("访问时间:"+f.getAccessTime());
			System.out.println("文件大小:"+f.getLen());
			System.out.println("path:"+f.getPath());
			System.out.println("所有者:"+f.getOwner());
			System.out.println("复本数量:"+f.getReplication());
			System.out.println("块大小:"+f.getBlockSize());	
			System.out.println("是不是一个目录:"+f.isDirectory());
			System.out.println("是不是一个文件:"+f.isFile());
			
			System.out.println("----------------------------");
		}
	}
	
	//建目录
	static void demo4() throws Exception{
		URI uri=new URI("hdfs://linda:9000");  
		Configuration conf=new Configuration();	
		FileSystem fs=FileSystem.get(uri, conf,"admin");	
		fs.mkdirs(new Path("/aa/bb/cc/dd/ee"));	
		fs.close();
	}
	
	//文件删除
	static void demo3() throws Exception{
		URI uri=new URI("hdfs://linda:9000");  
		Configuration conf=new Configuration();
		
		FileSystem fs=FileSystem.get(uri, conf,"admin");
		
		fs.delete(new Path("/关于面试.java"),true);
		fs.close();	
	}
	
	
	//上传文件到hdfs
	static void demo2() throws Exception {
		URI uri=new URI("hdfs://linda:9000");  
		Configuration conf=new Configuration();
		
		FileSystem fs=FileSystem.get(uri, conf,"admin");
		
		InputStream in=new FileInputStream("D:\\关于面试.java");
		OutputStream out =fs.create(new Path("/关于面试.java"));
		
		IOUtils.copyBytes(in, out, 4096,true);
		
		fs.close();
	}
	
	
	//下载hdfs上的文件	
	static void demo1() throws Exception {
		//它是java.net下的
		URI uri=new URI("hdfs://linda:9000");  
		Configuration conf=new Configuration();
		
		FileSystem fs=FileSystem.get(uri, conf);
		
		InputStream in = fs.open(new Path("/book.txt")); 
		
		FileOutputStream out=new FileOutputStream("d:/book.txt");
		IOUtils.copyBytes(in, out, 4096,true);
		
		fs.close();
	}

}
