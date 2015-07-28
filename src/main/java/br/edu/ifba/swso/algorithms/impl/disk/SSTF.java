package br.edu.ifba.swso.algorithms.impl.disk;

import br.edu.ifba.swso.algorithms.interfaces.IDiskScheduler;
import br.edu.ifba.swso.util.Constantes;

/**
 * @author Ramon
 *
 */
public class SSTF implements IDiskScheduler {

	private final String nome = "SSTF";
	
	@Override
	public int[] escalonar(int[] queue, int initialCylinder) {
		int[] resultPath = new int[queue.length];
		int now = initialCylinder;
		int[] requests = new int[queue.length];
		int[] queueAux = new int[queue.length];
		
		for (int i = 0; i < queue.length; i++) {
			requests[i] = getCylinder(queue[i]);
			queueAux[i] = getCylinder(queue[i]);
		}
		for (int i = 0; i < resultPath.length; i++) {
			int closest = closest(now, requests, queue, queueAux);
			resultPath[i] = closest;
			now = getCylinder(closest);
		}
		return resultPath;
	}
	
	private int closest(int now, int[] requests, int[] queue, int[] queueAux) {
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

	
	@Override
	public String toString() {
		return nome;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SSTF other = (SSTF) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}