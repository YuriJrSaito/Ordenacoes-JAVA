package Classes;

public class No {
    private No prox;
    private No ant;
    private Registro reg;

    public No(No prox, No ant, Registro reg) {
        this.prox = prox;
        this.ant = ant;
        this.reg = new Registro(reg.getCodigo());
    }
    
    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }

    public No getAnt() {
        return ant;
    }

    public void setAnt(No ant) {
        this.ant = ant;
    }

    public Registro getReg() {
        return this.reg;
    }

    public void setReg(Registro reg) {
        this.reg = reg;
    } 
}
