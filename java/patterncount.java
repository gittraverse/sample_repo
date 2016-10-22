import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class patterncount{
	public static void main(String args[]){
		Scanner in = null;
		try {
			in = new Scanner(new FileReader("rosalind_ba1a (1).txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text = in.next();
		String pattern = in.next();
		pc(text,pattern);
	}
	public static int pc(String text,String pattern)
	{
		int count = 0;
		for (int i = 0; i < text.length()-pattern.length();i ++)
			if(text.substring(i, i + pattern.length()).equals(pattern))
				count++;
		System.out.println(count);
		return count;
	}
}