package subFinder;
import java.util.Comparator;

public class KeywordComparator implements Comparator<Keyword>{
		public int compare(Keyword o1 ,Keyword o2) {
			if(o1==null||o2==null) {
				throw new NullPointerException();
			}
			if(o1.weight < o2.weight) {
				return -1;
			}else if(o1.weight > o2.weight){
				return 1;
			}
			return 0;
		}
		
}
