/**
 * @name: c_modActualizacion.java
 * @description: Módulo para la actualización de la base de conocimiento
 * @version 17.5.25
 * @author: 
 * @author: 
 * @author: Sanchez Martínez Humberto
 * @author: Valle Rodríguez Julio Cesar
 */
package sistema_experto;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class c_moduloActualizacion {
    
    // Datos de la base de conocimiento
    private int a_noRegla;                      // Número de regla
    private int a_noAntecedentes;               // Número de antecedentes
    private char a_Antecedentes[];              // Antecedentes
    private char a_Consecuente;                 // Consecuente
    
    private char a_Meta;                        // Carácter que representa una meta
    
    // Captura por teclado
    //private Scanner a_Entrada;
    
    // Direccion de la base de conocimiento
    final private String a_arcBaseConocimiento="src/files/bc.dat";
    final private String a_arcBaseConoTemporal="src/files/bc_temp.dat";
    
    // Direccion de las metas
    final private String a_arcMetas="src/files/metas.dat";
    String reglas="";
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
                    a_noRegla=Integer.parseInt(JOptionPane.showInputDialog("No. Regla: "));
                    a_noAntecedentes=Integer.parseInt(JOptionPane.showInputDialog("¿Numero de Antecedentes?: "));
                    a_Antecedentes=new char[a_noAntecedentes];
                    for (int i = 0; i < a_noAntecedentes; i++) {
                        a_Antecedentes[i]=JOptionPane.showInputDialog("Antecedente ["+(i+1)+"]: ").charAt(0);
                    }
                    a_Consecuente=JOptionPane.showInputDialog("Consecuente: ").charAt(0);
                    v_baseConocimiento.seek(v_baseConocimiento.length());
                    v_baseConocimiento.writeInt(a_noRegla);
                    v_baseConocimiento.writeInt(a_noAntecedentes);
                    for (int i = 0; i < a_noAntecedentes; i++) {
                        v_baseConocimiento.writeChar(a_Antecedentes[i]);
                    }
                    v_baseConocimiento.writeChar(a_Consecuente);
                    v_Opcion=JOptionPane.showInputDialog("¿Desea agregar otra regla?\n[Si]=1\n[No]=Cualquier tecla\nOpcion: ");
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
    public String m_mostrarBaseConocimiento(){
        reglas="";
        RandomAccessFile v_baseConocimiento = null;
        long v_apActual=0,v_apFinal;
        try{
            v_baseConocimiento = new RandomAccessFile(a_arcBaseConocimiento,"r");
        }catch(Exception e){
            reglas=("m_mostrarBaseConocimiento: Error al abrir el archivo: "+a_arcBaseConocimiento);
            reglas+=(e.toString());
        }
        if(v_baseConocimiento!=null){
            try{
                reglas+=("");
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
                        reglas+=("R"+a_noRegla+": ");
                        for (int i = 0; i < a_noAntecedentes; i++) {
                            reglas+=(a_Antecedentes[i]);
                            if((i+1)<a_noAntecedentes){
                                reglas+=("^");
                            }
                        }
                        reglas+=("->");
                        reglas+=(a_Consecuente);
                        reglas+=("\n");
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
        return reglas;
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
                v_Regla = Integer.parseInt(JOptionPane.showInputDialog("¿Regla a modificar?: "));
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
                        a_noAntecedentes = Integer.parseInt(JOptionPane.showInputDialog("¿Numero de Antecedentes?: "));
                        a_Antecedentes = new char[a_noAntecedentes];
                        for (int i = 0; i < a_noAntecedentes; i++) {
                            a_Antecedentes[i] = JOptionPane.showInputDialog("Antecedente ["+(i+1)+"]: ").charAt(0);
                        }
                        a_Consecuente = JOptionPane.showInputDialog("Consecuente: ").charAt(0);    
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
                    JOptionPane.showMessageDialog(null,"Regla no encontrada","Error",JOptionPane.ERROR_MESSAGE);
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
                v_Regla = Integer.parseInt(JOptionPane.showInputDialog("\n¿Regla a eliminar?: "));
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
                        JOptionPane.showMessageDialog(null,"Regla Eliminada","Regla Eliminada",JOptionPane.INFORMATION_MESSAGE);
                    }
                    v_apActual = v_baseConocimiento.getFilePointer();
                    v_apFinal = v_baseConocimiento.length();
                }
                v_baseConocimiento.close();
                if(!v_Elimina){
                    JOptionPane.showMessageDialog(null,"Regla no encontrada","Error",JOptionPane.ERROR_MESSAGE);
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
}
