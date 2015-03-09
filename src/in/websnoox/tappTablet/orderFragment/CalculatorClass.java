package in.websnoox.tappTablet.orderFragment;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class CalculatorClass {


	public double calculation(String str){
		double res=0;
		ArrayList<String> newlist = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str, "+-x/", true);
		while(st.hasMoreTokens()){
			String aa =st.nextToken();
			newlist.add(aa);
		}

		for(int i=0;i<newlist.size();i++){

			if(newlist.get(i).equals("/")){
				res =Double.parseDouble(newlist.get(i-1))/ Double.parseDouble(newlist.get(i+1));

				newlist.remove(i-1);
				newlist.remove(i-1);
				newlist.remove(i-1);
				newlist.add(i-1, res+""); 
				i--;
			}
		}

		for(int i=0;i<newlist.size();i++){

			if(newlist.get(i).equals("x")){
				res =Double.parseDouble(newlist.get(i-1))* Double.parseDouble(newlist.get(i+1));

				newlist.remove(i-1);
				newlist.remove(i-1);
				newlist.remove(i-1);
				newlist.add(i-1, res+""); 
				i--;
			}
		}

		for(int i=0;i<newlist.size();i++){

			if(newlist.get(i).equals("-")){
				res =Double.parseDouble(newlist.get(i-1))- Double.parseDouble(newlist.get(i+1));

				newlist.remove(i-1);
				newlist.remove(i-1);
				newlist.remove(i-1);
				newlist.add(i-1, res+""); 
				i--;
			}
		}

		for(int i=0;i<newlist.size();i++){

			if(newlist.get(i).equals("+")){
				res =Double.parseDouble(newlist.get(i-1))+ Double.parseDouble(newlist.get(i+1));

				newlist.remove(i-1);
				newlist.remove(i-1);
				newlist.remove(i-1);
				newlist.add(i-1, res+""); 
				i--;
			}
		}
		return Double.parseDouble(newlist.get(0));

	}
}

