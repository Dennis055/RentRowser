package subFinder;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
//
//import javax.swing.text.Document;
//import javax.swing.text.Element;

import org.jsoup.Jsoup;
import org.jsoup.internal.ConstrainableInputStream;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.io.BufferedReader;
import java.io.IOException;



public class GoogleQuery {
 public String searchKeyword;
 public String url;
 public String content;
 public ArrayList<String>RootURL;
 public ArrayList<String>titleList;
 
 public GoogleQuery(String searchKeyword){
  this.searchKeyword = searchKeyword;
  this.url = "https://www.google.com.tw/search?q=" + searchKeyword + "&num=15";//我們先找5筆資料，不然server會爆掉，但太多筆資料時間又很長。
 }
 
	private String fetchContent() throws IOException{
		//TODO 如何處理沒有https的網頁？以免出現 java.net.MalformedURLException:
//		if(this.url.contains("BBC")==true) {
//			this.url = "https://www.google.com"; //
//		}
		String NoContent = "";
		if(this.url.contains("http")!=true) {
			this.url  = "http://" + url;
		}
		try {
		URL url = new URL(this.url);
		URLConnection conn = url.openConnection();
		HttpURLConnection httpCon = (HttpURLConnection) conn;
		int code = httpCon.getResponseCode();
		if(code==HttpURLConnection.HTTP_OK) {//避免連線錯誤。
		InputStream in = conn.getInputStream();
		BufferedReader bReader = new BufferedReader(new InputStreamReader(in));
		
		String retVal = "";
		String line = null;
		
		while((line = bReader.readLine())!=null) { //while loop continue
			retVal = retVal + line + "\n";  
			}
		in.close();
		return retVal;
		}else {
			 System.out.println("Can not access the website");
			 
		}	
		}catch (MalformedURLException e) {
			System.out.println("Wrong URL!");
			// TODO: handle exception
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("Can't not connect!");
		}catch (IllegalArgumentException e) {
			// TODO: handle exception
			System.out.println("又是你啊痞客邦！");
		}
		return NoContent;
	}
 
// public HashMap<String, String> query() throws IOException{
//  if(this.content == null) {
//   this.content = fetchContent();
//  }
//  HashMap<String, String> retVal = new HashMap<String, String>();
//  Document document = Jsoup.parse(this.content);
//  Elements lis =  document.select("div.g");
//  
//  for(Element li : lis) {
//   try {
//    Element h3 = li.select("h3.r").get(0);
//    String title = h3.text();
//    
//    Element cite = li.select("cite").get(0);
//    String citeUrl = cite.text();
//    System.out.println(title + " " + citeUrl);;
//    retVal.put(title, citeUrl);
//   } catch(IndexOutOfBoundsException e) {
//    
//   }
//  }
//  return retVal;
// }
// 
 
 
 public ArrayList<String>query() throws IOException{
	  if(this.content == null) {
	   this.content = fetchContent();
	  }
	  //HashMap<String, String> retVal = new HashMap<String, String>();
	  ArrayList<String>retVal = new ArrayList<String>();
	  Document document = Jsoup.parse(this.content);
	  Elements lis =  document.select("div.g");
	  
	  for(Element li : lis) {
	   try {
	    Element h3 = li.select("h3.r").get(0);
	    String title = h3.text();
	    
	    Element cite = li.select("cite").get(0);
	    String citeUrl = cite.text();
	    System.out.println(title + " " + citeUrl);;
	    retVal.add(citeUrl);
	   } catch(IndexOutOfBoundsException e) {
	    
	   }
	  }
	  return retVal;
	 }

 public ArrayList<String>queryForTitle() throws IOException{
	  if(this.content == null) {
	   this.content = fetchContent();
	  }
	  //HashMap<String, String> retVal = new HashMap<String, String>();
	  ArrayList<String>retVal = new ArrayList<String>();
	  Document document = Jsoup.parse(this.content);
	  Elements lis =  document.select("div.g");
	  
	  for(Element li : lis) {
	   try {
	    Element h3 = li.select("h3.r").get(0);
	    String title = h3.text();
	    
	    Element cite = li.select("cite").get(0);
	    String citeUrl = cite.text();
	    //System.out.println(title + " " + citeUrl);;
	    retVal.add(title);
	   } catch(IndexOutOfBoundsException e) {
	    
	   }
	  }
	  this.titleList = retVal;
	  return retVal;
	 }
 
 	//TODO:把google ban掉
// 	public ArrayList<String> query() throws IOException{
// 		ArrayList<String>retVal = new ArrayList<String>();
// 		if(this.content==null) {
// 			this.content = fetchContent();
// 		}
// 		 Document doc = Jsoup.connect(this.url).get();
// 		 Elements links = doc.select("a[href]");
// 		 for(Element link:links) {
// 			String str =  link.attr("href");
// 			if(str.startsWith("http")) {
// 			//	System.out.println(str);
// 				if(str.contains("google"))
// 					continue;
// 				retVal.add(str);
// 			}
// 	}
// 		 this.RootURL = retVal;
// 		return retVal;
// 	}
 	
 	public void printResult() {
 		for(int i = 0 ; i<RootURL.size();i++) {
 			System.out.println(titleList + ", "+ RootURL.get(i));
 		}
 	}
 
 	public static void main(String[]args) throws IOException {
// 		 ArrayList<String>retVal = new ArrayList<String>();
// 		 GoogleQuery query = new GoogleQuery("壽司");
// 		 String content = query.fetchContent();
// 		 Document document = Jsoup.parse(content);
// 		 //Elements links = document.select("h3.r");
// 		// Elements links = document.select("a[href]");
// 		 Document doc = Jsoup.connect(query.url).get();
// 		 Elements links = doc.select("a[href]");
// 		 for(Element link:links) {
// 			String str =  link.attr("href");
// 			if(str.startsWith("http")) {
// 				if(str.contains("google")==true) {
// 					continue;
// 				}
// 				System.out.println(str);
// 			}
//
// 		 }
// 		 retVal = query.query();
// 		 query.printResult();
 		 
 	
 	}
}