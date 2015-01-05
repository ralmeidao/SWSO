package br.edu.ifba.swso.algorithms.impl.disk;

import br.edu.ifba.swso.algorithms.interfaces.IDiskScheduler;
import br.edu.ifba.swso.util.Constantes;

public class SSTF implements IDiskScheduler {

	@Override
	public int[] reordenaLista(int[] queue, int initialCylinder) {
		int[] resultPath = new int[queue.length];
		int now = initialCylinder;
		int[] requests = new int[queue.length];
		int[] queueAux = new int[queue.length];//new
		
		for (int i = 0; i < queue.length; i++) {
			requests[i] = getCylinder(queue[i]); //requests[i] = queue[i];
			queueAux[i] = getCylinder(queue[i]); //new
		}
		for (int i = 0; i < resultPath.length; i++) {
			int closest = closest(now, requests, queue, queueAux);//int closest = closest(now, requests, queue);
			resultPath[i] = closest;
			now = getCylinder(closest);
		}
		return resultPath;
	}

	private int closest(int now, int[] requests, int[] queue, int[] queueAux) {//private int closest(int k, int[] requests, int[] queue) {
		int min = 5000000;
		int minPos = -1;
		
		for (int i = 0; i < requests.length; i++) {
			if (requests[i] == -1)
				continue;
			else if (Math.abs(now - queueAux[i]) < min) {
				minPos = i;
				min = Math.abs(now - queueAux[i]);//min = Math.abs(k - queueAux[i++]);
			}
		}
		int nearestSector = queue[minPos];//int nearestCylinder = requests[minPos];
		requests[minPos] = -1;
		return nearestSector;
	}
	
	private int getCylinder(int sector) {
		return (sector % (Constantes.TRACK_SIZE * Constantes.PLATE_SIZE))/Constantes.TRACK_SIZE;
	}

}