package br.edu.ifba.swso.algorithms.impl.disk;

import br.edu.ifba.swso.algorithms.interfaces.IDiskScheduler;

public class Main {

	static int[] array = { 98, 183, 37, 122, 14, 124, 65, 67 };//{2, 3, 1, 2, 2, 0, 1, 3};
						 // 2,   3,  1,   2,  2,   0,  1,  3
	// 2 2 2 3 3 1 1 0
	
	
	//98, 122, 14, 183, 67, 37, 65, 124
	
	
	public static void main(String[] args) {
		int[] resultArray; 
		IDiskScheduler algo = new SSTF();
		resultArray = algo.reordenaLista(array, 2);

		System.out.println("SSTF");
		for (int i : resultArray) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		algo = new FCFS();
		resultArray = algo.reordenaLista(array, 53);

		System.out.println("FCFS");
		for (int i : resultArray) {
			System.out.print(i + " ");
		}
	}

}
