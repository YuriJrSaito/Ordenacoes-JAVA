package Classes;

public class Balde {
    private Balde prox;
    private Balde ant;
    private ListaNo lista;
    private int codigo;

    public Balde(Balde prox, Balde ant, ListaNo lista, int codigo) {
        this.prox = prox;
        this.ant = ant;
        this.lista = new ListaNo();
        this.codigo = codigo;
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

    public ListaNo getLista() {
        return lista;
    }

    public void setLista(ListaNo lista) {
        this.lista = lista;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
