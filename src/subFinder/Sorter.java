package subFinder;

import java.util.ArrayList;

import org.ietf.jgss.Oid;

public class Sorter {
	
	private double[] a;
	 public Sorter(double[]myArray) {
		// TODO Auto-generated constructor stub
		a= myArray;
		sort();
	}
	 public Sorter(ArrayList<Double>scoreList) {
		 a  = generateArray(scoreList);
		 sort();
	 }
	 
	 private double[] generateArray(ArrayList<Double>input) {//自己產生要排序的sorter
		 int times = input.size();
		 double [] myArray = new double [times];
		 for(int i =0 ; i<times;i++) {
			 myArray[i] = input.get(i);
		 }
		return myArray;
	 }
	 
	 private void sort() {
		 int from = 0;
		 int to = a.length - 1;
		 sort(from, to);
	 }
	 
	public void sort(int from, int to) {		
	 if (from >= to)
		return;
		int p = partition(from, to);
		sort(from,p);
		sort(p+1,to);
	//recursion call sort() for divide 
	}

	private int partition(int from, int to)
	{ 	
		double pivot = a[from]; 
		int i = from - 1;
		int j = to + 1; 
	while (i < j)
	{
	      i++;
	      while(a[i]<pivot)
	    	  i++;
	      j--;
	      while(a[j]>pivot)
	    	  j--;if(i<j){
	    		  swap(i,j);
	    	    }
	    	  }
	    		return j;// return new pivot
	} 

	    private void swap(int i, int j){
	    	double temp = a[i];
	    	a[i] = a[j];
	    	a[j] = temp;
			}
	    
	    public void printResult() {
	    	for(int i = 0 ; i<a.length;i++) {
	    		System.out.print(a[i] + " ");
	    	}
	    }
	    
	    public static void main(String[]args) {
	    	ArrayList<Double>tester = new ArrayList<Double>();
	    	for(int i = 0 ; i<10 ;i++) {
	    		double r = Math.random()*100;
	    		tester.add(r);
	    	}
	    	Sorter sorter = new Sorter(tester);
	    	sorter.printResult();
		
		}
	  } 

		

