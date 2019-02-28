package subFinder;

import java.io.IOException;
import java.util.ArrayList;

public class UnitTest {
	public static void main(String[]args) throws IOException {
		String keyTest = "壽司";//"壽司"可以替換成前端變數
		GoogleQuery querier = new GoogleQuery(keyTest);
		ArrayList<String>rootlist = querier.query();
		ArrayList<String>titleList =querier.queryForTitle(); 
		//Setting for test
		int i = 0; //為了for each可以控制index
		Keyword keyword = new Keyword(keyTest, 1.1);
		ArrayList<Keyword>keywords = new ArrayList<Keyword>();
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
		
			//TODO 子網頁的分數累加到整個網頁  
			tree.setPostOrderScore(keywords);
			forest.add(tree);		
			//tree.printTree();
		//System.out.println(tree.root.nodeScore);
			}
		//上面括弧是最上層for迴圈結束
		Gardener gardener = new Gardener(forest);
		System.out.println("這是您的搜尋結果");
		gardener.printResult();
	}
}