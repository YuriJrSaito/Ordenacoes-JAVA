package Classes;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Registro {
    
      private int codigo; //4 bytes
      public final int tf=1022;
      private char lixo[] = new char[tf]; //2044 bytes 
    
    public Registro(){}
    
    public Registro(int codigo)
    {
        this.codigo = codigo;
        for(int i=0; i<tf;i++)
            lixo[i] = 'X';
    }
    
    /*public void gravaNoArq(RandomAccessFile arquivo) //apenas para quando o retorno de length for 4
    {
        try
        {
           arquivo.writeInt(codigo);
        }catch(IOException e){}
    }*/
    
    public void leDoArq(RandomAccessFile arquivo)
    {
        try
        {
            codigo = arquivo.readInt();
            for(int i=0 ; i<tf ; i++)
                lixo[i]=arquivo.readChar();
        
        }catch(IOException e){}
    }
    
    public void gravaNoArq(RandomAccessFile arquivo)
    {
        try
        {
           arquivo.writeInt(codigo);
           for(int i=0 ; i<tf ; i++)
            arquivo.writeChar(lixo[i]);
        }catch(IOException e){}
    }

    
    /*public void leDoArq(RandomAccessFile arquivo) //apenas para quando o retorno de length for 4
    {
        try
        {
           codigo = arquivo.readInt();
        }catch(IOException e){}
    }*/
    
    static int length()
    {
        //int numero; 4 bytes
        //char lixo[] = new char[tf]; 2044 bytes
        //--------------------------------------
        // 2048 bytes
        return(2048);
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
