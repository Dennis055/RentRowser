package subFinder;
import java.io.IOException;
import java.util.ArrayList;

public class WebTree {
	public WebNode root;
	
	public WebTree(WebPage rootPage) {
		this.root= new WebNode(rootPage);
	}
	public void setPostOrderScore(ArrayList<Keyword>keywords) throws IOException {
		setPostOrderScore(root, keywords);
	}
	private void setPostOrderScore(WebNode startNode,ArrayList<Keyword>keywords) throws IOException {
		for(WebNode child:startNode.children) {
			setPostOrderScore(child,keywords);
		}
		startNode.setNodeScore(keywords);
	}
	public void printTree() {
		printTree(root);
	}
	private void printTree(WebNode startNode) {
		for(int i=0;i<startNode.getDepth();i++) {
			System.out.print("  ");
		}
		if(startNode.children.isEmpty()) {
			System.out.println("(" + startNode.webPage.name + "," + startNode.webPage.score+")");
		}else {
			System.out.println("("+ startNode.webPage.name + ","+startNode.webPage.score);
			for(WebNode child:startNode.children) {
				printTree(child);
			}
			System.out.println(")");
		}
	}
	//Just for unit test
	private void setRootscore(WebNode root , ArrayList<Keyword>keywords) throws IOException {
		root.setNodeScore(keywords);
	}
	public void setRootScore(ArrayList<Keyword>keywords) throws IOException {
		setRootscore(this.root, keywords);
	}
	public void printRoot(WebNode startnode) {
		System.out.println("(" + startnode.webPage.name + startnode.nodeScore + ")");
	}
	
}
