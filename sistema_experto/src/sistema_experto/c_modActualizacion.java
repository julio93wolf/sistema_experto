/**
 * @name: c_modActualizacion.java
 * @description: Módulo para la actualización de la base de conocimiento
 * @version 17.5.25
 * @author: Valle Rodríguez Julio Cesar
 */
package sistema_experto;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class c_modActualizacion {
    
    // Datos de la base de conocimiento
    private int a_noRegla;                      // Número de regla
    private int a_noAntecedentes;               // Número de antecedentes
    private char a_Antecedentes[];              // Antecedentes
    private char a_Consecuente;                 // Consecuente
    
    private char a_Meta;                        // Carácter que representa una meta
    
    // Captura por teclado
    private Scanner a_Entrada;
    
    // Direccion de la base de conocimiento
    final private String a_arcBaseConocimiento="src/files/bc.dat";
    final private String a_arcBaseConoTemporal="src/files/bc_temp.dat";
    
    // Direccion de las metas
    final private String a_arcMetas="src/files/metas.dat";
    
    /**
     * @name: m_insertarBaseConocimiento
     * @description: Método para insertar reglas en la base de conocimiento
     */
    public void m_insertarBaseConocimiento(){
        String v_Opcion="";
        RandomAccessFile v_baseConocimiento = null;
        try{
            v_baseConocimiento = new RandomAccessFile(a_arcBaseConocimiento,"rw");
        }catch(Exception e){
            System.out.println("m_insertarBaseConocimiento: Error al abrir el archivo: "+a_arcBaseConocimiento);
            System.out.println(e.toString());
        }
        if(v_baseConocimiento!=null){
            do{
                try{
                    v_Opcion="1";
                    a_Entrada = new Scanner(System.in);
                    System.out.print("\nNo. Regla: ");
                    a_noRegla=a_Entrada.nextInt();
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
                    v_baseConocimiento.writeInt(a_noRegla);
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
                    System.out.println("m_insertarBaseConocimiento: Valor no Valido");
                    System.out.println(e.toString());
                }
            }while(v_Opcion.equals("1"));
            try{
                v_baseConocimiento.close();
            }catch(Exception e){
                System.out.println("m_insertarBaseConocimiento: El archivo no se ha cerrado: "+a_arcBaseConocimiento);
                System.out.println(e.toString());
            }
        }
    }// Fin del método m_insertarBaseConocimiento
   
    /**
     * @name: m_mostrarBaseConocimiento
     * @description: Método para mostar por pantalla las reglas en la base de conocimiento
     */
    public void m_mostrarBaseConocimiento(){
        RandomAccessFile v_baseConocimiento = null;
        long v_apActual=0,v_apFinal;
        try{
            v_baseConocimiento = new RandomAccessFile(a_arcBaseConocimiento,"r");
        }catch(Exception e){
            System.out.println("m_mostrarBaseConocimiento: Error al abrir el archivo: "+a_arcBaseConocimiento);
            System.out.println(e.toString());
        }
        if(v_baseConocimiento!=null){
            try{
                v_apActual=v_baseConocimiento.getFilePointer();
                v_apFinal=v_baseConocimiento.length();
                while(v_apActual!=v_apFinal){
                    a_noRegla=v_baseConocimiento.readInt();
                    a_noAntecedentes=v_baseConocimiento.readInt();
                    a_Antecedentes=new char[a_noAntecedentes];
                    for (int i = 0; i < a_noAntecedentes; i++) {
                        a_Antecedentes[i]=v_baseConocimiento.readChar();   
                    }
                    a_Consecuente=v_baseConocimiento.readChar();
                    if(a_noRegla>0){
                        System.out.print("R"+a_noRegla+": ");
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
                System.out.println("m_mostrarBaseConocimiento: Error al leer el archivo: "+a_arcBaseConocimiento);
                System.out.println(e.toString());
            }
            try{
                v_baseConocimiento.close();
            }catch(Exception e){
                System.out.println("m_mostrarBaseConocimiento: El archivo no se ha cerrado: "+a_arcBaseConocimiento);
                System.out.println(e.toString());
            }
        }
    }// Fin del método m_mostrarBaseConocimiento

    /**
     * @name: m_modificaBaseConocimiento
     * @description: Método para modificar una de las reglas de la base de conocimiento
     */
    public void m_modificaBaseConocimiento(){
        int v_Regla;
        boolean v_Modifica = false,v_banError=false;
        File v_arcBaseConocimiento = null;
        File v_arcBaseConoTemporal = null;
        RandomAccessFile v_baseConocimiento = null;
        RandomAccessFile v_baseConoTemporal = null;
        long v_apActual = 0,v_apFinal;
        try{                
            v_baseConocimiento = new RandomAccessFile(a_arcBaseConocimiento,"r");
            v_baseConoTemporal = new RandomAccessFile(a_arcBaseConoTemporal,"rw");
        }catch(Exception e){
            System.out.println("m_modificaBaseConocimiento: Error al abrir el archivo: "+a_arcBaseConocimiento);
            System.out.println("m_modificaBaseConocimiento: Error al abrir el archivo: "+a_arcBaseConoTemporal);
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
                    a_noRegla = v_baseConocimiento.readInt();
                    a_noAntecedentes = v_baseConocimiento.readInt();
                    a_Antecedentes = new char[a_noAntecedentes];
                    for (int i = 0; i < a_noAntecedentes; i++) {
                        a_Antecedentes[i] = v_baseConocimiento.readChar();
                    }
                    a_Consecuente = v_baseConocimiento.readChar();
                    if(v_Regla == a_noRegla){
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
                    v_baseConoTemporal.writeInt(a_noRegla);
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
                System.out.println("m_modificaBaseConocimiento: Error al leer el archivo: "+a_arcBaseConocimiento);
                System.out.println(e.toString());
                v_banError=true;
            }
            try{
                v_baseConocimiento.close();
                v_baseConoTemporal.close();
                if(v_banError){
                    v_arcBaseConoTemporal = new File(a_arcBaseConoTemporal);
                    v_arcBaseConoTemporal.delete();
                }else{
                    v_arcBaseConocimiento = new File(a_arcBaseConocimiento);
                    v_arcBaseConocimiento.delete();
                    v_arcBaseConoTemporal = new File(a_arcBaseConoTemporal);
                    v_arcBaseConoTemporal.renameTo(new File(a_arcBaseConocimiento));
                    v_arcBaseConoTemporal.delete();
                }
            }catch(Exception e){
                System.out.println("m_modificaBaseConocimiento: Error eliminar el archivo: "+a_arcBaseConoTemporal);
                System.out.println("m_modificaBaseConocimiento: Error eliminar el archivo: "+a_arcBaseConocimiento);
                System.out.println("m_modificaBaseConocimiento: Error al renombrar el el archivo: "+a_arcBaseConoTemporal);
                System.out.println(e.toString());
            }
        }
    }// Fin del método m_modificaBaseConocimiento
    
    /**
     * @name: m_eliminarBaseConocimiento
     * @description: Método para eliminar una de las reglas de la base de conocimiento
     */
    public void m_eliminarBaseConocimiento(){
        int v_Regla;
        boolean v_Elimina = false;
        RandomAccessFile v_baseConocimiento = null;
        long v_apActual = 0,v_apFinal;
        try{
            v_baseConocimiento = new RandomAccessFile(a_arcBaseConocimiento,"rw");
        }catch(Exception e){
            System.out.println("m_eliminarBaseConocimiento: Error al abrir el archivo: "+a_arcBaseConocimiento);
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
                    a_noRegla = v_baseConocimiento.readInt();
                    a_noAntecedentes = v_baseConocimiento.readInt();
                    a_Antecedentes = new char[a_noAntecedentes];
                    for (int i = 0; i < a_noAntecedentes; i++) {
                        a_Antecedentes[i] = v_baseConocimiento.readChar();
                    }
                    a_Consecuente = v_baseConocimiento.readChar();
                    if(v_Regla == a_noRegla){
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
                System.out.println("m_eliminarBaseConocimiento: Error al leer el archivo: "+a_arcBaseConocimiento);
                System.out.println(e.toString());
            }
            try{
                v_baseConocimiento.close();
            }catch(Exception e){
                System.out.println("m_eliminarBaseConocimiento: El archivo no se ha cerrado: "+a_arcBaseConocimiento);
                System.out.println(e.toString());
            }
        }
    }// Fin del método m_eliminarBaseConocimiento
    
    /**
     * @name: m_insertarMeta
     * @description: Método para insertar meta en el archivo de metas
     */
    public void m_insertarMeta(){
        String v_Opcion="";
        RandomAccessFile v_Metas = null;
        try{
            v_Metas = new RandomAccessFile(a_arcMetas,"rw");
        }catch(Exception e){
            System.out.println("m_insertarMeta: Error al abrir el archivo: "+a_arcMetas);
            System.out.println(e.toString());
        }
        if(v_Metas!=null){
            do{
                try{
                    v_Opcion="1";
                    a_Entrada = new Scanner(System.in);
                    System.out.print("\nMeta: ");
                    a_Meta=a_Entrada.next().charAt(0);
                    v_Metas.seek(v_Metas.length());
                    v_Metas.writeChar(a_Meta);
                    System.out.println("\n¿Desea agregar otra meta?");
                    System.out.println("[Si]=1\n[No]=Cualquier tecla");
                    System.out.print("Opcion: ");
                    v_Opcion=a_Entrada.next();
                }catch(Exception e){
                    System.out.println("m_insertarMeta: Valor no Valido");
                    System.out.println(e.toString());
                }
            }while(v_Opcion.equals("1"));
            try{
                v_Metas.close();
            }catch(Exception e){
                System.out.println("m_insertarMeta: El archivo no se ha cerrado: "+a_arcMetas);
                System.out.println(e.toString());
            }
        }
    }// Fin del método m_insertarMeta
    
    /**
     * @name: m_mostrarMetas
     * @description: Método para mostrar las metas en el archivo de metas
     */
    public void m_mostrarMetas(){
        RandomAccessFile v_Metas = null;
        long v_apActual=0,v_apFinal;
        try{
            v_Metas = new RandomAccessFile(a_arcMetas,"r");
        }catch(Exception e){
            System.out.println("m_mostrarMetas: Error al abrir el archivo: "+a_arcMetas);
            System.out.println(e.toString());
        }
        if(v_Metas!=null){
            try{
                v_apActual=v_Metas.getFilePointer();
                v_apFinal=v_Metas.length();
                System.out.println("");
                while(v_apActual!=v_apFinal){
                    a_Meta=v_Metas.readChar();
                    System.out.println(a_Meta);
                    v_apActual=v_Metas.getFilePointer();
                    v_apFinal=v_Metas.length();
                }
            }catch(Exception e){
                System.out.println("m_mostrarMetas: Error al leer el archivo: "+a_arcMetas);
                System.out.println(e.toString());
            }
            try{
                v_Metas.close();
            }catch(Exception e){
                System.out.println("m_mostrarMetas: El archivo no se ha cerrado: "+a_arcMetas);
                System.out.println(e.toString());
            }
        }
    }// Fin del método m_mostrarMetas
    
    /**
     * @name: m_vaciarMetas
     * @description: Método para eliminar todas las metas en el archivo de metas
     */
    public void m_vaciarMetas(){   
        File v_arcMetas = null;
        RandomAccessFile v_Metas = null;
        try{
            v_arcMetas = new File(a_arcMetas);
            v_arcMetas.delete();
            v_Metas = new RandomAccessFile(a_arcMetas,"rw");
            v_Metas.close();
        }catch(Exception e){
            System.out.println("m_vaciarMetas: Error al eliminar el archivo: "+a_arcMetas);
            System.out.println("m_vaciarMetas: Error al crear el archivo: "+a_arcMetas);
            System.out.println(e.toString());
        }
    }// Fin del método m_vaciarMetas
}
