package poarquivo;

import Classes.Arquivo;
import java.io.FileWriter;
import java.io.PrintWriter;

public class POArquivo {

    Arquivo arqOrd, arqRev, arqRand, auxRev, auxRand;
    int Tamanho = 512;
    
    public void geraTabela()
    {
        Long tempoInicial, tempoFinal, tempoTotalOrd, tempoTotalRev, tempoTotalRand;
        int compOrd, movOrd, compRev, movRev, compRand, movRand;
        
        arqOrd = new Arquivo("Arquivo_Ordenado.dat");
        arqOrd.geraArquivoOrdenado(Tamanho);
        System.out.println("Arquivo ordenado gerado");
        arqOrd.exibirArq();
        
        arqRev = new Arquivo("Arquivo_Reverso.dat");
        arqRev.geraArquivoReverso(Tamanho);
        System.out.println("Arquivo reverso gerado");
        arqRev.exibirArq();
        
        arqRand = new Arquivo("Arquivo_Randomico.dat");
        arqRand.geraArquivoRandomico(Tamanho);       
        System.out.println("Arquivo randomico gerado");
        arqRand.exibirArq();
        System.out.println("\n");
        
        auxRev = new Arquivo("Arquivo_ReversoAux.dat");   
        auxRev.truncate(Tamanho);
        auxRand = new Arquivo("Arquivo_RandomicoAux.dat");
        auxRand.truncate(Tamanho);
        
        
        String linha;
        linha ="____________________________________________________________________________________________________________________________________________________________________";
        linha+="\n|Métodos de Ordenação | Arquivo Ordenado                            || Arquivo em Ordem Reversa                    || Arquivo Randômico                             |";
        linha+="\n|                     | Comp.  | Comp.  |  Mov.  |  Mov.  |  TEMPO  || Comp.  | Comp.  |  Mov.  |  Mov.  |  TEMPO  || Comp.  | Comp.  |  Mov.  |  Mov.  |  TEMPO    |";
        linha+="\n|                     | Prog.  | Equa.  |  Prog. |  Equa. |  SEG.   || Prog.  | Equa.  |  Prog. |  Equa. |  SEG.   || Prog.  | Equa.  |  Prog. |  Equa. |  SEG.     |";
        //linha+="\n----------------------------------------------------------------------------------------------------------------------------------------------------------------";
        linha+="\n|___________________________________________________________________________________________________________________________________________________________________|";
        //linha+="\n----------------------------------------------------------------------------------------------------------------------------------------------------------------";
        
        gravarLinha(linha);
        //---------------------------------InsercaoDireta---------------------------------
        linha="\n|Inserção Direta      |  ";
        System.out.println("Insercao Direta");
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();        
        tempoInicial = System.currentTimeMillis();
        arqOrd.insercaoDireta();
        tempoFinal = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        tempoTotalOrd = tempoFinal-tempoInicial;
        tempoTotalOrd = tempoTotalOrd/1000;     
        linha += compOrd + "  |  " + (int)calcComparacoes(0) + "  |  " + movOrd + "  |  " + (int)calcMovimentacoes(0) + "  |  " + tempoTotalOrd;
        System.out.println("Ordenado");
        arqOrd.exibirArq();
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRev.insercaoDireta();
        tempoFinal = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();      
        tempoTotalRev = tempoFinal-tempoInicial;
        tempoTotalRev = tempoTotalRev/1000;  
        linha += "  || " + compRev + " | " + (int)calcComparacoes(2) + " | " + movRev + " | " + (int)calcMovimentacoes(1) + " | " + tempoTotalRev;
        System.out.println("Reverso");
        auxRev.exibirArq();
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRand.insercaoDireta();
        tempoFinal = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();        
        tempoTotalRand = tempoFinal-tempoInicial;
        tempoTotalRand = tempoTotalRand/1000;  
        linha += "  || " + compRand + " | " + (int)calcComparacoes(1) + " | " + movRand + " | " + (int)calcMovimentacoes(2) + " |  " + tempoTotalRand+"    |";
        linha+="\n|___________________________________________________________________________________________________________________________________________________________________|";
        gravarLinha(linha);
        System.out.println("Randomico");
        auxRand.exibirArq();
        System.out.println("Insercao Direta finalizado");
        
        //---------------------------------InsercaoBinaria---------------------------------
        System.out.println("_____________________________________________________________________________________________________________________________________________________");
        linha="\n|Inserção Binaria     | ";
        System.out.println("Insercao Binaria");
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();        
        tempoInicial = System.currentTimeMillis();
        arqOrd.insercaoBinaria();
        tempoFinal = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        tempoTotalOrd = tempoFinal-tempoInicial;
        tempoTotalOrd = tempoTotalOrd/1000;  
        linha += compOrd + "  |  " + (int)calcComparacoes(4) + "  |  " + movOrd + "  |  " + (int)calcMovimentacoes(0) + "  |  " + tempoTotalOrd;
        System.out.println("Ordenado");
        arqOrd.exibirArq();
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRev.insercaoBinaria();
        tempoFinal = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();      
        tempoTotalRev = tempoFinal-tempoInicial;
        tempoTotalRev = tempoTotalRev/1000;  
        linha += "  ||  " + compRev + "  |  " + (int)calcComparacoes(4) + "  | " + movRev + " | " + (int)calcMovimentacoes(1) + " | " + tempoTotalRev;
        System.out.println("Reverso");
        auxRev.exibirArq();
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRand.insercaoBinaria();
        tempoFinal = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();        
        tempoTotalRand = tempoFinal-tempoInicial;
        tempoTotalRand = tempoTotalRand/1000;  
        linha += "  || " + compRand + "  |  " + (int)calcComparacoes(4) + "  | " + movRand + " | " + (int)calcMovimentacoes(2) + " |  " + tempoTotalRand+"    |";
        linha+="\n|___________________________________________________________________________________________________________________________________________________________________|";
        gravarLinha(linha);
        System.out.println("Randomico");
        auxRand.exibirArq();
        System.out.println("Insercao Binaria finalizado");
        
        //---------------------------------SelecaoDireta--------------------------------
        System.out.println("_____________________________________________________________________________________________________________________________________________________");
        linha="\n|Selecao Direta       | ";
        System.out.println("Selecao Direta");
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();        
        tempoInicial = System.currentTimeMillis();
        arqOrd.selecaoDireta();
        tempoFinal = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        tempoTotalOrd = tempoFinal-tempoInicial;
        tempoTotalOrd = tempoTotalOrd/1000;
        linha += compOrd + " | " + (int)calcComparacoes(3) + " |  " + movOrd + "  |  " + (int)calcMovimentacoes(0) + "  |  " + tempoTotalOrd;
        System.out.println("Ordenado");
        arqOrd.exibirArq();
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRev.selecaoDireta();
        tempoFinal = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();      
        tempoTotalRev = tempoFinal-tempoInicial;
        tempoTotalRev = tempoTotalRev/1000;  
        linha += "  || " + compRev + " | " + (int)calcComparacoes(3) + " |  " + movRev + "  | " + (int)calcMovimentacoes(3) + " |  " +tempoTotalRev;
        System.out.println("Reverso");
        auxRev.exibirArq();        
        //Arquivo Randomico        
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRand.selecaoDireta();
        tempoFinal = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();        
        tempoTotalRand = tempoFinal-tempoInicial;
        tempoTotalRand = tempoTotalRand/1000;  
        linha += "  || " + compRand + " | " + (int)calcComparacoes(3) + " |  " + movRand + "  |  " + (int)calcMovimentacoes(4) + "  |  " + tempoTotalRand+"    |";
        linha+="\n|___________________________________________________________________________________________________________________________________________________________________|";
        gravarLinha(linha);
        System.out.println("Randomico");
        auxRand.exibirArq();        
        System.out.println("Selecao Direta finalizado");
        
        //---------------------------------BubbleSort---------------------------------
        System.out.println("_____________________________________________________________________________________________________________________________________________________");
        linha="\n|Bubble Sort          | ";
        System.out.println("Bubble Sort");
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();        
        tempoInicial = System.currentTimeMillis();
        arqOrd.bubbleSort();
        tempoFinal = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        tempoTotalOrd = tempoFinal-tempoInicial;
        tempoTotalOrd = tempoTotalOrd/1000;  
        linha += compOrd + " | " + (int)calcComparacoes(3) + " |   " + movOrd + "    |    " + (int)calcMovimentacoes(7) + "   |  " + tempoTotalOrd;
        System.out.println("Ordenado");
        arqOrd.exibirArq(); 
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRev.bubbleSort();
        tempoFinal = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();      
        tempoTotalRev = tempoFinal-tempoInicial;
        tempoTotalRev = tempoTotalRev/1000;  
        linha += "  || " + compRev + " | " + (int)calcComparacoes(3) + " | " + movRev + "| " + (int)calcMovimentacoes(6) + " |  " + tempoTotalRev;
        System.out.println("Reverso");
        auxRev.exibirArq(); 
        //Arquivo Randomico       
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRand.bubbleSort();
        tempoFinal = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();        
        tempoTotalRand = tempoFinal-tempoInicial;
        tempoTotalRand = tempoTotalRand/1000;  
        linha += " || " + compRand + " | " + (int)calcComparacoes(3) + " | " + movRand + " | " + (int)calcMovimentacoes(5) + "|  " + tempoTotalRand+"   |";
        linha+="\n|___________________________________________________________________________________________________________________________________________________________________|";
        gravarLinha(linha);           
        System.out.println("Randomico");
        auxRand.exibirArq();
        System.out.println("Bubble Sort finalizado");
        
        //---------------------------------ShakeSort---------------------------------
        System.out.println("______________________________________________________________________________________________________________________________________________________");
        linha="\n|Shake Stort          | ";
        System.out.println("Shake Sort");
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();        
        tempoInicial = System.currentTimeMillis();
        arqOrd.shakeSort();
        tempoFinal = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        tempoTotalOrd = tempoFinal-tempoInicial;
        tempoTotalOrd = tempoTotalOrd/1000;  
        linha += compOrd + " | " + (int)calcComparacoes(3) + " |   " + movOrd + "    |    " + (int)calcMovimentacoes(7) + "   |  " + tempoTotalOrd;
        System.out.println("Ordenado");
        arqOrd.exibirArq();   
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRev.shakeSort();
        tempoFinal = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();      
        tempoTotalRev = tempoFinal-tempoInicial;
        tempoTotalRev = tempoTotalRev/1000;  
        linha += " || " + compRev + " | " + (int)calcComparacoes(3) + " | " + movRev + "| " + (int)calcMovimentacoes(6) + " |  " + tempoTotalRev;
        System.out.println("Reverso");
        auxRev.exibirArq();
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRand.shakeSort();
        tempoFinal = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();        
        tempoTotalRand = tempoFinal-tempoInicial;
        tempoTotalRand = tempoTotalRand/1000;  
        linha += " || " + compRand + " | " + (int)calcComparacoes(3) + " | " + movRand + " | " + (int)calcMovimentacoes(5) + "|  " + tempoTotalRand+"   |";
        linha+="\n|___________________________________________________________________________________________________________________________________________________________________|";
        gravarLinha(linha);   
        System.out.println("Randomico");
        auxRand.exibirArq();
        System.out.println("Shake Sort finalizado");
        
        //---------------------------------ShellSort---------------------------------
        System.out.println("_____________________________________________________________________________________________________________________________________________________");
        linha="\n|Shell Sort           |  ";
        System.out.println("Shell Sort");
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();        
        tempoInicial = System.currentTimeMillis();
        arqOrd.shellSort();
        tempoFinal = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        tempoTotalOrd = tempoFinal-tempoInicial;
        tempoTotalOrd = tempoTotalOrd/1000;  
        linha += compOrd + "  |    " + "X" + "   |   " + movOrd + "    |    " + "X" + "   |  " + tempoTotalOrd;
        System.out.println("Ordenado");
        arqOrd.exibirArq();
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRev.shellSort();
        tempoFinal = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();      
        tempoTotalRev = tempoFinal-tempoInicial;
        tempoTotalRev = tempoTotalRev/1000;  
        linha += "  || " + compRev + " |   " + "X" + "    | " + movRev + " |   " + "X" + "    |  " + tempoTotalRev;        
        System.out.println("Reverso");
        auxRev.exibirArq();
        //Arquivo Randomico   
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRand.shellSort();
        tempoFinal = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();        
        tempoTotalRand = tempoFinal-tempoInicial;
        tempoTotalRand = tempoTotalRand/1000;  
        linha += " || " + compRand + "  |   " + "X" + "    | " + movRand + " |   " + "X" + "    |  " + tempoTotalRand+"    |";
        linha+="\n|___________________________________________________________________________________________________________________________________________________________________|";
        gravarLinha(linha);        
        System.out.println("Randomico");
        auxRand.exibirArq();
        System.out.println("Shell Sort finalizado");
        
        //---------------------------------HeapSort---------------------------------
        System.out.println("_____________________________________________________________________________________________________________________________________________________");
        linha="\n|Heap Sort            | ";
        System.out.println("Heap Sort");
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();        
        tempoInicial = System.currentTimeMillis();
        arqOrd.heapSort();
        tempoFinal = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        tempoTotalOrd = tempoFinal-tempoInicial;
        tempoTotalOrd = tempoTotalOrd/1000;   
        linha += compOrd + " |    " + "X" + "   | " + movOrd + "  |    " + "X" + "   | " + tempoTotalOrd;  
        System.out.println("Ordenado");
        arqOrd.exibirArq();
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRev.heapSort();
        tempoFinal = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();      
        tempoTotalRev = tempoFinal-tempoInicial;
        tempoTotalRev = tempoTotalRev/1000;  
        linha += "  || " + compRev + " |   " + "X" + "    | " + movRev + "  |   " + "X" + "    |  " + tempoTotalRev; 
        System.out.println("Reverso");
        auxRev.exibirArq();
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRand.heapSort();
        tempoFinal = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();        
        tempoTotalRand = tempoFinal-tempoInicial;
        tempoTotalRand = tempoTotalRand/1000;  
        linha += " || " + compRand + " |   " + "X" + "    | " + movRand + "  |   " + "X" + "    |  " + tempoTotalRand+"   |";
        linha+="\n|___________________________________________________________________________________________________________________________________________________________________|";
        gravarLinha(linha);   
        System.out.println("Randomico");
        auxRand.exibirArq();
        System.out.println("Heap Sort finalizado");
        
        //---------------------------------QuickSort---------------------------------
        System.out.println("_____________________________________________________________________________________________________________________________________________________");
        linha="\n|Quick Sort           | ";
        System.out.println("Quick Sort");
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();        
        tempoInicial = System.currentTimeMillis();
        arqOrd.quickSort(0, Tamanho-1);
        tempoFinal = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        tempoTotalOrd = tempoFinal-tempoInicial;
        tempoTotalOrd = tempoTotalOrd/1000;  
        linha += compOrd + " |    " + "X" + "   |  " + movOrd + "  |    " + "X" + "   |  " + tempoTotalOrd;     
        System.out.println("Ordenado");
        arqOrd.exibirArq();
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRev.quickSort(0, Tamanho-1);
        tempoFinal = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();      
        tempoTotalRev = tempoFinal-tempoInicial;
        tempoTotalRev = tempoTotalRev/1000;  
        linha += "  || " + compRev + " |   " + "X" + "    |  " + movRev + "  |   " + "X" + "    |  " + tempoTotalRev; 
        System.out.println("Reverso");
        auxRev.exibirArq();
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRand.quickSort(0, Tamanho-1);
        tempoFinal = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();        
        tempoTotalRand = tempoFinal-tempoInicial;
        tempoTotalRand = tempoTotalRand/1000;  
        linha += "  || " + compRand + "  |   " + "X" + "    |  " + movRand + "  |   " + "X" + "    |  " + tempoTotalRand+"    |";
        linha+="\n|___________________________________________________________________________________________________________________________________________________________________|";
        gravarLinha(linha);    
        System.out.println("Randomico");
        auxRand.exibirArq();
        System.out.println("Quick Sort finalizado");
        
        //---------------------------------QuickSortPivo---------------------------------
        System.out.println("_____________________________________________________________________________________________________________________________________________________");
        linha="\n|Quick Sort Pivo      |  ";
        System.out.println("Quick Pivo Sort");
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();        
        tempoInicial = System.currentTimeMillis();
        arqOrd.quickSortPivo(0, Tamanho-1);
        tempoFinal = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        tempoTotalOrd = tempoFinal-tempoInicial;
        tempoTotalOrd = tempoTotalOrd/1000;  
        linha += compOrd + "  |    " + "X" + "   |  " + movOrd + "  |    " + "X" + "   |  " + tempoTotalOrd;       
        System.out.println("Ordenado");
        arqOrd.exibirArq();
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRev.quickSortPivo(0, Tamanho-1);
        tempoFinal = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();      
        tempoTotalRev = tempoFinal-tempoInicial;
        tempoTotalRev = tempoTotalRev/1000;  
        linha += "  || " + compRev + "   |   " + "X" + "    |  " + movRev + "  |   " + "X" + "    |  " + tempoTotalRev; 
        System.out.println("Reverso");
        auxRev.exibirArq();
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRand.quickSortPivo(0, Tamanho-1);
        tempoFinal = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();        
        tempoTotalRand = tempoFinal-tempoInicial;
        tempoTotalRand = tempoTotalRand/1000;  
        linha += "  || " + compRand + "  |   " + "X" + "    |  " + movRand + "  |   " + "X" + "    |  " + tempoTotalRand+"    |";
        linha+="\n|___________________________________________________________________________________________________________________________________________________________________|";
        gravarLinha(linha); 
        System.out.println("Randomico");
        auxRand.exibirArq();
        System.out.println("Quick Pivo Sort finalizado");
   
        //---------------------------------MergeSort1---------------------------------
        System.out.println("_____________________________________________________________________________________________________________________________________________________");
        linha="\n|Merge Sort 1         |  ";
        System.out.println("Merge Sort 1");
        arqOrd.initComp();
        arqOrd.initMov();        
        tempoInicial = System.currentTimeMillis();
        arqOrd.mergeSort();
        tempoFinal = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        tempoTotalOrd = tempoFinal-tempoInicial;
        tempoTotalOrd = tempoTotalOrd/1000;  
        linha += compOrd + "  |   " + "X" + "    | " + movOrd + "  |   " + "X" + "    |  " + tempoTotalOrd;      
        System.out.println("Ordenado");
        arqOrd.exibirArq();
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRev.mergeSort();
        tempoFinal = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();      
        tempoTotalRev = tempoFinal-tempoInicial;
        tempoTotalRev = tempoTotalRev/1000;  
        linha += "  ||  " + compRev + "  |   " + "X" + "    | " + movRev + "  |   " + "X" + "    |  " + tempoTotalRev; 
        System.out.println("Reverso");
        auxRev.exibirArq();
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRand.mergeSort();
        tempoFinal = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();        
        tempoTotalRand = tempoFinal-tempoInicial;
        tempoTotalRand = tempoTotalRand/1000;  
        linha += "  ||  " + compRand + "  |   " + "X" + "    | " + movRand + "  |   " + "X" + "    |   " + tempoTotalRand+"   |";
        linha+="\n|___________________________________________________________________________________________________________________________________________________________________|";
        gravarLinha(linha); 
        System.out.println("Randomico");
        auxRand.exibirArq();
        System.out.println("Merge Sort 1 finalizado");
        
        //---------------------------------MergeSort2---------------------------------
        System.out.println("_____________________________________________________________________________________________________________________________________________________");
        linha="\n|Merge Sort 2         |  ";
        System.out.println("Merge Sort 2");
        arqOrd.initComp();
        arqOrd.initMov();        
        tempoInicial = System.currentTimeMillis();
        arqOrd.mergeSort2();
        tempoFinal = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        tempoTotalOrd = tempoFinal-tempoInicial;
        tempoTotalOrd = tempoTotalOrd/1000;   
        linha += compOrd + "  |   " + "X" + "    | " + movOrd + "  |   " + "X" + "    |  " + tempoTotalOrd;   
        System.out.println("Ordenado");
        arqOrd.exibirArq();
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRev.mergeSort2();
        tempoFinal = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();      
        tempoTotalRev = tempoFinal-tempoInicial;
        tempoTotalRev = tempoTotalRev/1000;  
        linha += "   ||  " + compRev + "  |   " + "X" + "    | " + movRev + "  |   " + "X" + "    |  " + tempoTotalRev; 
        System.out.println("Reverso");
        auxRev.exibirArq();
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRand.mergeSort2();
        tempoFinal = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();        
        tempoTotalRand = tempoFinal-tempoInicial;
        tempoTotalRand = tempoTotalRand/1000;  
        linha += "  ||  " + compRand + "  |   " + "X" + "    | " + movRand + "  |   " + "X" + "    |   " + tempoTotalRand+"   |";
        linha+="\n|___________________________________________________________________________________________________________________________________________________________________|";
        gravarLinha(linha);
        System.out.println("Randomico");
        auxRand.exibirArq();
        System.out.println("Merge Sort 2 finalizado");
        
        //---------------------------------CountingSort---------------------------------
        System.out.println("_____________________________________________________________________________________________________________________________________________________");
        linha="\n|Counting Sort        |  ";
        System.out.println("Counting Sort");
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();        
        tempoInicial = System.currentTimeMillis();
        arqOrd.countSort();
        tempoFinal = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        tempoTotalOrd = tempoFinal-tempoInicial;
        tempoTotalOrd = tempoTotalOrd/1000;   
        linha += compOrd + "  |   " + "X" + "    |  " + movOrd + "  |   " + "X" + "    | " + tempoTotalOrd;   
        System.out.println("Ordenado");
        arqOrd.exibirArq();
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRev.countSort();
        tempoFinal = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();      
        tempoTotalRev = tempoFinal-tempoInicial;
        tempoTotalRev = tempoTotalRev/1000;  
        linha += "   ||  " + compRev + "  |   " + "X" + "    |  " + movRev + "  |   " + "X" + "    |  " + tempoTotalRev; 
        System.out.println("Reverso");
        auxRev.exibirArq();
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRand.countSort();
        tempoFinal = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();        
        tempoTotalRand = tempoFinal-tempoInicial;
        tempoTotalRand = tempoTotalRand/1000;  
        linha += "  ||  " + compRand + "  |   " + "X" + "    |  " + movRand + "  |   " + "X" + "    |  " + tempoTotalRand+"    |";
        linha+="\n|___________________________________________________________________________________________________________________________________________________________________|";
        gravarLinha(linha);
        System.out.println("Randomico");
        auxRand.exibirArq();
        System.out.println("Counting Sort finalizado");
        
        //---------------------------------BucketSort---------------------------------
        System.out.println("_____________________________________________________________________________________________________________________________________________________");
        linha="\n|Bucket Sort          | ";
        System.out.println("Bucket Sort");
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();        
        tempoInicial = System.currentTimeMillis();
        arqOrd.bucketSort();
        tempoFinal = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        tempoTotalOrd = tempoFinal-tempoInicial;
        tempoTotalOrd = tempoTotalOrd/1000;   
        linha += compOrd + " |   " + "X" + "    |  " + movOrd + "  |   " + "X" + "    | " + tempoTotalOrd;  
        System.out.println("Ordenado");
        arqOrd.exibirArq();
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRev.bucketSort();
        tempoFinal = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();      
        tempoTotalRev = tempoFinal-tempoInicial;
        tempoTotalRev = tempoTotalRev/1000;  
        linha += "   || " + compRev + " |   " + "X" + "    |  " + movRev + "  |   " + "X" + "    |  " + tempoTotalRev;
        System.out.println("Reverso");
        auxRev.exibirArq();
        //Arquivo Randomico  
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRand.bucketSort();
        tempoFinal = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();        
        tempoTotalRand = tempoFinal-tempoInicial;
        tempoTotalRand = tempoTotalRand/1000;  
        linha += "  || " + compRand + " |   " + "X" + "    |  " + movRand + "  |   " + "X" + "    |  " + tempoTotalRand+"    |";
        linha+="\n|___________________________________________________________________________________________________________________________________________________________________|";
        gravarLinha(linha);
        System.out.println("Randomico");
        auxRand.exibirArq();
        System.out.println("bucket Sort finalizado");
        
        //---------------------------------RadixSort---------------------------------
        System.out.println("_____________________________________________________________________________________________________________________________________________________");
        linha="\n|Radix Sort           |  ";
        System.out.println("Radix Sort");
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();        
        tempoInicial = System.currentTimeMillis();
        arqOrd.radixSort();
        tempoFinal = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        tempoTotalOrd = tempoFinal-tempoInicial;
        tempoTotalOrd = tempoTotalOrd/1000;   
        linha += compOrd + "  |   " + "X" + "    |  " + movOrd + "  |   " + "X" + "    |  " + tempoTotalOrd;  
        System.out.println("Ordenado");
        arqOrd.exibirArq();
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRev.radixSort();
        tempoFinal = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();      
        tempoTotalRev = tempoFinal-tempoInicial;
        tempoTotalRev = tempoTotalRev/1000;  
        linha += "  ||  " + compRev + "  |   " + "X" + "    |  " + movRev + "  |   " + "X" + "    |  " + tempoTotalRev; 
        System.out.println("Reverso");
        auxRev.exibirArq();
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRand.radixSort();
        tempoFinal = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();        
        tempoTotalRand = tempoFinal-tempoInicial;
        tempoTotalRand = tempoTotalRand/1000;  
        linha += "  ||  " + compRand + "  |   " + "X" + "    |  " + movRand + "  |   " + "X" + "    |   " + tempoTotalRand+"   |";
        linha+="\n|___________________________________________________________________________________________________________________________________________________________________|";
        gravarLinha(linha);
        System.out.println("Randomico");
        auxRand.exibirArq();
        System.out.println("Radix Sort finalizado");
        
        //---------------------------------CombSort---------------------------------
        System.out.println("_____________________________________________________________________________________________________________________________________________________");
        linha="\n|Comb Sort            |  ";
        System.out.println("Comb Sort");
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();        
        tempoInicial = System.currentTimeMillis();
        arqOrd.combSort();
        tempoFinal = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        tempoTotalOrd = tempoFinal-tempoInicial;
        tempoTotalOrd = tempoTotalOrd/1000;   
        linha += compOrd + " |   " + "X" + "    |   " + movOrd + "    |   " + "X" + "    |  " + tempoTotalOrd;  
        System.out.println("Ordenado");
        arqOrd.exibirArq();
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRev.combSort();
        tempoFinal = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();      
        tempoTotalRev = tempoFinal-tempoInicial;
        tempoTotalRev = tempoTotalRev/1000;  
        linha += "  || " + compRev + "  |   " + "X" + "    |  " + movRev + "  |   " + "X" + "    |  " + tempoTotalRev; 
        System.out.println("Reverso");
        auxRev.exibirArq();
        //Arquivo Randomico 
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRand.combSort();
        tempoFinal = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();        
        tempoTotalRand = tempoFinal-tempoInicial;
        tempoTotalRand = tempoTotalRand/1000;  
        linha += "  || " + compRand + "  |   " + "X" + "    |  " + movRand + "  |   " + "X" + "    |  " + tempoTotalRand+"    |";
        linha+="\n|___________________________________________________________________________________________________________________________________________________________________|";
        gravarLinha(linha);
        System.out.println("Randomico");
        auxRand.exibirArq();
        System.out.println("Comb Sort finalizado");  
        
        //---------------------------------GnomeSort---------------------------------
        System.out.println("_____________________________________________________________________________________________________________________________________________________");
        linha="\n|Gnome Sort           |  ";
        System.out.println("Gnome Sort");
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();        
        tempoInicial = System.currentTimeMillis();
        arqOrd.gnomeSort();
        tempoFinal = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        tempoTotalOrd = tempoFinal-tempoInicial;
        tempoTotalOrd = tempoTotalOrd/1000;  
        linha += compOrd + "  |   " + "X" + "    |   " + movOrd + "    |   " + "X" + "    | " + tempoTotalOrd;        
        System.out.println("Ordenado");
        arqOrd.exibirArq();
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRev.gnomeSort();
        tempoFinal = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();      
        tempoTotalRev = tempoFinal-tempoInicial;
        tempoTotalRev = tempoTotalRev/1000;  
        linha += "   || " + compRev + "|   " + "X" + "    | " + movRev + "|   " + "X" + "    |    " +tempoTotalRev; 
        System.out.println("Reverso");
        auxRev.exibirArq();
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRand.gnomeSort();
        tempoFinal = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();        
        tempoTotalRand = tempoFinal-tempoInicial;
        tempoTotalRand = tempoTotalRand/1000;  
        linha += "    || " + compRand + " |   " + "X" + "    | " + movRand + " |   " + "X" + "    |  " + tempoTotalRand+"   |";
        linha+="\n|___________________________________________________________________________________________________________________________________________________________________|";
        gravarLinha(linha);
        System.out.println("Randomico");
        auxRand.exibirArq();
        System.out.println("Gnome Sort finalizado");   
        
        //---------------------------------TimSort---------------------------------
        System.out.println("_____________________________________________________________________________________________________________________________________________________");
        linha="\n|Tim Sort              | ";
        System.out.println("Tim Sort");
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();        
        tempoInicial = System.currentTimeMillis();
        arqOrd.timSort();
        tempoFinal = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        tempoTotalOrd = tempoFinal-tempoInicial;
        tempoTotalOrd = tempoTotalOrd/1000;   
        linha += compOrd + "  |   " + "X" + "    | " + movOrd + "  |   " + "X" + "    |  " + tempoTotalOrd;
        System.out.println("Ordenado");
        arqOrd.exibirArq();
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRev.timSort();
        tempoFinal = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();      
        tempoTotalRev = tempoFinal-tempoInicial;
        tempoTotalRev = tempoTotalRev/1000;  
        linha += "  || " + compRev + " |   " + "X" + "    | " + movRev + " |   " + "X" + "    |  " + tempoTotalRev;
        System.out.println("Reverso");
        auxRev.exibirArq();
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        tempoInicial = System.currentTimeMillis();
        auxRand.timSort();
        tempoFinal = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();        
        tempoTotalRand = tempoFinal-tempoInicial;
        tempoTotalRand = tempoTotalRand/1000;  
        linha += " || " + compRand + " |   " + "X" + "    | " + movRand + " |   " + "X" + "    |  " + tempoTotalRand+"    |";
        linha+="\n|___________________________________________________________________________________________________________________________________________________________________|";
        gravarLinha(linha);
        System.out.println("Randomico");
        auxRand.exibirArq();
        System.out.println("Tim Sort finalizado");
    }
    
    public void gravarLinha(String linha)
    {
        try 
        {
            FileWriter arq = new FileWriter("Tabela.txt", true);
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.write(linha);           
            arq.close();
            arq=null;
        }catch(Exception e){}       
    }
    
    public double calcComparacoes(int id)
    {      
        double valor=0;
        switch(id)
        {
            case 0: valor = Tamanho-1; break; //insercaoDireta(Melhor Situacao)  
            case 1: valor = ((Tamanho*Tamanho+Tamanho-2)/4); break; //insecaoDireta(situacao Media)
            case 2: valor = ((Tamanho*Tamanho+Tamanho-4)/4); break; //insercaoDireta(Pior situacao) 
            case 3: valor = ((Tamanho*Tamanho-Tamanho)/2); break; //selecaoDireta(Melhor Situacao, Pior situacao, Situacao Media), bubbleSort(Melhor Situacao, Pior situacao, Situacao Media), shakeSort(Melhor Situacao, Pior situacao, Situacao Media)
            case 4: valor = Tamanho*(Math.log(Tamanho)-Math.log(Math.E)); break; //insercaoBinaria(Melhor Situacao, Pior situacao, Situacao Media)
        }
        return Math.round(valor);
    }
    
    public double calcMovimentacoes(int id)
    {
        double valor=0;
        switch(id)
        {
            case 0: valor = (3*(Tamanho-1)); break; //insercaoDireta(Melhor situacao), insercaoBinaria(Melhor situacao), selecaoDireta(Melhor situacao)
            case 1: valor = ((Tamanho*Tamanho+3*Tamanho-4)/2); break; //insercaoDireta(Pior situacao), insercaoBinaria(Pior situacao)
            case 2: valor = ((Tamanho*Tamanho+9*Tamanho-10)/4); break; //insercaoDireta(Situacao media), insercaoBinaria(Situacao media)
            case 3: valor = (Tamanho*Tamanho/4+3*(Tamanho-1)); break; //selecaoDireta(Pior situacao) 
            case 4: valor = (Tamanho*(Math.log(Tamanho)+Math.E)); break; //selecaoDireta(Situacao media)
            case 5: valor = (3*(Tamanho*Tamanho-Tamanho)/2); break; //bubbleSort(Situacao media), shakeSort(situacao media)
            case 6: valor = (3*(Tamanho*Tamanho-Tamanho)/4); break; //bubbleSort(Pior situacao), shakeSort(Pior situacao)
            case 7: valor = 0; break; //bubbleSort(Melhor situacao), shakeSort(Melhor situacao)
        }
        return Math.round(valor);
    }
    
    public static void main(String[] args) {
        POArquivo p = new POArquivo();     
        p.geraTabela();
    }   
}
