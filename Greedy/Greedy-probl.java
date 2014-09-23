import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Greedy-probl {
	private static int Dim[];
	private static int N;
	private static int L;
	private static int M;
	
	public static void readData() {
		try {
			File f = new File("date.in");
			Scanner sc = new Scanner(f);
			N = sc.nextInt();
			M = sc.nextInt();
			L = sc.nextInt();
			Dim = new int[N];
			for (int i = 0; i < N; i++) {
				Dim[i] = sc.nextInt();
			}
			sc.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printData(int min, ArrayList D) {
		try {
			PrintWriter pw = new PrintWriter("date.out");
			pw.print(min + "\n");
			pw.print(D.size() + "\n");
			for(int i = 0; i < D.size(); i++) {
				pw.print(D.get(i) + "\n");
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public static int nr_stalpi_elim(int min_dist) {
		int count = 0;
		//stalp_ref este ultimul stalp adaugat in noul gard
		int stalp_ref = 0;
		/*se contorizeaza ca stalpi eliminati stalpii care
		au distanta fata de stalpul de referinta >=
		decat distanta maxima ce poate exista intre 2 stalpi */
		for(int i = 1; i < N; i++) {
			if (Dim[i] - Dim[stalp_ref] >= min_dist) {
				stalp_ref = i;
			} else {
				count++;
			}
		}
		return count;
	}
	
	public static void elim_stalpi(int min_dist) {
		ArrayList<Integer> new_dist = new ArrayList<Integer>();
		int min;
		int stalp_ref = 0;
		
		//se adauga primul stalp (nu poate fi sters din gard)
		new_dist.add(0);
		min = Integer.MAX_VALUE;
		int d;
		/*se elimina stalpi pana cand distanta intre ultimul stalp
		adaugat si stalpul curent devine >= cu distanta minima 
		gasita*/
		for(int i = 1; i < Dim.length - 1; i++) {
			d = Dim[i] - Dim[stalp_ref];
			if(d >= min_dist) {
				stalp_ref = i;
				new_dist.add(Dim[i]);
				if(d < min) min = d;
			}
		}
		/*se verifica si ultima distanta pentru calcularea distantei 
		minime deoarece for-ul se opreste inainte*/
		d = Dim[Dim.length - 1] - Dim[stalp_ref];
		if(d < min)	min = d;
		//se adauga ultimul stalp (nu poate fi sters din gard)
		new_dist.add(Dim[N - 1]);
		printData(min, new_dist);
	}
	
	public static void strica_gard() {
		int left, right, mid;
		/*folosim Binary Search pentru a gasi distanta
		minima optima care poate exista intre stalpi*/
		left = 0;
		right = L;
		while(left <= right) {
			mid = left + (right - left)/2;
			/*daca nr de stalpi gasiti se incadreaza in
			cerintele problemei */
			if(nr_stalpi_elim(mid) <= M) {
				/*in cazul in care distanta maximizata cu 1
				nu se mai incadreaza in cerintele problemei
				inseamna ca rezultatul ales este cel corect*/
				if(nr_stalpi_elim(mid + 1) > M) {
					elim_stalpi(mid);
					break;
				} else {
					left = mid;
				}
			} else {
				right = mid;
			}
		}
	}
	
	public static void main(String[] argv) {
		readData();
		strica_gard();
	}
}
