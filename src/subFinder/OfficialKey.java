package subFinder;

import java.util.ArrayList;

public class OfficialKey {
		private ArrayList<Keyword>keyBox = new ArrayList<Keyword>();
		
		public OfficialKey() {
			// TODO Auto-generated constructor stub
			setting();
		}
		
		
		private void setting() {
			ArrayList<Keyword>keywords = new ArrayList<Keyword>();
			String init1 = "租屋";
	        String init2 = "學生";
	        String init3 = "低價";
	        String init4 = "車位";
	        String init5  = "傢俱";
	        Keyword k1 = new Keyword(init1, 2);
	        Keyword k2 = new Keyword(init2, 1.05);
	        Keyword k3 = new Keyword(init3, 1.05);
	        Keyword k4 = new Keyword(init4, 1.05);
	        Keyword k5 = new Keyword(init5, 1.05);
//	        
	        keywords.add(k1);
	        keywords.add(k2);
	        keywords.add(k3);
	        keywords.add(k4);
	        keywords.add(k5);
	        this.keyBox = keywords;
		}
		public ArrayList<Keyword>initKeyword(){
			return this.keyBox;
		}
		
		
}	
