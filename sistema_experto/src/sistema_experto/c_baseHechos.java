/**
 * @name: c_baseHechos.java
 * @description: Clase para controlar la base de hechos
 * @version 17.5.25
 * @author: 
 * @author: 
 * @author: Sanchez Martínez Humberto
 * @author: Valle Rodríguez Julio Cesar
 */

package sistema_experto;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class c_baseHechos {
    private c_moduloSintomas o_Sintomas = new c_moduloSintomas();
    private char a_carHecho;            // Carácter que representa al hecho
    private int a_valHecho;             // Valor del hecho
    
    private ArrayList a_Hecho;          // Arreglo con los datos del hecho
    private ArrayList a_baseHechos;     // Arreglo con los datos de la base de hechos
    
    private Scanner a_Entrada;          // Captura por teclado
    String a_mosHechos;                 //Captura los hechos almacenados en la base de hechos.
    // Dirección de la base de hechos
    final private String a_arcBaseHechos="src/files/bh.dat";
    
    /**
     * @name: m_cargaBaseHechos
     * @description: Método para cargar los datos de la base de hechos en un arreglo
     * @return Arreglo con la base de hechos
     */
    public ArrayList m_cargaBaseHechos(){
        a_baseHechos = new ArrayList();
        RandomAccessFile v_baseHechos = null;
        long v_apActual=0,v_apFinal;
        try{
            v_baseHechos = new RandomAccessFile(a_arcBaseHechos,"r");
        }catch(Exception e){
            System.out.println("m_cargaBaseHechos: Error al abrir el archivo: "+a_arcBaseHechos);
            System.out.println(e.toString());
        }
        if(v_baseHechos!=null){
            try{
                v_apActual=v_baseHechos.getFilePointer();
                v_apFinal=v_baseHechos.length();
                while(v_apActual!=v_apFinal){
                    a_carHecho=v_baseHechos.readChar();
                    a_valHecho=v_baseHechos.readInt();
                    
                    a_Hecho = new ArrayList();
                    a_Hecho.add(a_carHecho);
                    a_Hecho.add(a_valHecho);
                    
                    a_baseHechos.add(a_Hecho);
                    v_apActual=v_baseHechos.getFilePointer();
                    v_apFinal=v_baseHechos.length();
                }
            }catch(Exception e){
                System.out.println("m_cargaBaseHechos: Error al leer el archivo: "+a_arcBaseHechos);
                System.out.println(e.toString());
            }
            try{
                v_baseHechos.close();
            }catch(Exception e){
                System.out.println("m_cargaBaseHechos: El archivo no se pudo cerrado: "+a_arcBaseHechos);
                System.out.println(e.toString());
            }
        }
        return a_baseHechos;
    }// Fin del método m_cargaBaseHechos
    
    /**
     * @name: m_ingresaBaseHechos
     * @description: Método para ingresar hechos al archivo de la base de hechos
     */
    public void m_ingresaBaseHechos(){
        String v_Opcion="";
        RandomAccessFile v_baseHechos=null;
        try{
            File v_arcBaseConocimiento = new File(a_arcBaseHechos);
            v_arcBaseConocimiento.delete();
        }catch(Exception e){
            System.out.println("m_ingresaBaseHechos: Error al eliminar archivo: "+a_arcBaseHechos);
            System.out.println(e.toString());
        }    
        try{
            v_baseHechos = new RandomAccessFile(a_arcBaseHechos,"rw");
        }catch(Exception e){
            System.out.println("m_ingresaBaseHechos: Error al abrir el archivo: "+a_arcBaseHechos);
            System.out.println(e.toString());
        }
        if(v_baseHechos!=null){
            do{
                try{
                    v_Opcion="1";
                    a_Entrada = new Scanner(System.in);
                    a_carHecho=JOptionPane.showInputDialog("Síntoma: ").charAt(0);
                    a_valHecho=1;
                    
                    v_baseHechos.seek(v_baseHechos.length());
                    v_baseHechos.writeChar(a_carHecho);
                    v_baseHechos.writeInt(a_valHecho);
                    
                    v_Opcion=JOptionPane.showInputDialog("\n¿Desea agregar otro síntoma?\n[Si]=1\n[No]=Cualquier tecla\nOpción: ");
                }catch(Exception e){
                    System.out.println("m_ingresaBaseHechos: Valor no Valido");
                    System.out.println(e.toString());
                }
            }while(v_Opcion.equals("1"));
            try{
                v_baseHechos.close();
            }catch(Exception e){
                System.out.println("m_ingresaBaseHechos: El archivo no se a cerrado");
                System.out.println(e.toString());
            }
        }
    }// Fin del método m_ingresaBaseHechos
    
    /**
     * @name: m_mostrarBaseHechos
     * @description: Método para mostrar por pantalla los hechos actuales de la base de hechos
     */
    public String m_mostrarBaseHechos(){
        a_mosHechos="";
        RandomAccessFile v_baseHechos = null;
        long v_apActual=0,v_apFinal;
        try{
            v_baseHechos = new RandomAccessFile(a_arcBaseHechos,"r");
        }catch(Exception e){
            System.out.println("m_mostrarBaseHechos: Error al abrir el archivo: "+a_arcBaseHechos);
            System.out.println(e.toString());
        }
        if(v_baseHechos!=null){
            try{
                v_apActual=v_baseHechos.getFilePointer();
                v_apFinal=v_baseHechos.length();
                while(v_apActual!=v_apFinal){
                    a_carHecho=v_baseHechos.readChar();
                    a_valHecho=v_baseHechos.readInt();
                    if(a_valHecho==1){
                    
                    a_mosHechos+=(""+a_carHecho+"\n");    
                    }
                    
                    v_apActual=v_baseHechos.getFilePointer();
                    v_apFinal=v_baseHechos.length();
                }
            }catch(Exception e){
                System.out.println("m_mostrarBaseHechos: Error al leer el archivo: "+a_arcBaseHechos);
                System.out.println(e.toString());
            }
            try{
                v_baseHechos.close();
            }catch(Exception e){
                System.out.println("m_mostrarBaseHechos: Error al cerrar el archivo: "+a_arcBaseHechos);
                System.out.println(e.toString());
            }
        }
        return a_mosHechos;
    }// Fin del método m_mostrarBaseHechos
    
}
