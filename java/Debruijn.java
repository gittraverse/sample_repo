import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;




public class Debruijn{
	public static void main(String[] args)
	{
		Scanner in = null;
		try {
			in = new Scanner(new FileReader("rosalind_ba1f.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		graphD(in.nextInt(),in.next());
	}
	public static void graphD(int k, String dna)
	{
		Map<String,Set<String>> m1 = new HashMap<String,Set<String>>(); 
		String curK;
		String curV;
	//	Set<String> s;
		for(int i = 0; i <= dna.length()-k; i++)
		{
			//set for every node, reset every loop
			Set<String> s = new HashSet<String>();
			//getting current key and value
			curK = dna.substring(i,i+k-1);
			curV = dna.substring(i+1,i+k);
			//add curv to a new set to be key of curk
			s.add(curV);
			//if key exists already, retrive list
			if(m1.containsKey(curK)) 
			{
				//retrieve list from what key points to currently, 
				//then somehow find duplicates
				s = m1.get(curK);
				s.add(curV);
			}
			Set<String> sorts = new TreeSet<String>(s); 
			//add relation back to map
			m1.put(curK, sorts);
		}
		//print in specific way

		Map<String, Set<String>> mapS = new TreeMap<String,Set<String>>(m1);
		Set<String> keys = mapS.keySet();
		Iterator<String> keyIter = keys.iterator();
		while(keyIter.hasNext())
		{
			curK = keyIter.next();
			System.out.print(curK + " -> ");
			Set<String> s = m1.get(curK);
			TreeSet<String> t = new TreeSet<String>(s);
			Iterator<String> valIter = t.iterator();
			while(valIter.hasNext()){
				System.out.print(valIter.next());
				if(valIter.hasNext())
					System.out.print(",");
			}
			System.out.println();	
		}
	}
}