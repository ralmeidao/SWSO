package br.edu.ifba.swso.algorithms;

import br.edu.ifba.swso.business.so.memorymanager.PageTable;
import br.edu.ifba.swso.business.so.memorymanager.RealMemory;

public interface IPageReplacementAlgorithm {
	int findPageToReplace(RealMemory memory, Process process, PageTable pageTable);
}
