import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class MinSkew{
	public static void main(String args[]){
		ArrayList<Integer> sk = new ArrayList<Integer>();
		sk.add(0);
		Scanner in = null;
		try {
			in = new Scanner(new FileReader("rosalind_ba1f (1).txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text = in.next();
		sk = skew(sk,text,0);
		findMin(sk,0);
	}
	public static ArrayList<Integer> 
	skew(ArrayList<Integer> res,String text,int count)
	{
		for(int i = 0; i < text.length(); i ++){
		
		if(text.charAt(i)== 'C')
			count --;
		if(text.charAt(i)== 'G')
			count ++;
		res.add(count);
		}
		return res;
	}
	public static void findMin(ArrayList<Integer> sk,int curMin){
		for(int i = 0; i < sk.size(); i ++){
			if (sk.get(i) < curMin)
				curMin = sk.get(i);
		}
		for(int j = 0; j < sk.size(); j ++){
			if (sk.get(j) == curMin)
				System.out.println(j + " ");
		}
	}
}