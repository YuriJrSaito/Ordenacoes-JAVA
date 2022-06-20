package lista;

public class No {
    private No prox;
    private No ant;
    private int info;
    
    public No(No ant, No prox, int info) {
        this.prox = prox;
        this.ant = ant;
        this.info = info;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }
    
    public void setAnt(No ant)
    {
        this.ant = ant;
    }
    
    public No getAnt()
    {
        return ant;
    }
}
