package subFinder;
import java.awt.Window;
import java.awt.Window.Type;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.InputMismatchException;

import javax.swing.text.AbstractDocument.Content;

public class wordCounter {
	private String url;
	private String cotent;
	
	public wordCounter(String url) {
		// TODO Auto-generated constructor stub
		this.url = url;
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
		if(code==HttpURLConnection.HTTP_OK) {
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
			System.out.println("Can not connect!");
		}
		return NoContent;
	}
	
	public  int countKeyword(String keyword) throws IOException {
		if(cotent==null) {
			cotent = fetchContent();
		} // To count the times of content after this method
		
		cotent = cotent.toUpperCase();
		keyword = keyword.toUpperCase();
		// Transforming the word to upper case for catch the key word 
		
		// TO DO:indexOf(keyword)
		int count = 0;	
		while(cotent.indexOf(keyword)!=-1) {
		int cur = cotent.indexOf(keyword);
		cotent = cotent.substring(cotent.indexOf(keyword) + keyword.length(),cotent.length());
		count++;
			}	
		// brute-force algorithm :time complexcity: O(mn) -> while exhits the m time of P.length()
		// n shows that the occurence occures n time (n.length = n)
		return count ;
	}
	

}


