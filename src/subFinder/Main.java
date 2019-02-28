package subFinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Main() {
        // TODO Auto-generated constructor stub
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
				response.setCharacterEncoding("UTF-8");
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html");
				if(request.getParameter("keyword")== null) {
					String requestUri = request.getRequestURI();
					request.setAttribute("requestUri", requestUri);
					request.getRequestDispatcher("Search.jsp").forward(request, response);
					return;
				}
				String keyTest = request.getParameter("keyword");
				GoogleQuery querier = new GoogleQuery(keyTest);
				//String keyTest = "壽司";//"壽司"可以替換成前端變數
				//GoogleQuery querier = new GoogleQuery(keyTest);
				ArrayList<String>rootlist = querier.query();
				ArrayList<String>titleList =querier.queryForTitle(); 
				//Setting for test
				int i = 0; //為了for each可以控制index
				Keyword keyword = new Keyword(keyTest, 1.1);
				OfficialKey okeKey = new OfficialKey();
				//ArrayList<Keyword>keywords =okeKey.initKeyword();//產生初始字串以符合主題。
				ArrayList<Keyword>keywords = okeKey.initKeyword();
				keywords.add(keyword);
				System.out.println("收集您輸入的關鍵字，完成！");
				
				ArrayList<WebTree>forest = new ArrayList<WebTree>();//一個放所有tree的list
				//end
				System.out.println("計算分數中......");
				for(String rooturl:rootlist) {
					WebPage rootPage = new WebPage(rooturl,titleList.get(i));
					if(i<titleList.size()-1)
						i++;
					WebTree tree = new WebTree(rootPage); //利用每個rootURL設立一顆顆的tree出來
					//System.out.println(rooturl);
					subFinder finder = new subFinder(rooturl);
					ArrayList<String>children  = finder.findSublinks();
					for(String child:children) {
						tree.root.addChild(new WebNode(new WebPage(child ,"")));//放入子連結
					}
					//tree.setRootScore(keywords);//延伸為包含統計子頁面
					//tree.printRoot(tree.root); //print出頁面分數跟標題
					System.out.println("累加分數");
					//TODO 子網頁的分數累加到整個網頁  -> Done 
					tree.setPostOrderScore(keywords);
					forest.add(tree);		
				
					//tree.printTree();
				//System.out.println(tree.root.nodeScore);
					}
				//上面括弧是最上層for迴圈結束
				Gardener gardener = new Gardener(forest);
				//System.out.println("這是您的搜尋結果");
			//gardener.printResult();
			//	ArrayList<String> query = google.query();
				HashMap<String, String>query = gardener.myBeautifulForest();
				//TODO:這上面我們就處理資料 ， 下面我們處理寫入資料。
				String[][] s = new String[query.size()][2];
				request.setAttribute("query", s);
				int num = 0;
				for(Entry<String, String> entry : query.entrySet()) {
				    String key = entry.getKey();
				    String value = entry.getValue();
				    s[num][0] = key;
				    s[num][1] = value;
				    num++;
				    System.out.println(key + "," + value);
				}
				System.out.println("搜尋結果已出爐！");
				
				request.getRequestDispatcher("googleitem.jsp")
				 .forward(request, response);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	} 

}
