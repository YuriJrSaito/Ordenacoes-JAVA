package Classes;

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
        return this.inicio;
    }
    
    public void insereNovoFim(int codigo)
    {
        Balde novo = new Balde(null, fim, null, codigo);
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
        if(inicio.getCodigo() == pos)
            return aux;
        else
        {
            while(aux != null && aux.getCodigo() != pos)
                aux = aux.getProx();
            if(aux != null)
                return aux;
            else
                return null;
        }     
    }
}
