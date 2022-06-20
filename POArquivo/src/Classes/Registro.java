package Classes;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Registro {
    
//    public final int tf=1022;
      private int codigo; //4 bytes
//    private char lixo[] = new char[tf]; //2044 bytes 
    
    public Registro(){}
    
    public Registro(int codigo)
    {
        this.codigo = codigo;
    }
    
    public void gravaNoArq(RandomAccessFile arquivo)
    {
        try
        {
           arquivo.writeInt(codigo);
        }catch(IOException e){}
    }
    
    public void leDoArq(RandomAccessFile arquivo)
    {
        try
        {
           codigo = arquivo.readInt();
        }catch(IOException e){}
    }
    
    static int length()
    {
        //int numero; 4 bytes
        //char lixo[] = new char[tf]; 2044 bytes
        //--------------------------------------
        // 2048 bytes
        return(4);
    }
    
    public void exibirReg()
    {
        System.out.print(this.codigo + " ");
    }
    
    public int getCodigo()
    {
        return this.codigo;
    }
    
    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }
    
}
