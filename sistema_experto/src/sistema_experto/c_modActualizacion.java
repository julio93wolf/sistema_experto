package sistema_experto;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class c_modActualizacion {
    
    // Datos de la base de conocimiento
    private int a_Llave;
    private int a_noAntecedentes;
    private char a_Antecedentes[];
    private char a_Consecuente;
    private char a_Hechos;
    
    // Captura por teclado
    private Scanner a_Entrada;
    
    // Direccion de la base de conocimiento
    final private String a_baseConocimiento="src/files/bc.dat";
    final private String a_baseConoTemporal="src/files/bc_temp.dat";
    
    // Direccion de la base de hechos
    final private String a_baseHechos="src/files/bh.dat";
    
    public void m_InsertarBC(){
        String v_Opcion="";
        RandomAccessFile v_baseConocimiento = null;
        try{
            v_baseConocimiento = new RandomAccessFile(a_baseConocimiento,"rw");
        }catch(Exception e){
            System.out.println("Error al Insertar: Error al abrir el archivo: "+a_baseConocimiento);
            System.out.println(e.toString());
        }
        if(v_baseConocimiento!=null){
            do{
                try{
                    v_Opcion="1";
                    a_Entrada = new Scanner(System.in);
                    System.out.print("\nNo. Regla: ");
                    a_Llave=a_Entrada.nextInt();
                    System.out.print("¿Numero de Antecedentes?: ");
                    a_noAntecedentes=a_Entrada.nextInt();
                    a_Antecedentes=new char[a_noAntecedentes];
                    for (int i = 0; i < a_noAntecedentes; i++) {
                        System.out.print("Antecedente ["+(i+1)+"]: ");
                        a_Antecedentes[i]=a_Entrada.next().charAt(0);
                    }
                    System.out.print("Consecuente: ");
                    a_Consecuente=a_Entrada.next().charAt(0);
                    
                    v_baseConocimiento.seek(v_baseConocimiento.length());
                    v_baseConocimiento.writeInt(a_Llave);
                    v_baseConocimiento.writeInt(a_noAntecedentes);
                    for (int i = 0; i < a_noAntecedentes; i++) {
                        v_baseConocimiento.writeChar(a_Antecedentes[i]);
                    }
                    v_baseConocimiento.writeChar(a_Consecuente);
                    
                    System.out.println("\n¿Desea agregar otra regla?");
                    System.out.println("[Si]=1\n[No]=Cualquier tecla");
                    System.out.print("Opcion: ");
                    v_Opcion=a_Entrada.next();
                }catch(Exception e){
                    System.out.println("Error al Insertar: Valor no Valido");
                    System.out.println(e.toString());
                }
            }while(v_Opcion.equals("1"));
            try{
                v_baseConocimiento.close();
            }catch(Exception e){
                System.out.println("Error al Insertar: El archivo no se a cerrado");
                System.out.println(e.toString());
            }
        }
    }
   
    public void m_MostrarBC(){
        RandomAccessFile v_baseConocimiento = null;
        long v_apActual=0,v_apFinal;
        try{
            v_baseConocimiento = new RandomAccessFile(a_baseConocimiento,"r");
        }catch(Exception e){
            System.out.println("Error al Leer: Error al abrir el archivo: "+a_baseConocimiento);
            System.out.println(e.toString());
        }
        if(v_baseConocimiento!=null){
            try{
                v_apActual=v_baseConocimiento.getFilePointer();
                v_apFinal=v_baseConocimiento.length();
                System.out.println("");
                while(v_apActual!=v_apFinal){
                    a_Llave=v_baseConocimiento.readInt();
                    a_noAntecedentes=v_baseConocimiento.readInt();
                    a_Antecedentes=new char[a_noAntecedentes];
                    for (int i = 0; i < a_noAntecedentes; i++) {
                        a_Antecedentes[i]=v_baseConocimiento.readChar();   
                    }
                    a_Consecuente=v_baseConocimiento.readChar();
                    if(a_Llave>0){
                        System.out.print("R"+a_Llave+": ");
                        for (int i = 0; i < a_noAntecedentes; i++) {
                            System.out.print(a_Antecedentes[i]);
                            if((i+1)<a_noAntecedentes){
                                System.out.print("^");
                            }
                        }
                        System.out.print("->");
                        System.out.println(a_Consecuente);
                    }
                    v_apActual=v_baseConocimiento.getFilePointer();
                    v_apFinal=v_baseConocimiento.length();
                }
            }catch(Exception e){
                System.out.println("Error al Leer: Error al leer el archivo: "+a_baseConocimiento);
                System.out.println(e.toString());
            }
            try{
                v_baseConocimiento.close();
            }catch(Exception e){
                System.out.println("Error al Leer: El archivo no se a cerrado");
                System.out.println(e.toString());
            }
        }
    }
    
    public void m_ModificaBC(){
        int v_Regla;
        boolean v_Modifica = false,v_banError=false;
        File v_arcBaseConocimiento = null;
        File v_arcBaseConoTemporal = null;
        RandomAccessFile v_baseConocimiento = null;
        RandomAccessFile v_baseConoTemporal = null;
        long v_apActual = 0,v_apFinal;
        try{                
            v_baseConocimiento = new RandomAccessFile(a_baseConocimiento,"r");
            v_baseConoTemporal = new RandomAccessFile(a_baseConoTemporal,"rw");
        }catch(Exception e){
            System.out.println("Error al Modificar: Error al abrir el archivo: "+a_baseConocimiento);
            System.out.println("Error al Modificar: Error al abrir el archivo: "+a_baseConoTemporal);
            System.out.println(e.toString());
        }
        if(v_baseConocimiento != null&&v_baseConoTemporal != null){
            try{
                a_Entrada=new Scanner(System.in);
                System.out.print("\n¿Regla a modificar?: ");
                v_Regla = a_Entrada.nextInt();
                v_apActual = v_baseConocimiento.getFilePointer();
                v_apFinal = v_baseConocimiento.length();
                while(v_apActual != v_apFinal){
                    a_Llave = v_baseConocimiento.readInt();
                    a_noAntecedentes = v_baseConocimiento.readInt();
                    a_Antecedentes = new char[a_noAntecedentes];
                    for (int i = 0; i < a_noAntecedentes; i++) {
                        a_Antecedentes[i] = v_baseConocimiento.readChar();
                    }
                    a_Consecuente = v_baseConocimiento.readChar();
                    if(v_Regla == a_Llave){
                        v_Modifica = true;
                        System.out.print("¿Numero de Antecedentes?: ");
                        a_noAntecedentes = a_Entrada.nextInt();
                        a_Antecedentes = new char[a_noAntecedentes];
                        for (int i = 0; i < a_noAntecedentes; i++) {
                            System.out.print("Antecedente ["+(i+1)+"]: ");
                            a_Antecedentes[i] = a_Entrada.next().charAt(0);
                        }
                        System.out.print("Consecuente: ");
                        a_Consecuente = a_Entrada.next().charAt(0);    
                    }
                    v_baseConoTemporal.seek(v_baseConoTemporal.length());
                    v_baseConoTemporal.writeInt(a_Llave);
                    v_baseConoTemporal.writeInt(a_noAntecedentes);
                    for (int i = 0; i < a_noAntecedentes; i++) {
                        v_baseConoTemporal.writeChar(a_Antecedentes[i]);
                    }
                    v_baseConoTemporal.writeChar(a_Consecuente);
                    v_apActual = v_baseConocimiento.getFilePointer();
                    v_apFinal = v_baseConocimiento.length();
                }
                if(!v_Modifica){
                    System.out.println("\nNo se encontro la regla: "+v_Regla);
                }
            }catch(Exception e){
                System.out.println("Error al Modificar: Error al leer el archivo: "+a_baseConocimiento);
                System.out.println(e.toString());
                v_banError=true;
            }
            try{
                v_baseConocimiento.close();
                v_baseConoTemporal.close();
                if(v_banError){
                    v_arcBaseConoTemporal = new File(a_baseConoTemporal);
                    v_arcBaseConoTemporal.delete();
                }else{
                    v_arcBaseConocimiento = new File(a_baseConocimiento);
                    v_arcBaseConocimiento.delete();
                    v_arcBaseConoTemporal = new File(a_baseConoTemporal);
                    v_arcBaseConoTemporal.renameTo(new File(a_baseConocimiento));
                    v_arcBaseConoTemporal.delete();
                }
            }catch(Exception e){
                System.out.println("Error al Modificar: Error al abrir los archivos");
                System.out.println(e.toString());
            }
        }
    }
    
    public void m_EliminarBC(){
        int v_Regla;
        boolean v_Elimina = false;
        RandomAccessFile v_baseConocimiento = null;
        long v_apActual = 0,v_apFinal;
        try{
            v_baseConocimiento = new RandomAccessFile(a_baseConocimiento,"rw");
        }catch(Exception e){
            System.out.println("Error al Eliminar: Error al abrir el archivo: "+a_baseConocimiento);
            System.out.println(e.toString());
        }
        if(v_baseConocimiento != null){
            try{
                a_Entrada=new Scanner(System.in);
                System.out.print("\n¿Regla a eliminar?: ");
                v_Regla = a_Entrada.nextInt();
                v_apActual = v_baseConocimiento.getFilePointer();
                v_apFinal = v_baseConocimiento.length();
                while(v_apActual != v_apFinal){
                    a_Llave = v_baseConocimiento.readInt();
                    a_noAntecedentes = v_baseConocimiento.readInt();
                    a_Antecedentes = new char[a_noAntecedentes];
                    for (int i = 0; i < a_noAntecedentes; i++) {
                        a_Antecedentes[i] = v_baseConocimiento.readChar();
                    }
                    a_Consecuente = v_baseConocimiento.readChar();
                    if(v_Regla == a_Llave){
                        v_Elimina = true;
                        v_baseConocimiento.seek(v_apActual);
                        v_baseConocimiento.writeInt(-1);
                        a_noAntecedentes = v_baseConocimiento.readInt();
                        a_Antecedentes = new char[a_noAntecedentes];
                        for (int i = 0; i < a_noAntecedentes; i++) {
                            a_Antecedentes[i] = v_baseConocimiento.readChar();
                        }
                        a_Consecuente = v_baseConocimiento.readChar();
                    }
                    v_apActual = v_baseConocimiento.getFilePointer();
                    v_apFinal = v_baseConocimiento.length();
                }
                v_baseConocimiento.close();
                if(!v_Elimina){
                    System.out.println("\nNo se encontro la regla: "+v_Regla);
                }
            }catch(Exception e){
                System.out.println("Error al Eliminar: Error al leer el archivo: "+a_baseConocimiento);
                System.out.println(e.toString());
            }
        }
    }
    
    public void m_InsertarBH(){
        String v_Opcion="";
        RandomAccessFile v_baseHechos = null;
        try{
            v_baseHechos = new RandomAccessFile(a_baseHechos,"rw");
        }catch(Exception e){
            System.out.println("Error al Insertar: Error al abrir el archivo: "+a_baseHechos);
            System.out.println(e.toString());
        }
        if(v_baseHechos!=null){
            do{
                try{
                    v_Opcion="1";
                    a_Entrada = new Scanner(System.in);
                    System.out.print("Hecho: ");
                    a_Hechos=a_Entrada.next().charAt(0);
                    
                    v_baseHechos.seek(v_baseHechos.length());
                    v_baseHechos.writeChar(a_Hechos);
                    
                    System.out.println("\n¿Desea agregar otra hecho?");
                    System.out.println("[Si]=1\n[No]=Cualquier tecla");
                    System.out.print("Opcion: ");
                    v_Opcion=a_Entrada.next();
                }catch(Exception e){
                    System.out.println("Error al Insertar: Valor no Valido");
                    System.out.println(e.toString());
                }
            }while(v_Opcion.equals("1"));
            try{
                v_baseHechos.close();
            }catch(Exception e){
                System.out.println("Error al Insertar: El archivo no se a cerrado");
                System.out.println(e.toString());
            }
        }
    }
    
    public void m_MostrarBH(){
        RandomAccessFile v_baseHechos = null;
        long v_apActual=0,v_apFinal;
        try{
            v_baseHechos = new RandomAccessFile(a_baseHechos,"r");
        }catch(Exception e){
            System.out.println("Error al Leer: Error al abrir el archivo: "+a_baseHechos);
            System.out.println(e.toString());
        }
        if(v_baseHechos!=null){
            try{
                v_apActual=v_baseHechos.getFilePointer();
                v_apFinal=v_baseHechos.length();
                System.out.println("");
                while(v_apActual!=v_apFinal){
                    a_Consecuente=v_baseHechos.readChar();
                    System.out.println(a_Consecuente);
                    v_apActual=v_baseHechos.getFilePointer();
                    v_apFinal=v_baseHechos.length();
                }
            }catch(Exception e){
                System.out.println("Error al Leer: Error al leer el archivo: "+a_baseHechos);
                System.out.println(e.toString());
            }
            try{
                v_baseHechos.close();
            }catch(Exception e){
                System.out.println("Error al Leer: El archivo no se a cerrado");
                System.out.println(e.toString());
            }
        }
    }
}
