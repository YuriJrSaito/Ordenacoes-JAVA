package main;

import java.util.Random;
import lista.Lista;
import lista.No;

public class principal {

    public static void main(String[] args) {
        Lista L = new Lista();
        Lista Laux = new Lista();
        int Tamanho = 1024;
        
        L.gerarLista(Tamanho);
        System.out.println("Lista aleatoria gerada: ");
        L.exibir();
        
        //-------------------Insercao direta-------------------
        System.out.println("\nInsercao Direta");
        Laux.copiaLista(L);
        Laux.insercaoDireta();
        Laux.exibir();
           
        //-------------------Insercao Binaria-------------------
        System.out.println("\nInsercao Binaria");
        Laux.copiaLista(L);
        Laux.insercaoBinaria();
        Laux.exibir();
        
        //-------------------Selecao Direta-------------------
        System.out.println("\nSelecao Direta");
        Laux.copiaLista(L);
        Laux.selecaoDireta();
        Laux.exibir();
            
        //-------------------Bubble Sort-------------------
        System.out.println("\nBubble Sort");
        Laux.copiaLista(L);
        Laux.bubbleSort();
        Laux.exibir();
        
        //-------------------Shake Sort-------------------
        System.out.println("\nShake Sort");
        Laux.copiaLista(L);
        Laux.shakeSort();
        Laux.exibir();
        
        //-------------------Heap Sort-------------------
        System.out.println("\nHeap Sort");
        Laux.copiaLista(L);
        Laux.heapSort();
        Laux.exibir();
        
        //-------------------Shell Sort-------------------
        System.out.println("\nShell Sort");
        Laux.copiaLista(L);
        Laux.shellsort();
        Laux.exibir();
        
        //-------------------Quick Sort-------------------
        System.out.println("\nQuick Sort");
        Laux.copiaLista(L);
        Laux.quickSort(Laux.getInicio(), Laux.getFim());
        Laux.exibir();
        
        //-------------------Quick Sort Pivo-------------------
        System.out.println("\nQuick Sort Pivo");
        Laux.copiaLista(L);
        Laux.quickPivoSort(Laux.getInicio(), Laux.getFim());
        Laux.exibir();
        
        //-------------------Merge Sort 1-------------------
        System.out.println("\nMerge Sort 1");
        Laux.copiaLista(L);
        Laux.mergeSort1();
        Laux.exibir();
        
        //-------------------Merge Sort 2-------------------
        System.out.println("\nMerge Sort 2");
        Laux.copiaLista(L);
        Laux.mergeSort2();
        Laux.exibir();
        
        //-------------------Count Sort-------------------
        System.out.println("\nCount Sort");
        Laux.copiaLista(L);
        Laux.countSort();
        Laux.exibir();
        
        //-------------------Bucket Sort-------------------
        System.out.println("\nBucket Sort");
        Laux.copiaLista(L);
        Laux.bucketSort();
        Laux.exibir();
        
        //-------------------Radix Sort-------------------
        System.out.println("\nRadix Sort");
        Laux.copiaLista(L);
        Laux.radixSort();
        Laux.exibir();
        
        //-------------------Comb Sort-------------------
        System.out.println("\nComb Sort");
        Laux.copiaLista(L);
        Laux.combSort();
        Laux.exibir();
        
        //-------------------Gnome Sort-------------------
        System.out.println("\nGnome Sort");
        Laux.copiaLista(L);
        Laux.gnomeSort();
        Laux.exibir();
        
        //-------------------Tim Sort-------------------
        System.out.println("\nTim Sort");
        Laux.copiaLista(L);
        Laux.timSort();
        Laux.exibir();
    }
    
}
