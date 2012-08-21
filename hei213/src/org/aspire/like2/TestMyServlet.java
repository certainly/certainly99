package org.aspire.like2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestMyServlet
 */
public class TestMyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestMyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tUrl="http://s.weibo.com/weibo/%25E8%25A5%25BF%25E5%258D%2595%25E5%25A4%25A7%25E6%2582%25A6%25E5%259F%258EWIFI%25E5%25AF%2586%25E7%25A0%2581?&topnav=1&topsug=1";
		String tContent=downww(tUrl);
		String tar="WIFI\\u5bc6\\u7801<\\/span>\\uff1a";
		String withold=getUnicode("更多热门微博");
		int lan=tContent.indexOf(withold);
		int begin=tContent.indexOf(tar);
		String time=getUnicode("今天")+" 09:00";
		int idxTime=tContent.indexOf(time);
		System.out.println("idxTime="+idxTime);
		if(lan>0 && begin<lan && idxTime>lan){
			begin=tContent.indexOf(tar,begin+1);
		}
		int realBegin=begin+30;
		while(tContent.charAt(realBegin)==' ' || tContent.charAt(realBegin)=='\n'){
			realBegin++;
		}
		String rz=tContent.substring(realBegin,realBegin+8);
		System.out.println("TestMyServlet.doGet():"+rz);
		response.getOutputStream().write(rz.getBytes());
		
	}
	public   static   String   getUnicode(String   source){ 
		  String   returnUniCode=null; 
		  String   uniCodeTemp=null; 
		  for(int   i=0;i <source.length();i++){ 
		    uniCodeTemp   =   "\\u"+Integer.toHexString((int)source.charAt(i)); 
		    returnUniCode=returnUniCode==null?uniCodeTemp:returnUniCode+uniCodeTemp; 
		  } 
		  System.out.print(source   + "   's   unicode   ="+returnUniCode); 
		  return   returnUniCode; 
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	public static String downww (String st){
		String str = "";
		String s = "";
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
		URL u =new URL(st);
		HttpURLConnection huc =(HttpURLConnection) u.openConnection();
		InputStream in = huc.getInputStream();
		br = new BufferedReader(new InputStreamReader(in,"utf-8"));
		while ((s = br.readLine()) != null)
		{
		str += s;
		}
		br.close();
		huc.disconnect(); 
		} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} finally { 

		} 

		return str;
		}
}
