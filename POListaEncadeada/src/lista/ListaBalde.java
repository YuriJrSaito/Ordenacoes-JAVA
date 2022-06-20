package lista;

public class ListaBalde {
    private Balde inicio;
    private Balde fim;

    public ListaBalde()
    {
        inicio=fim=null;
    }
    
    public void inicializar()
    {
        inicio=fim=null;
    }
    
    public Balde getInicio()
    {
        return inicio;
    }
    
    public void insereNovoFim(int codigo)
    {
        Lista l = new Lista();
        Balde novo = new Balde(l, null, fim, codigo);
        if(inicio == null)
            inicio=fim=novo;
        else
        {
            fim.setProx(novo);
            fim = novo;
        }   
    }
    
    public Balde procuraBalde(int pos)
    {
        Balde aux = inicio;
        if(aux.geCodigo() == pos)
            return aux;
        else
        {
            while(aux != null && aux.geCodigo() != pos)
                aux = aux.getProx();
            if(aux != null)
                return aux;
            else
                return null;
        }     
    }
    
    
}
