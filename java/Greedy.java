import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthOptionPaneUI;
public class Greedy{
	public static void main(String[] args){
		Scanner in = null;
		try {
			in = new Scanner(new FileReader("rosalind_ba2e (1).txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int k = Integer.parseInt(in.next());
		int t = Integer.parseInt(in.next());
		ArrayList<String> dna = new ArrayList<String>();
		for(int i = 0;i < t; i++)
			dna.add(in.next());
		ArrayList<String> bestMotifs = new ArrayList<String>();
		for(int i = 0;i < dna.size(); i++)
			bestMotifs.add(dna.get(i).substring(0,k));
		double[][] matrix1 = matricize(bestMotifs);
//		System.out.println(matrix1.length);
//		System.out.println(matrix1[0].length);
//		for(int i = 0; i < matrix1.length; i ++){
//			System.out.print(i+ " ");
//			for (int j = 0; j < matrix1[0].length; j ++)
//				System.out.print(j + ":" + matrix1[i][j]+ " ");
//			System.out.println();
//		}
		int bestScore = -1;
		for(int a = 0; a <= dna.get(0).length() - k; a++)
		{
			//length t
			ArrayList<String> motifs = new ArrayList<String>();
			motifs.add(dna.get(0).substring(a, a+k));
			for(int i = 1; i < t; i++) {
	         	ArrayList<String> prevMotifs = new ArrayList<String>();
	            for(int x = 0; x < i; ++x) {
	                prevMotifs.add(motifs.get(x));
	            }
	            double[][] profile = matricize(prevMotifs);
	            motifs.add(probable(dna.get(i),k,profile));
	        }
			int currScore = scoreMotifsWithPseudocounts(motifs);
			if(currScore > bestScore) {
				bestScore = currScore;
	            bestMotifs = motifs;
            }
		}
		for(int i = 0; i < bestMotifs.size(); i++)
			System.out.println(bestMotifs.get(i));
		
	}
	public static double[][] matricize(ArrayList<String> motifs){
		//System.out.println("motif aL" + motifs);
		double [][] returning = new double[4][motifs.get(0).length()];
		//System.out.println(returning.length + " " + returning[0].length);
		char curChar;
		int a;
		int c;
		int g;
		int t;
		//for each index
		for(int i = 0; i < motifs.get(0).length(); i++){
			a=1;
			c=1;
			g=1;
			t=1;
			//traverse whole list every time	
			for(int j = 0; j < motifs.size(); j++)
			{	
				curChar = motifs.get(j).charAt(i);
				switch(curChar)
				{
				case 'A': a++; break;
				case 'C': c++;break;
				case 'G': g++;break;
				case 'T': t++;break;
				}
			}
//			System.out.println(motifs);
			double divBy = motifs.size() +4;
			//System.out.println("a" + a + "c" + c + "g" + g + "t" + t);
			returning[0][i] = a/divBy;
			returning[1][i] = c/divBy;
			returning[2][i] = g/divBy;
			returning[3][i] = t/divBy;
		}
		return returning;
	}
	public static String probable(String curDna, int k, double[][] profile)
	{
		String best = "";
		double max = 0.0;
		for(int i = 0; i < curDna.length() - k + 1; i++)
		{
			String kmer = curDna.substring(i, i+k);
			double prob = 1.0;
			for(int j =0; j < kmer.length(); j++)
				prob = prob * profile[charToInt(kmer.charAt(j))][j];
			if(prob > max){
				best = kmer;
				max = prob;
			}
		}
		return best;
	}
	public static int charToInt( char n )
	{
		int ret = 0;
		switch(n){
		case 'A': ret = 0; break;
		case 'C': ret = 1; break;
		case 'G': ret = 2; break;
		case 'T': ret = 3; break;
		}
		return ret;
    }
	public static int scoreMotifsWithPseudocounts(ArrayList<String> motifs ) {
        double[][] profile = matricize(motifs);
        String consensus = "";
        for(int i = 0; i < motifs.get(0).length(); i++) {
        	double maxProb = -1;
        	char maxChar = '\0';
	        for(int j = 0; j < 4; j++) {
	        	if(profile[j][i] > maxProb) {
	        		maxProb = profile[j][i];
	        		maxChar = intToChar(j);
	             }
	        }	
	        consensus += maxChar;
	    }
	        
        int score = 0;
        for(int i = 0; i < consensus.length(); i++) {
            char c = consensus.charAt(i);
            for(int j = 0; j < motifs.size(); j++) {
                if(motifs.get(j).charAt(i) == c) 
                    score++;
            }
        }
        return score;
    }
	 public static char intToChar( int i ) {
		 char ret = 'A';
		 switch(i){
		 case 0: ret = 'A';break;
		 case 1: ret = 'C';break;
		 case 2: ret = 'G';break;
		 case 3: ret = 'T';break;
		 }
		 return ret;  
	 }
}	