package Classes;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Arquivo {
    private String nomearquivo;
    private RandomAccessFile arquivo;
    private int comp, mov;
    
    public Arquivo(String nomearquivo)
    {
        try 
        {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        } catch (IOException e){}
    }
    
    public void copiaArquivo(Arquivo arquivoOrigem)
    {
        Registro reg = new Registro();
        for(int i=0; i<arquivoOrigem.filesize(); i++)
        {
            arquivoOrigem.seekArq(i);
            reg.leDoArq(arquivoOrigem.getFile());
            seekArq(i);
            reg.gravaNoArq(arquivo);
        }     
    }
    
    public RandomAccessFile getFile()
    {
        return this.arquivo;
    }
    
    public void truncate(long pos)
    {
        try 
        {
            arquivo.setLength(pos * Registro.length());
        } catch (IOException exc){}
    }
    
    public boolean eof() 
    {
        boolean bo = false;
        try
        {
            if (arquivo.getFilePointer() == arquivo.length())            
                bo = true;          
        } catch (IOException e){}
        return (bo);
    }
    
    public void seekArq(int pos) 
    {
        try
        {
            arquivo.seek(pos * Registro.length());
        }catch (IOException e){}
    }
    
    public int filesize()
    {
        try 
        {
            return (int) (arquivo.length() / Registro.length());
        } catch (IOException e){}
        return 0;
    }
    
    public void inserirRegNoFinal(Registro reg) 
    {
        seekArq(filesize());
        reg.gravaNoArq(arquivo);
    }
    
    public void inserir(int info)
    {
        Registro novo = new Registro(info);
        seekArq(filesize());
        novo.gravaNoArq(arquivo);   
    }
    
    public void swap(int i, int j) 
    {
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        seekArq(i);
        reg1.leDoArq(arquivo);
        seekArq(j);
        reg2.leDoArq(arquivo);
        seekArq(i);
        reg2.gravaNoArq(arquivo);
        seekArq(j);
        reg1.gravaNoArq(arquivo);
    }
    
    public void exibirArq() 
    {
        int i;
        Registro aux = new Registro();
        seekArq(0);
        i = 0;
        while (!eof()) 
        {
            aux.leDoArq(arquivo);
            aux.exibirReg();
            i++;
        }
        System.out.println("");
    }
    
    public void initComp() 
    {
        this.comp=0;
    } 
    
    public void initMov() 
    {
        this.mov=0;
    } 
    
    public int getComp() 
    {
        return comp;
    }
    
    public int getMov() 
    {
        return mov; 
    }
    
    public void insercaoDireta() 
    {
        int TL = filesize();
        int pos;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        for (int i = 1; i < TL; i++) 
        {
            pos = i;
            seekArq(pos - 1);
            reg2.leDoArq(arquivo);
            reg1.leDoArq(arquivo);
           
            comp++;
            while (pos > 0 && reg1.getCodigo() < reg2.getCodigo()) 
            {
                seekArq(pos);
                reg2.gravaNoArq(arquivo);
                mov++;
                seekArq(--pos - 1);
                reg2.leDoArq(arquivo);
                comp++;            
            }
            seekArq(pos);
            reg1.gravaNoArq(arquivo);
            mov++;
        }
    }
    
    private int buscaBinaria(Registro reg, int TL) //insercaoBinaria
    {
        int ini = 0, fim = TL - 1, meio = TL / 2;
        Registro regaux = new Registro();
        seekArq(meio);
        regaux.leDoArq(arquivo);

        comp++;
        while (ini < fim && reg.getCodigo() != regaux.getCodigo()) 
        {
            comp++;
            if (reg.getCodigo() < regaux.getCodigo()) 
                fim = meio - 1;
            else 
                ini = meio + 1;
            meio = (ini + fim) / 2;

            seekArq(meio);
            regaux.leDoArq(arquivo);
        }
        comp++;
        if (reg.getCodigo() < regaux.getCodigo()) 
            return meio;
        
        return meio + 1;
    }
    
    public void insercaoBinaria()
    { 
        int TL = filesize();
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        int pos;
        for (int i = 1; i < TL; i++)
        {
            seekArq(i);
            reg1.leDoArq(arquivo);
            pos = buscaBinaria(reg1, i);
            for (int j = i; j > pos; j--)
            {
                seekArq(j - 1);
                reg2.leDoArq(arquivo);
                reg2.gravaNoArq(arquivo);
                mov++;
            }
            seekArq(pos);
            reg1.gravaNoArq(arquivo);
            mov++;
        }
    }
    
    public void selecaoDireta() 
    {
        Registro menor = new Registro();
        Registro reg2 = new Registro();
        int posMenor;
        for (int i = 0; i < filesize() - 1; i++) 
        {
            seekArq(i);
            menor.leDoArq(arquivo);
            posMenor = i;
            for (int j = i + 1; j < filesize(); j++) 
            {
                reg2.leDoArq(arquivo);
                comp++;
                if (menor.getCodigo() > reg2.getCodigo()) 
                {
                    seekArq(j);
                    menor.leDoArq(arquivo);
                    posMenor = j;
                }
            }
            seekArq(i);
            reg2.leDoArq(arquivo);
            
            seekArq(posMenor);
            reg2.gravaNoArq(arquivo);
            
            seekArq(i);
            menor.gravaNoArq(arquivo);
            mov += 2;
        }
    }
    
    public void bubbleSort()
    {
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        int i, TL2 = filesize();
        while(TL2 > 1)
        {
            for(i=0;i<TL2-1;i++)
            {
                seekArq(i);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                comp++;
                if(reg1.getCodigo() > reg2.getCodigo())
                {
                    //System.out.println("I: "+reg1.getCodigo()+"J: "+reg2.getCodigo());
                    seekArq(i);
                    mov+=2;
                    reg2.gravaNoArq(arquivo);
                    reg1.gravaNoArq(arquivo);
                }
            }
            TL2--;
        }      
    }
    
    public void shakeSort() 
    {
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        int inicio = 0, fim = filesize()-1;
        while(inicio < fim)
        {
            for (int i = inicio; i < fim; i++) 
            {
                seekArq(i);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                comp++;
                if (reg1.getCodigo() > reg2.getCodigo()) 
                {
                    mov += 2;
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    reg1.gravaNoArq(arquivo);
                }
            }
            fim--;

            for (int i = fim; i > inicio; i--) 
            {
                seekArq(i-1);
                reg2.leDoArq(arquivo);
                reg1.leDoArq(arquivo);
                comp++;
                if (reg1.getCodigo() < reg2.getCodigo()) 
                {
                    mov += 2;
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    seekArq(i-1);
                    reg1.gravaNoArq(arquivo);
                }
            }
            inicio++;
        }
    }
    
    public void heapSort() 
    {
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        int fd, fe, maiorf, pai, TL2;
        TL2 = filesize();
        while (TL2 > 1)
        {
            pai = TL2/2 -1;
            while (pai >= 0)
            {
                fe = 2 * pai + 1;
                fd = fe + 1;
                maiorf = fe;

                seekArq(fe);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);

                comp++;
                if (fd < TL2 && reg1.getCodigo() < reg2.getCodigo()) 
                    maiorf = fd;
                
                seekArq(maiorf);
                reg1.leDoArq(arquivo);
                seekArq(pai);
                reg2.leDoArq(arquivo);

                comp++;
                if (reg1.getCodigo() > reg2.getCodigo())
                {
                    seekArq(pai);
                    reg1.gravaNoArq(arquivo);
                    seekArq(maiorf);
                    reg2.gravaNoArq(arquivo);                 
                    mov += 2;
                }
                pai--;
            }
            mov += 2;
            swap(0, TL2 - 1);
            TL2--;
        }
    }
    
    public void shellSort() 
    {
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        int i, j, k, dist;
        for (dist = 4; dist > 0; dist /= 2)        
            for (i = 0; i < dist; i++)            
                for (j = i; j + dist < filesize(); j += dist) 
                {
                    seekArq(j);
                    reg1.leDoArq(arquivo);
                    seekArq(j + dist);
                    reg2.leDoArq(arquivo);
                    comp++;
                    if (reg1.getCodigo() > reg2.getCodigo()) 
                    {
                        mov += 2;
                        swap(j, j + dist);
                        k = j;
                        seekArq(k);
                        reg1.leDoArq(arquivo);
                        seekArq(k - dist);
                        reg2.leDoArq(arquivo);
                        comp++;
                        while (k - dist >= i && reg1.getCodigo() < reg2.getCodigo()) 
                        {
                            comp++;
                            mov += 2;
                            swap(k, k - dist);
                            k -= dist;
                            seekArq(k);
                            reg1.leDoArq(arquivo);
                            seekArq(k - dist);
                            reg2.leDoArq(arquivo);
                        }

                    }
                }                   
    }
    
    public void quickSort(int ini, int fim) 
    {
        int i=ini, j=fim;
        boolean flag = true;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        
        while(i < j)
        {
            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);
            if(flag)       
            {
                comp++;
                while(i < j && reg1.getCodigo() < reg2.getCodigo())
                {
                    i++;
                    seekArq(i);
                    reg1.leDoArq(arquivo);
                    comp++;
                }    
            }
            else
            {
                comp++;
                while(i < j && reg2.getCodigo() > reg1.getCodigo())
                {                  
                    j--;
                    seekArq(j);
                    reg2.leDoArq(arquivo);
                    comp++;
                }
            }
            mov+=2;
            swap(i,j);
            flag = !flag;       
        }
        if(ini < i-1)
            quickSort(ini, i-1);
        if(j+1 < fim)
            quickSort(j+1, fim);       
    }
    
    public void quickSortPivo(int ini, int fim) 
    {
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        Registro pivo = new Registro();
        int i = ini;
        int j = fim;
        int valor = (ini + fim) / 2;
        seekArq(valor);
        pivo.leDoArq(arquivo);
        while (i < j) 
        {
            seekArq(i);
            reg1.leDoArq(arquivo);
            comp++;
            while (reg1.getCodigo() < pivo.getCodigo()) 
            {
                i++;
                seekArq(i);
                reg1.leDoArq(arquivo);
                comp++;
            }
            seekArq(j);
            reg2.leDoArq(arquivo);
            comp++;
            while (reg2.getCodigo() > pivo.getCodigo()) 
            {
                j--;
                seekArq(j);
                reg2.leDoArq(arquivo);
                comp++;
            }
            if(i <= j)
            {
                seekArq(i);
                reg2.gravaNoArq(arquivo);
                seekArq(j);
                reg1.gravaNoArq(arquivo);
                mov += 2;
                i++;
                j--;
            }
        }
        if (ini < j) 
            quickSortPivo(ini, j);        
        if (i < fim) 
            quickSortPivo(i, fim);       
    }
    
    public void fusao(Arquivo arq1, Arquivo arq2, int seq) //mergeSort
    {
        int i=0, j=0, k=0, auxSeq = seq;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        while(k < filesize())
        {
            while(i<seq && j<seq)
            {
                arq1.seekArq(i);
                reg1.leDoArq(arq1.getFile());
                arq2.seekArq(j);
                reg2.leDoArq(arq2.getFile());               
                comp++;
                if(reg1.getCodigo() < reg2.getCodigo())
                {
                    i++;
                    seekArq(k++);
                    reg1.gravaNoArq(arquivo);
                    mov++;
                }
                else
                {
                    j++;
                    seekArq(k++);
                    reg2.gravaNoArq(arquivo);
                    mov++;
                }
            }
            
            while(i < seq)
            {
                arq1.seekArq(i++);
                reg1.leDoArq(arq1.getFile());
                seekArq(k++);
                reg1.gravaNoArq(arquivo);
                mov++;
            }
            while(j < seq)
            {
                arq2.seekArq(j++);
                reg2.leDoArq(arq2.getFile());
                seekArq(k++);
                reg2.gravaNoArq(arquivo);
                mov++;
            }
            seq = seq+auxSeq;
        }
    }
    
    public void particao(Arquivo arq1, Arquivo arq2) //mergeSort
    {
        int tam = filesize()/2;
        Registro reg = new Registro();
        
        for(int i=0; i<tam; i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            arq1.seekArq(i);
            reg.gravaNoArq(arq1.getFile());
            
            seekArq(i+tam);
            reg.leDoArq(arquivo);
            arq2.seekArq(i);
            reg.gravaNoArq(arq2.getFile());
            
            mov+=2;
        }
        arq1.truncate(tam);
        arq2.truncate(tam);
    }
    
    public void mergeSort()
    {
        try
        {
            int seq = 1;
            Arquivo arq1 = new Arquivo("mergeArq1.dat");
            Arquivo arq2 = new Arquivo("mergeArq2.dat");
            while(seq < filesize())
            {
                particao(arq1, arq2);
                fusao(arq1, arq2, seq);
                seq = seq*2;       
            }
            arq1 = null;
            arq2 = null;
        }catch(Exception e){}  
    }
    
    public void fusao2(Arquivo arq1, int ini1, int fim1, int ini2, int fim2) //mergeSort2
    {
        int i=ini1, j=ini2, k=0;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        
        while(i<=fim1 && j<=fim2)
        {
            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);
            comp++;
            if(reg1.getCodigo() < reg2.getCodigo())
            {
                arq1.seekArq(k++);
                reg1.gravaNoArq(arq1.getFile());
                i++;
                mov++;
            }
            else
            {
                arq1.seekArq(k++);
                reg2.gravaNoArq(arq1.getFile());
                j++;
                mov++;
            }
        }
        
        while(i <= fim1)
        {
            seekArq(i++);
            reg1.leDoArq(arquivo);
            arq1.seekArq(k++);
            reg1.gravaNoArq(arq1.getFile());
            mov++;
        }
        while(j <= fim2)
        {
            seekArq(j++);
            reg2.leDoArq(arquivo);
            arq1.seekArq(k++);
            reg2.gravaNoArq(arq1.getFile());
            mov++;
        }
        
        for(i=0; i<k; i++)
        {
            arq1.seekArq(i);
            reg1.leDoArq(arq1.getFile());
            
            seekArq(i+ini1);
            reg1.gravaNoArq(arquivo);
            mov++;
        }
    }
    
    public void merge(Arquivo arq1, int esq, int dir) //mergeSort2
    {
        int meio;
        if(esq<dir)
        {
            meio = (esq+dir)/2;
            merge(arq1, esq, meio);
            merge(arq1, meio+1, dir);
            fusao2(arq1, esq, meio, meio+1, dir);
        }
    } 
    
    public void mergeSort2()
    {
        Arquivo arq1 = new Arquivo("mergeSort2.dat");
        merge(arq1, 0, filesize()-1);
        arq1 = null;
    }
    
    public int buscarMaior()
    {
        int maior;
        Registro reg = new Registro();
        seekArq(0);
        maior = reg.getCodigo();
        for (int i = 1; i < filesize(); i++)
        {
            reg.leDoArq(arquivo);
            comp++;
            if (maior < reg.getCodigo())
                maior = reg.getCodigo();           
        }
        return maior;
    }
        
    public void countSort() 
    {
        int maior = buscarMaior()+1;
        int vet[] = new int[maior];
        int pos;
        Registro reg = new Registro();
        
        for(int i=0; i<maior; i++)
            vet[i]=0;
        
        for(int i=0; i<filesize(); i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            vet[reg.getCodigo()-1]++;
        }
        
        for(int i=1; i<maior; i++)
            vet[i] += vet[i-1];
        
        int vet2[] = new int[filesize()];
        
        for(int i=0; i<filesize(); i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            pos = reg.getCodigo();
            vet2[vet[pos-1]-1] = pos;
            vet[pos-1]--;
        }
               
        for(int i=0; i<filesize(); i++)
        {
            reg.setCodigo(vet2[i]);
            seekArq(i);
            reg.gravaNoArq(arquivo);
            mov++;
        }
    }
    
    public void bucketSort()
    {
        int maior = buscarMaior()+1;
        int nBaldes = (int) Math.sqrt(maior), pos;
        int max = (maior-1)/nBaldes;
        ListaBalde lb = new ListaBalde();
        Registro reg = new Registro();
        ListaNo listaAux;
        Balde b;
        
        for(int i=0; i<nBaldes; i++)       
            lb.insereNovoFim(i);
        
        int i=0;
        while(i < filesize())
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            pos = (reg.getCodigo()-1) / (max+1);
            listaAux = lb.procuraBalde(pos).getLista();
            listaAux.inserirNoFim(reg);
            listaAux.insercaoDireta();
            comp += listaAux.getComp();
            i++;
        }
        
        b = lb.getInicio();
        while(b != null)
        {
            b.getLista().exibir();
            b = b.getProx();
        }
        
        b = lb.getInicio();
        No no;
        i=0;
        while(b != null)
        {
            no = b.getLista().getInicio();
            while(no != null)
            {
                seekArq(i);
                reg = no.getReg();
                mov++;
                reg.gravaNoArq(arquivo);
                no = no.getProx();
                i++;
            }
            b = b.getProx();
        } 
    }
    
    public void timSort_insertionSort(int esq, int dir) 
    {
        System.out.println("esq = "+esq+" dir = "+dir);
        int j;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        for (int i = esq; i <= dir; i++) 
        {
            j = i;
            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(j - 1);
            reg2.leDoArq(arquivo);
            
            comp++;
            while (j > esq && reg1.getCodigo() < reg2.getCodigo()) 
            {
                seekArq(j);
                reg2.gravaNoArq(arquivo);
                mov++;
                j--;
                seekArq(j - 1);
                reg2.leDoArq(arquivo);
                comp++;
            }
            seekArq(j);
            reg1.gravaNoArq(arquivo);
            mov++;
        }
    }
    
    public void timSort()
    {
        int tam = filesize()-1;
        int timTam = 32;
        Arquivo aux = new Arquivo("timSortArqAux.dat");
        
        for(int i=0; i<=tam; i+=timTam)
            timSort_insertionSort(i,Math.min((i+31), tam));
        for(int range = timTam; range <= tam; range = 2*range)
        {
            for(int esq = 0; esq <= tam; esq += 2*range)
            {
                int meio = esq + range-1;
                int dir = Math.min((esq+2 * range-1), tam);               
                fusao2(aux, esq, meio, meio+1, dir);
            }
        }
    }
    
    public void radixSort()
    {
        int maior = buscarMaior();
        for(int i=1; maior/i > 0; i*=10)
            countSort_radix(i);
    }
    
    public void countSort_radix(int v)
    {
        int TL = filesize();
        int i, pos, va;
        int vet[] = new int[10];
        int vet2[] = new int[TL];
        Registro reg = new Registro();
        
        i=0;
        while(i<TL)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            vet[(reg.getCodigo()/v)%10]++;
            i++;
        }
        
        for(i=1; i<10; i++)
            vet[i] += vet[i-1];
        
        i=TL-1;
        while(i >= 0)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            pos = reg.getCodigo();
            va = vet[(pos/v) %10]-1;
            vet2[va] = pos;
            vet[(pos/v)%10]--;
            i--;
        }
        
        for(i=0; i<TL; i++)
        {
            reg.setCodigo(vet2[i]);
            seekArq(i);
            reg.gravaNoArq(arquivo);
            mov++;
        }    
    }
    
    public void gnomeSort() 
    {
       int i=0;
       Registro reg1 = new Registro();
       Registro reg2 = new Registro();
       
       while(i < filesize())
       {
           seekArq(i-1);
           reg2.leDoArq(arquivo);
           reg1.leDoArq(arquivo);
           comp++;
           if(i!=0 && reg1.getCodigo() < reg2.getCodigo())
           {
               mov+=2;
               seekArq(i-1);
               reg2.leDoArq(arquivo);
               reg1.leDoArq(arquivo);
               
               seekArq(i-1);
               reg1.gravaNoArq(arquivo);
               reg2.gravaNoArq(arquivo);               
               i--;
           }
           else
               i++;
       }
    }
    
    public void combSort()
    {
        int gap = filesize();
        boolean troca = true; 
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        
        while(gap != 1 || troca == true)
        {
            gap /= 1.3;
            if(gap < 1)
                gap = 1;
            troca = false;
            for(int i=0; i<filesize()-gap;i++)
            {
                seekArq(i);
                reg1.leDoArq(arquivo);
                seekArq(i+gap);
                reg2.leDoArq(arquivo);
                comp++;
                if(reg1.getCodigo() > reg2.getCodigo())
                {
                    swap(i, i+gap);
                    mov+=2;
                    troca = true;
                }
            }
        }      
    }
    
    public void geraArquivoOrdenado(int tam)
    {
        Registro reg;
        int i=0;
        while(i < tam)
        {
            reg = new Registro(i+1);
            seekArq(i);
            reg.gravaNoArq(arquivo);    
            i++; 
        }    
        truncate(tam);
    }
    
    public void geraArquivoReverso(int tam)
    {
        Registro reg;
        
        tam--;
        int i=0;
        while(tam >= 0)
        {
            reg = new Registro(tam+1);
            seekArq(i++);
            reg.gravaNoArq(arquivo);
            tam--;
        }
        truncate(i);      
    }
    
    /*public boolean verifica(int valor)
    {
        boolean bo = true;
        Registro reg = new Registro();
        for(int i=0; i<filesize() && bo==true;i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            if(reg.getCodigo() == valor)
                bo = false;
        }
        return bo;
    }
    
    public void geraArquivoRandomico(int tam) //muito lento
    {
        Random rand = new Random();
        int valor,cont=0;
        while(cont < tam)
        {
            valor = valor = rand.nextInt(tam)+1;
            if(verifica(valor))
            {
                inserir(valor);
                cont++;
            }
        }
    }*/
    
    public void geraArquivoRandomico(int tam)
    {
        Registro reg;
        int i =0;
        ArrayList<Integer> lista = new ArrayList<Integer>();
        for(i=0; i<tam; i++)
            lista.add(i+1);
               
        Collections.shuffle(lista);
        for(i=0; i<tam; i++)
        {
            reg = new Registro(lista.get(i));
            seekArq(i);
            reg.gravaNoArq(arquivo);
        }
        truncate(tam); 
    }
}
