package lista;

import java.util.Random;

public class Lista {
    private No inicio;
    private No fim;
    
    public Lista()
    {
        inicio = fim = null;
    }
    
    public void inicializar() {
        inicio = fim = null;
    }
    
    public No getFim()
    {
        return fim;
    }
    
    public No getInicio()
    {
        return inicio;
    }
    
    public void inserirNoInicio(int info)
    {
        No nova = new No(null, inicio, info);
        if(inicio == null)
            inicio = fim = nova;
        else
        {
            inicio.setAnt(nova);
            inicio = nova;
        }
    }
    
    public void inserirNoFim(int info)
    {
        No nova = new No(fim, null, info);
        if(inicio == null)
            inicio = fim = nova;
        else
        {
            fim.setProx(nova);
            fim = nova;
        }
    }
    
    public void copiaLista(Lista lista)
    {
        No no = lista.getInicio();
        this.inicializar();
        while(no != null)
        {
            this.inserirNoInicio(no.getInfo());
            no = no.getProx();
        }
    }
    
    public void gerarLista(int tamanho)
    {
        Random rand = new Random();
        int cont = 0, valor;
        while(cont < tamanho)
        {
            valor = rand.nextInt(tamanho)+1;
            if(this.verificaValor(valor))
            {
                this.inserirNoInicio(valor);
                cont++;
            }
        }
    }
    
    public boolean verificaValor(int valor)
    {
        No aux = inicio;
        while(aux != null && aux.getInfo() != valor)
            aux = aux.getProx();
        if(aux == null)
            return true;
        return false;
        
    }
    
    public void exibir()
    {
        No aux = inicio;
        while(aux != null)
        {
            System.out.print(aux.getInfo()+" ");
            aux = aux.getProx();
        }
        System.out.println("");
    }
    
    public void insercaoDireta()
    {
        No pi = inicio.getProx(), pj;
        int valor;
        
        while(pi != null)
        {
            pj = pi;
            valor = pi.getInfo();
            while(pj != inicio && valor < pj.getAnt().getInfo())
            {
                pj.setInfo(pj.getAnt().getInfo());
                pj = pj.getAnt();
            }
            pj.setInfo(valor);
            pi = pi.getProx();
        }
    }
        
    public No acharMeio(No inicio, No fim) //buscaBinaria, quickPivoSort
    {
        No devagar = inicio, rapido = inicio.getProx();       
        if(inicio == null)
            return null;
        else
        {
            while(rapido != null && rapido != fim)
            {
                rapido = rapido.getProx();
                if(rapido != null && rapido != fim)
                {
                    devagar = devagar.getProx();
                    rapido = rapido.getProx();
                }
            }        
        }
        return devagar;
    }
    
    public No buscaBinaria(No end, int valor) //insercaoBinaria
    { 
        No meio, start = inicio;
        do{
            meio = acharMeio(start, end);
            if(meio == null)
                return null;
            if(meio.getInfo() == valor)
                return meio;
            else
                if(meio.getInfo() < valor)
                    start = meio.getProx();
                else
                    end = meio;
            
        }while(end != start && meio.getInfo() != valor);
        
        if(valor > meio.getInfo())
            return meio.getProx();
        return meio;
    }
    
    public void insercaoBinaria()
    {
        No pos, pi = inicio.getProx(), pj;
        int valor;
        while(pi != null)
        {
            valor = pi.getInfo();
            pos = buscaBinaria(pi, valor);
            pj = pi;
            while(pj != pos && pj != null)
            {
                pj.setInfo(pj.getAnt().getInfo());
                pj = pj.getAnt();
            }
            pos.setInfo(valor);
            
            pi = pi.getProx();
        }
    }
           
    public void selecaoDireta()
    {
        No posMenor, pi = inicio, pj;
        int menor;
        
        while(pi.getProx() != null)
        {
            posMenor = pi;
            menor = pi.getInfo();
            
            pj = pi.getProx();
            while(pj != null)
            {
                if(pj.getInfo() < menor)
                {
                    posMenor = pj;
                    menor = pj.getInfo(); 
                }
                pj = pj.getProx();
            }
            posMenor.setInfo(pi.getInfo());
            pi.setInfo(menor);
                
            pi = pi.getProx();
        }
    }
        
    public void bubbleSort()
    {
        No pi = fim, pj;
        int aux;
        
        while(pi != inicio)
        {
            pj = inicio;
            while(pj.getProx() != null)
            {
                if(pj.getInfo() > pj.getProx().getInfo())
                {
                    aux = pj.getInfo();
                    pj.setInfo(pj.getProx().getInfo());
                    pj.getProx().setInfo(aux);
                }
                pj = pj.getProx();
            }
            pi = pi.getAnt();
        }
    }
    
    public void shakeSort()
    {
        No pi=inicio;
        int i=0, ini, fim, aux;
        
        ini = buscarPos(inicio);
        fim = buscarPos(this.fim);
       
        while(ini < fim)
        {   
            if(ini>0)
            {
                pi = pi.getProx();
                i++;
            }          
            while(i < fim)
            {
                if(pi.getInfo() > pi.getProx().getInfo())
                {
                    aux = pi.getInfo();
                    pi.setInfo(pi.getProx().getInfo());
                    pi.getProx().setInfo(aux);
                }                
                i++;
                pi = pi.getProx();
            }
            fim--;

            pi = pi.getAnt();
            i--;
            while(i > ini)
            {
                if(pi.getInfo() < pi.getAnt().getInfo())
                {
                    aux = pi.getInfo();
                    pi.setInfo(pi.getAnt().getInfo());
                    pi.getAnt().setInfo(aux);
                }
                i--;
                pi = pi.getAnt();
            }
            ini++;
        }
    }
    
    public int buscarPos(No aux) //busca um int relacionado a posição do ponteiro enviado
    {
        No pi = inicio;
        int i = 0;      
        while(pi != null && pi != aux)
        {
            i++;
            pi = pi.getProx();
        }
        return i;
    }
    
    public No posicao(int pos)  //busca o No na posição relacionada ao int enviado
    {
        No aux = inicio;
        int i=0;
        while(aux != null && i<=pos)
        {
            i++;
            aux = aux.getProx();
        }
        return aux;
    }
    
    //no = posicao do ponteiro
    //posAtual = posicao atual do ponteiro em int
    //posFinal = posicao aonde o ponteiro quer chegar
    public No posicionar(No no, int posAtual, int posFinal) //realoca o ponteiro andando com prox ou ant dependendo de onde ele esta e onde quer chegar
    {
        No aux = no;
        int i;
        if(posAtual > posFinal)
        {
            i = posAtual-posFinal;
            while(aux != null && i>0)
            {     
                i--;
                aux = aux.getAnt();
            }
        }
        else
        if(posAtual < posFinal)
        {
            i = posFinal-posAtual;
            while(aux != null && i>0)
            {      
                i--;
                aux = aux.getProx();
            }
        }
       return aux;
    }      
    
    public void heapSort()
    {
        No pai, FE, FD, maiorF, TL2 = fim;
        int paipos, TL2pos, aux, FEpos;
        
        TL2pos = buscarPos(TL2);
        while(TL2.getAnt() != null)
        {
            pai = inicio;
            paipos=0;
            pai = posicionar(pai, paipos, paipos = TL2pos/2-1);
            FE = inicio;
            FEpos = 0;
            while(pai != null)
            {
                FE = posicionar(FE, FEpos, 2*paipos+1);
                FEpos = 2*paipos+1;
                maiorF = FE;
                
                if(FE.getProx() != null)
                {                   
                    FD = FE.getProx();    
                    if(FD != TL2 && FD.getInfo() > FE.getInfo())
                        maiorF = FD;
                }                
                if(maiorF.getInfo() > pai.getInfo())
                {
                    aux = pai.getInfo();
                    pai.setInfo(maiorF.getInfo());
                    maiorF.setInfo(aux);
                }                      
                pai = pai.getAnt();
                paipos--;            
            }
            if(inicio.getInfo() > TL2.getInfo())
            {
                aux = inicio.getInfo();
                inicio.setInfo(TL2.getInfo());
                TL2.setInfo(aux);
            } 
            TL2 = TL2.getAnt();
            TL2pos--;
        }              
    }
    
    public void shellsort()
    {
        No pi, pj, pk, aux=inicio;
        int pipos, pjpos, pkpos, auxpos=0;
        int dist, valor, posfim;
        
        posfim = buscarPos(fim);
        dist = posfim/4;
        
        while(dist > 0)
        {    
            pi = inicio;
            pipos = 0;
            while(pipos < dist)
            {
                pj = pi;
                pjpos = pipos;
                while(pjpos+dist <= posfim)
                {
                    aux = posicionar(aux, auxpos, pjpos+dist);
                    auxpos = pjpos+dist;
                    
                    if(pj.getInfo() > aux.getInfo())
                    {
                        valor = pj.getInfo();
                        pj.setInfo(aux.getInfo());
                        aux.setInfo(valor);
                        
                        
                        pk = pj;
                        pkpos = pjpos;
                        
                        aux = posicionar(aux, auxpos, pkpos-dist);
                        auxpos = pkpos-dist;
                        
                        while(pkpos-dist >= pipos && pk.getInfo() < aux.getInfo())
                        {
                            valor = pk.getInfo();
                            pk.setInfo(aux.getInfo());
                            aux.setInfo(valor);
                            
                            pk = posicionar(pk, pkpos, pkpos-dist);
                            pkpos-=dist;
                            
                            aux = posicionar(aux, auxpos, pkpos-dist);
                            auxpos = pkpos-dist;
                        }
                        aux = inicio;
                        auxpos = 0;
                    }                      
                    pj=posicionar(pj, pjpos, pjpos+dist);
                    pjpos+=dist;               
                }
                pi = pi.getProx();
                pipos++;
            }
            dist /= 2;
        }
    }
    
    public void quickSort(No pini, No pfim)
    {
        No pi = pini, pj = pfim;
        int i, j, ini, fim, aux;
        boolean flag = true;
        
        i = buscarPos(pini);
        j = buscarPos(pfim);
        ini = i;
        fim = j;
        
        while(i < j)
        {
            if(flag)
                while(pi != pj && pi.getInfo() <= pj.getInfo())
                {
                    i++;
                    pi = pi.getProx();
                }
            else
                while(pi != pj && pj.getInfo() >= pi.getInfo())
                {
                    j--;
                    pj = pj.getAnt();
                }
            
            aux = pi.getInfo();
            pi.setInfo(pj.getInfo());
            pj.setInfo(aux);
            flag = !flag;
        }
        if(pi.getAnt() != null && ini < i-1)
            quickSort(pini, pi.getAnt());
        if(pj.getProx() != null && j+1 < fim)
            quickSort(pj.getProx(), pfim); 
    }
    
    public void quickPivoSort(No pini, No pfim)
    {
        No pi=pini, pj=pfim;
        int i, j, pivo, aux, ini, fim;
        i = buscarPos(pini);
        j = buscarPos(pfim);
        ini = i;
        fim = j;
                
        pivo = acharMeio(pini, pfim).getInfo();
        while(i < j)
        {
            while(pi.getInfo() < pivo)
            {
                pi = pi.getProx();
                i++;
            }
            while(pj.getInfo() > pivo)
            {
                pj = pj.getAnt();
                j--;
            }           
            if(i <= j)
            {
                aux = pi.getInfo();
                pi.setInfo(pj.getInfo());
                pj.setInfo(aux);
                if(pi.getProx() != null)
                {
                    i++;
                    pi = pi.getProx();
                }
                if(pj.getAnt() != null)
                {
                    j--;
                    pj = pj.getAnt();
                }
            }
        }
        if(ini < j)
            quickPivoSort(pini, pj);
        if(i < fim)
            quickPivoSort(pi, pfim);     
    }
    
    private void particao(int tam, Lista part1, Lista part2) //mergeSort1
    {
        part1.inicializar();
        part2.inicializar();
        No aux1 = inicio;
        No aux2 = posicao(tam/2);
        while(aux1 != aux2)
        {
            part1.inserirNoFim(aux1.getInfo());
            aux1 = aux1.getProx();
        }
        while(aux2 != null)
        {
            part2.inserirNoFim(aux2.getInfo());
            aux2 = aux2.getProx();
        }
    }
    
    public void fusao(Lista part1, Lista part2, int seq, int tam) //mergeSort1
    {
        No  pi = part1.getInicio(), pj = part2.getInicio(), pk = inicio;
        int auxSeq = seq, pipos=0, pjpos=0, pkpos=0;
        
        while(pkpos < tam)
        {
            while(pipos<seq && pjpos<seq)
            {               
                if(pi.getInfo()<pj.getInfo()) 
                {
                    pk.setInfo(pi.getInfo());
                    pi = pi.getProx();
                    pipos++;
                }
                else
                {
                    pk.setInfo(pj.getInfo());
                    pj = pj.getProx();
                    pjpos++;
                }
                pk = pk.getProx();     
                pkpos++;
            }
            while(pipos < seq)
            {
                pk.setInfo(pi.getInfo());
                pk = pk.getProx();
                pkpos++;
                pi = pi.getProx();
                pipos++;
            }
            while(pjpos < seq)
            {
                pk.setInfo(pj.getInfo());
                pk = pk.getProx();
                pkpos++;
                pj = pj.getProx();
                pjpos++;
            }
            seq += auxSeq;
        }
    }
    
    public void mergeSort1()
    {
        int seq = 1;
        int tam = buscarPos(fim);
        Lista part1 = new Lista();
        Lista part2 = new Lista();
        while (seq < tam) 
        {
            particao(tam, part1, part2);
            fusao(part1, part2, seq, tam);
            seq = seq*2;
        }
    }
    
    public void fusao2(Lista part1, int ini1, int fim1, int ini2, int fim2) //mergeSort2
    {
        part1.inicializar();
        No pi=inicio, pj=inicio, pk, paux=inicio;
        int pipos=0, pjpos=0, pkpos=0, pauxpos=0;
        
        pi = posicionar(pi, pipos, ini1);
        pipos = ini1;
        pj = posicionar(pj, pjpos, ini2);
        pjpos = ini2;

        while(pipos<=fim1 && pjpos<=fim2)
        {
            if(pi.getInfo() < pj.getInfo())
            {
                part1.inserirNoFim(pi.getInfo());
                pi = pi.getProx();
                pipos++;
                pkpos++;
            }
            else
            {
                part1.inserirNoFim(pj.getInfo());
                pj = pj.getProx();
                pjpos++;
                pkpos++;
            }
        }
        while(pipos <= fim1)
        {
            part1.inserirNoFim(pi.getInfo());
            pi = pi.getProx();
            pipos++;
            pkpos++;
        }
        while(pjpos <= fim2)
        {
            part1.inserirNoFim(pj.getInfo());
            pj = pj.getProx();
            pjpos++;
            pkpos++;
        }
        pk = part1.getInicio();
        int cont=0;           
        while(cont < pkpos)
        {
            paux = posicionar(paux, pauxpos, cont+ini1);
            pauxpos = cont+ini1;
            paux.setInfo(pk.getInfo());
            pk = pk.getProx();
            cont++;
        }      
    }
    
    public void Merge(Lista part1, int esq, int dir) //mergeSort2
    {
        int meio;
        if(esq < dir)
        {
            meio = (esq+dir)/2;
            Merge(part1, esq, meio);
            Merge(part1, meio+1, dir);
            fusao2(part1, esq, meio, meio+1, dir);
        }
    }
    
    public void mergeSort2()
    {
        Lista part1 = new Lista();
        int esq = 0, dir = buscarPos(fim);
        Merge(part1, esq, dir);
    }
    
    public int buscaMaior() //countSort, bucketSort, radixSort
    {
        No aux = inicio;
        int maior = inicio.getInfo();
        while(aux != null)
        {
            if(aux.getInfo() > maior)
                maior = aux.getInfo();
            aux = aux.getProx();
        }
        return maior;
    }
    
    public void countSort()
    {
        int maior = buscaMaior();
        int vet[] = new int[maior];
        int tam = buscarPos(fim);
        No aux = inicio;
        
        for(int i=0; i<maior; i++)
            vet[i]=0;
        
        while(aux != null)
        {
            vet[aux.getInfo()-1]++;
            aux = aux.getProx();
        }
        
        for(int i=1; i<maior; i++)
            vet[i] += vet[i-1];
        
        Lista l2 = new Lista();
        
        for(int i=0; i<=tam; i++)
            l2.inserirNoFim(0);
        
        aux = inicio;
        No no2;
        while(aux!= null)
        {
            no2 = l2.getInicio();
            int i=0;
            while(no2 != null && i<vet[aux.getInfo()-1]-1)
            {
                i++;
                no2 = no2.getProx();
            }
            no2.setInfo(aux.getInfo());
            vet[aux.getInfo()-1]--;
            aux = aux.getProx();         
        }

        aux = inicio;
        no2 = l2.getInicio();
        while(aux != null && no2 != null)
        {
            aux.setInfo(no2.getInfo());
            aux = aux.getProx();
            no2 = no2.getProx();
        }
    }
    
    public void bucketSort()
    {
        int maior = buscaMaior();
        int nBaldes = (int) Math.sqrt(maior), pos;
        int max = (maior-1)/nBaldes;
        ListaBalde lb = new ListaBalde();
        Lista listaAux;
        No aux = inicio;
        Balde b;
        
        for(int i=0; i<nBaldes; i++)       
            lb.insereNovoFim(i);
       
        while(aux != null)
        {
            pos = (aux.getInfo()-1) / (max+1);
            listaAux = lb.procuraBalde(pos).getLista();
            listaAux.inserirNoInicio(aux.getInfo());
            listaAux.insercaoDireta();
            aux = aux.getProx();
        }
        
        b = lb.getInicio();
        while(b != null)
        {
            b.getLista().exibir();
            b = b.getProx();
        }
        
        b = lb.getInicio();
        No no;
        aux = inicio;
        while(b != null)
        {
            no = b.getLista().getInicio();
            while(no != null)
            {
                aux.setInfo(no.getInfo());
                aux = aux.getProx();
                no = no.getProx();
            }
            b = b.getProx();
        }
    }
    
    public void radixSort()
    {
        int maior = buscaMaior();
        for(int i=1; maior/i > 0; i*=10)
            countSort_radix(i);
    }
    
    public void countSort_radix(int v) // radixSort
    {
        int tam = buscarPos(fim);
        int i;
        int vet[] = new int[10];
        Lista l2 = new Lista();
        l2.inicializar();
        for(i=0; i<=tam; i++)
            l2.inserirNoFim(0);
        
        i=0;
        No aux = inicio;
        while(i <= tam)
        {
            vet[(aux.getInfo()/v)%10]++;
            aux = aux.getProx();
            i++;
        }
        
        for(i=1; i<10; i++)
            vet[i] += vet[i-1];
        
        int pos, j;
        aux = fim;
        i=tam;
        No nol2;
        while(i>=0)
        {
            pos = vet[(aux.getInfo()/v) %10]-1;
            j=0;
            nol2 = l2.getInicio();
            while(nol2 != null && j<pos)
            {
                nol2 = nol2.getProx();
                j++;
            }
            nol2.setInfo(aux.getInfo());
            vet[(aux.getInfo()/v)%10]--;
            aux = aux.getAnt();
            i--;
        }
            
        No aux1 = inicio;
        No aux2 = l2.getInicio();
        while (aux1 != null && aux2 != null) 
        {
            aux1.setInfo(aux2.getInfo());
            aux1 = aux1.getProx();
            aux2 = aux2.getProx();
        }       
    }
    
    public void combSort()
    {
        boolean troca = false;
        int tam = buscarPos(fim);
        int gap = tam+1, valor;
        No pi, pj;
        int pjpos; 
        while(gap != 1 || troca)
        {
            gap = (int)(gap / 1.3);
            if(gap < 1)
                gap=1;
            
            troca = false;
            pi = inicio;
            pj = inicio;
            pjpos = 0;
            for(int i = 0; i <=tam - gap; i++)
            {
                pj = posicionar(pj, pjpos, pjpos = i+gap);
                if(pi.getInfo() > pj.getInfo())
                {
                    valor = pi.getInfo();
                    pi.setInfo(pj.getInfo());
                    pj.setInfo(valor);
                    troca = true;
                }
                pi = pi.getProx();
            }
        }
    }
    
    public void gnomeSort()
    {
        int tam = buscarPos(fim);
        int valor=0;
        No pi=inicio, pj=inicio;
        int pipos=0, pjpos=0;
        int i=0;
                
        while(i <= tam)
        {
            if(i == 0)
                i++;
            
            pi = posicionar(pi, pipos, pipos = i);
            pj = posicionar(pj, pjpos, pjpos = i-1);
            if(pi.getInfo() > pj.getInfo())
                i++;
            else
            {
                valor = pi.getInfo();
                pi.setInfo(pj.getInfo());
                pj.setInfo(valor);
                i--;
            }
        }
    }
    
    public void timSort_insertionSort(int esq, int dir)
    {
        System.out.println("esq = "+esq+" dir = "+dir);
        int pipos=0, pjpos = 0;
        No pi = inicio, pj;
        pi = posicionar(pi, pipos, pipos = esq);
        int valor;
        
        while(pipos <= dir)
        {
            pj = pi;
            pjpos = pipos;
            valor = pi.getInfo();
            while(pjpos >esq && valor < pj.getAnt().getInfo())
            {
                pj.setInfo(pj.getAnt().getInfo());
                pj = pj.getAnt();
                pjpos--;
            }
            pj.setInfo(valor);
            pi = pi.getProx();
            pipos++;
        }
    }
    
    public void timSort()
    {
        int tam = buscarPos(fim);
        int timTam = 32;
        Lista part1 = new Lista();
        
        for(int i=0; i <=tam; i+=timTam)
            timSort_insertionSort(i,Math.min((i+31), tam));

        for(int range = timTam; range <= tam; range = 2*range)
        {
            for(int esq = 0; esq <= tam; esq += 2*range)
            {
                int meio = esq + range-1;
                int dir = Math.min((esq+2 * range-1), tam);
                
                fusao_timSort(part1, esq, meio, meio+1, dir);
            }
        }   
    }
    
    public void fusao_timSort(Lista part1, int ini1, int fim1, int ini2, int fim2) //timSort
    {
        part1.inicializar();
        No pi=inicio, pj=inicio, pk, paux=inicio;
        int pipos=0, pjpos=0, pkpos=0, pauxpos=0;
        
        pi = posicionar(pi, pipos, ini1);
        pipos = ini1;
        pj = posicionar(pj, pjpos, ini2);
        pjpos = ini2;

        while(pipos<=fim1 && pjpos<=fim2)
        {
            if(pi.getInfo() < pj.getInfo())
            {
                part1.inserirNoFim(pi.getInfo());
                pi = pi.getProx();
                pipos++;
                pkpos++;
            }
            else
            {
                part1.inserirNoFim(pj.getInfo());
                pj = pj.getProx();
                pjpos++;
                pkpos++;
            }
        }
        while(pipos <= fim1 && pi.getProx() != null)
        {
            part1.inserirNoFim(pi.getInfo());
            pi = pi.getProx();
            pipos++;
            pkpos++;
        }
        while(pjpos <= fim2 && pj.getProx() != null)
        {
            part1.inserirNoFim(pj.getInfo());
            pj = pj.getProx();
            pjpos++;
            pkpos++;
        }
        pk = part1.getInicio();
        int cont=0;           
        while(cont < pkpos)
        {
            paux = posicionar(paux, pauxpos, cont+ini1);
            pauxpos = cont+ini1;
            paux.setInfo(pk.getInfo());
            pk = pk.getProx();
            cont++;
        }      
    } 
}
