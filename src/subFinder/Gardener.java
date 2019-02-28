package subFinder;

import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.RootPaneContainer;

public class Gardener {
	keywordHeap heap = new keywordHeap();
	ArrayList<WebTree>forest;
	ArrayList<WebTree>new_forest;

	
	public Gardener(ArrayList<WebTree>forest) {
		// TODO Auto-generated constructor stub
		this.forest = forest;
		plant();
		trim();
	}
	
	private void plant() {
		int area = forest.size();
		for(int i=0 ; i < area; i++) {
			WebPage page = forest.get(i).root.webPage;
			String name = page.name;
			double score = page.score;
			Keyword keyword = new Keyword(name, score);
			heap.add(keyword);
		}
	}
	
	public void printResult() {
		heap.showPri();
	}
	//上面的code是給java自己用的，如果要用網頁處理需要儲存為資料結構，下面來實作
	private void trim() {
		ArrayList<String>names = heap.findName();
		ArrayList<WebTree>new_forest =new ArrayList<WebTree>();
		for(int i = 0 ; i < names.size();i++) {
			for(int j = 0 ; j <forest.size();j++) {
				String thisPlace = forest.get(j).root.webPage.name;
				if(names.get(i)==thisPlace) {
					new_forest.add(forest.get(j));
				}
			}
		}
		this.new_forest = new_forest;	
	}
	
	public HashMap<String, String>myBeautifulForest(){
		HashMap<String, String>forFrontier = new HashMap<String, String>();
		ArrayList<WebTree>beautiForest = this.new_forest;
		for(WebTree tree:beautiForest) {
			String name = tree.root.webPage.name;
			String url = tree.root.webPage.url;
			forFrontier.put(name, url);
		}
		return forFrontier;
	}

}
