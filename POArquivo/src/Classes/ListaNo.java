package Classes;

public class ListaNo {
    private No inicio;
    private No fim;
    private int comp;

    public ListaNo() 
    {
        inicio=fim=null;
        comp = 0;
    }
    
    public int getComp()
    {
        return this.comp;
    }
    
    public No getInicio()
    {
        return this.inicio;
    }
    
    public void inserirNoFim(Registro reg)
    {
        No nova = new No(null, fim, reg);
        if(inicio == null)
            inicio = fim = nova;
        else
        {
            fim.setProx(nova);
            fim = nova;
        }
    }
    
    public void insercaoDireta()
    {
        No pi = inicio.getProx(), pj;
        Registro reg = new Registro();
        while(pi != null)
        {
            reg = pi.getReg();
            pj = pi;
            comp++;
            while(pj != inicio && reg.getCodigo() < pj.getAnt().getReg().getCodigo())
            {
                comp++;
                pj.setReg(pj.getAnt().getReg());
                pj = pj.getAnt();
            }
            pj.setReg(reg);
            pi = pi.getProx();
        }
    }
    
    public void exibir()
    {
        No aux = inicio;
        while(aux != null)
        {
            System.out.print(aux.getReg().getCodigo()+" ");
            aux = aux.getProx();
        }
        System.out.println("");
    }
}
