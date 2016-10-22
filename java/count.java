import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class count{
	public static void main(String[]args){
		Scanner in = null;
		try {
			in = new Scanner(new FileReader("rosalind_dna.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count(in.next(), 0,0,0,0);
	}
	public static void count(String s, int a, int c, int g, int t){
		if(s.length() == 0)
			System.out.println(a + " " + c + " " + g + " " + t + " ");
		else{
			switch(s.charAt(0)){
			case 'A': a++;break;
			case 'C': c++;break;
			case 'G': g++;break;
			case 'T': t++;break;
			}
			count(s.substring(1,s.length()),a,c,g,t);
		}
	}
	
}