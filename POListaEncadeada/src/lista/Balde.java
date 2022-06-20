package lista;

public class Balde{
    private Lista lista;
    private Balde prox;
    private Balde ant;
    private int codigo;

    public Balde(Lista lista, Balde prox, Balde ant, int codigo) {
        this.lista = lista;
        this.prox = prox;
        this.ant = ant;
        this.codigo = codigo;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public Balde getProx() {
        return prox;
    }

    public void setProx(Balde prox) {
        this.prox = prox;
    }

    public Balde getAnt() {
        return ant;
    }

    public void setAnt(Balde ant) {
        this.ant = ant;
    }

    public int geCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

}
