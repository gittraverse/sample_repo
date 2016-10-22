import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Composition{
	public static void main(String[]args){
		Scanner in = null;
		try {
			in = new Scanner(new FileReader("rosalind_ba3a.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		compose(in.nextInt(),in.next());
	}
	public static void compose(int k,String dna)
	{
		for(int i =0; i <= dna.length() - k; i ++)
		{
			System.out.println(dna.substring(i,i+k));
		}
	}
}