/**
 * @name: c_baseConocimiento.java
 * @description: Clase para cargar la base de conocimiento
 * @version 17.5.25
 * @author: 
 * @author: 
 * @author: Sanchez Martínez Humberto
 * @author: Valle Rodríguez Julio Cesar
 */

package sistema_experto;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class c_baseConocimiento {
    
    private int a_noRegla;                      // Número de regla
    private int a_noAntecedentes;               // Número de antecedentes
    private char a_Antecedentes[];              // Antecedentes
    private char a_Consecuente;                 // Consecuente
    
    private ArrayList a_Regla;                 // Arreglo con los datos de las reglas
    private ArrayList a_baseConocimiento;       // Arreglo con los datos de la base de hechos
    
    // Dirección de la base de conocimiento
    final private String a_arcBaseConocimiento="src/files/bc.dat";
    
    /**
     * @name: m_cargarBaseConocimiento
     * @description: Método para cargar los datos de la base de conocimiento en un arreglo
     * @return Arreglo con la base de conocimiento
     */
    public ArrayList m_cargarBaseConocimiento(){
        a_baseConocimiento = new ArrayList();
        RandomAccessFile v_baseConocimiento = null;
        long v_apActual=0,v_apFinal;
        try{
            v_baseConocimiento = new RandomAccessFile(a_arcBaseConocimiento,"r");
        }catch(Exception e){
            System.out.println("m_cargarBaseConocimiento: Error al abrir el archivo: "+a_arcBaseConocimiento);
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
                        a_Regla = new ArrayList();
                        a_Regla.add(a_noRegla);
                        a_Regla.add(a_noAntecedentes);
                        for (int i = 0; i < a_noAntecedentes; i++) {
                            a_Regla.add(a_Antecedentes[i]);
                        }
                        a_Regla.add(a_Consecuente);
                        a_baseConocimiento.add(a_Regla);
                    }
                    v_apActual=v_baseConocimiento.getFilePointer();
                    v_apFinal=v_baseConocimiento.length();
                }
            }catch(Exception e){
                System.out.println("m_cargarBaseConocimiento: Error al leer el archivo: "+a_arcBaseConocimiento);
                System.out.println(e.toString());
            }
            try{
                v_baseConocimiento.close();
            }catch(Exception e){
                System.out.println("m_cargarBaseConocimiento: El archivo no se ha cerrado: "+a_arcBaseConocimiento);
                System.out.println(e.toString());
            }
        }
        return a_baseConocimiento;
    }// Fin del método m_cargarBaseConocimiento
}
