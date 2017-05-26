/**
 * @name: c_modActualizacion.java
 * @description: Módulo para la actualización de la base de conocimiento
 * @version 17.5.25
 * @author: Valle Rodríguez Julio Cesar
 */
package sistema_experto;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class c_motorInferencia {
    
    private c_baseHechos o_baseHechos;                  // Objeto de la clase de la base de hechos
    private c_baseConocimiento o_baseConocimiento;      // Objeto de la clase de la base de conocimiento
    
    private ArrayList a_baseHechos;                     // Arreglo de la base de hechos
    private ArrayList a_baseConocimiento;               // Arreglo de la base de conocimiento
    private ArrayList a_Metas;                          // Arreglo con las metas del sistema experto
    private ArrayList a_conjuntoConflicto;              // Arreglo del conjunto conflicto
    private ArrayList a_Resolver;                       // Arreglo con la regla a resolver    
    private int a_noReglaDescartar;                     // Numero de regla a descartar
    private boolean a_banderaNuevoHecho=false;          // Badera de nuevo hecho
    private char a_NuevoHecho;                          // Nuevo hecho
    

    // Captura por teclado
    private Scanner a_Entrada;
    
    // Direccion de las metas
    final private String a_arcMetas="src/files/metas.dat";
    
    // Dirección de la base de hechos
    final private String a_arcBaseHechos="src/files/bh.dat";
    
    /**
     * @name: c_motorInferencia
     * @description: Constructor de la clase c_motorInferencia
     */
    public c_motorInferencia(){ 
        o_baseHechos = new c_baseHechos();              // Instancia de la base de hechos
        o_baseConocimiento = new c_baseConocimiento();  // Instancia de la base de conocimiento
    }// Fin del constructor de la clase c_motorInferencia
    
    /**
     * @name: m_encAdelante
     * @description: Método de encadenamiento adelante
     */
    public void m_encadenamientoAdelante(){
        int v_contador=0;
        boolean v_BanderaCC=true;
        o_baseHechos.m_ingresaBaseHechos();                                     // Ingresa los hechos iniciales
        a_baseHechos = o_baseHechos.m_cargaBaseHechos();                        // Carga la base de hechos
        a_baseConocimiento = o_baseConocimiento.m_cargarBaseConocimiento();     // Carga la base de conocimiento
        a_Metas=m_cargaMetas();                                                 // Carga las metas del sistema experto
        if(a_baseHechos.size()>0&&a_baseConocimiento.size()>0&&a_Metas.size()>0){       
            a_conjuntoConflicto = new ArrayList();
            a_conjuntoConflicto.add(a_baseConocimiento.get(0));                 // Extraer cualquier regla de la base de conocimieto
            while(!m_contenidaMeta()&&a_conjuntoConflicto.size()>0){            // Mientras la meta no este en la BH y el CC no este vacio
                a_banderaNuevoHecho = false;
                v_BanderaCC=true;
                a_conjuntoConflicto = m_Equiparar();                            // Equipara antecedentes de la BC con la BH
                v_contador=0;
                if(a_conjuntoConflicto.size()>0){                               // Si el conjunto conflicto no esta vacio
                    while(v_BanderaCC){
                        if(v_contador<a_conjuntoConflicto.size()){
                            a_Resolver=(ArrayList)a_conjuntoConflicto.get(v_contador);
                            m_aplicarRegla();
                            if(a_banderaNuevoHecho){
                                m_actualizaBaseHechos(m_Consulta(a_NuevoHecho));
                                a_baseHechos = o_baseHechos.m_cargaBaseHechos();    // Carga la base de hechos
                                v_BanderaCC = false;
                            }
                            m_eliRegla();
                            v_contador++;
                        }else{
                            v_BanderaCC=false;
                            a_conjuntoConflicto.clear();
                        }
                        
                    }
                }
            }
            if(m_contenidaMeta()){
                System.out.println("\nÉxito");
                System.out.println("Diagnosico: "+a_NuevoHecho);
            }else{
                System.out.println("\nFracaso");
            }
        }
    }
    
    /**
     * @name: m_cargaMetas
     * @description: Método para cargar las metas del sistema experto
     */
    private ArrayList m_cargaMetas(){
        ArrayList v_arrayMetas = null;
        char v_Meta;
        v_arrayMetas = new ArrayList();
        RandomAccessFile v_arcMetas = null;
        long v_apActual=0,v_apFinal;
        try{
            v_arcMetas = new RandomAccessFile(a_arcMetas,"r");
        }catch(Exception e){
            System.out.println("m_cargaMetas: Error al abrir el archivo: "+a_arcMetas);
            System.out.println(e.toString());
        }
        if(v_arcMetas!=null){
            try{
                v_apActual=v_arcMetas.getFilePointer();
                v_apFinal=v_arcMetas.length();
                while(v_apActual!=v_apFinal){
                    v_Meta=v_arcMetas.readChar();
                    v_arrayMetas.add(v_Meta);
                    v_apActual=v_arcMetas.getFilePointer();
                    v_apFinal=v_arcMetas.length();
                }
            }catch(Exception e){
                System.out.println("m_cargaMetas: Error al leer el archivo: "+a_arcMetas);
                System.out.println(e.toString());
            }
            try{
                v_arcMetas.close();
            }catch(Exception e){
                System.out.println("m_cargaMetas: El archivo no se ha cerrado: "+a_arcMetas);
                System.out.println(e.toString());
            }
        }
        return v_arrayMetas;
    }// Fin del método m_cargaMetas
    
    /**
     * @name: m_contenidaMeta()
     * @description: Método para detectar si la meta se encuentra en la base de hechos
     * @return booleano
     */
    private boolean m_contenidaMeta(){
        boolean v_Bandera=false;
        ArrayList v_Hecho;
        char v_Meta1,v_Meta2;
        for (int i = 0; i < a_Metas.size(); i++) {
            v_Meta1=(char)a_Metas.get(i);
            for (int j = 0; j < a_baseHechos.size(); j++) {
                v_Hecho=(ArrayList)a_baseHechos.get(j);
                v_Meta2=(char)v_Hecho.get(0);
                if(v_Meta1==v_Meta2){
                    v_Bandera=true;
                }
            }
        }
        return v_Bandera;
    }// Fin del método m_contenidaMeta
    
    private ArrayList m_Equiparar(){
        boolean v_Bandera1,v_Bandera2;
        int v_noRegla;                      
        int v_noAntecedentes;               
        char v_Antecedente;             
        char v_carHecho;
        ArrayList v_Regla;
        ArrayList v_Hecho;
        ArrayList v_conjuntoConflicto = new ArrayList();
        
        for (int i = 0; i < a_baseConocimiento.size(); i++) {
            v_Regla = (ArrayList)a_baseConocimiento.get(i);
            v_noRegla = (int)v_Regla.get(0);
            if(v_noRegla>0){
                v_noAntecedentes = (int)v_Regla.get(1);
                v_Bandera1 = true;
                for (int j = 0; j < v_noAntecedentes; j++) {
                    v_Bandera2 = false;
                    v_Antecedente = (char)v_Regla.get(2+j);
                    for (int k = 0; k < a_baseHechos.size(); k++) {
                        v_Hecho = (ArrayList)a_baseHechos.get(k);
                        v_carHecho = (char)v_Hecho.get(0);
                        if(v_Antecedente==v_carHecho){
                            v_Bandera2=true;
                        }
                    }
                    if(v_Bandera2==false){
                        v_Bandera1=false;
                    }
                }
                if(v_Bandera1==true){
                    v_conjuntoConflicto.add(v_Regla);
                }
            }
        }
        return v_conjuntoConflicto;
    }
    
    private void m_aplicarRegla(){
        boolean v_Bandera=true;
        int v_noRegla;                      
        int v_noAntecedentes;               
        char v_Antecedente;             
        char v_carHecho;
        int v_Valor;
        ArrayList v_Hecho;
        v_noRegla = (int)a_Resolver.get(0);
        v_noAntecedentes = (int)a_Resolver.get(1);
        for (int i = 0; i < v_noAntecedentes; i++) {
            v_Antecedente = (char)a_Resolver.get(2+i);
            for (int j = 0; j < a_baseHechos.size(); j++) {
                v_Hecho = (ArrayList)a_baseHechos.get(j);
                v_carHecho = (char)v_Hecho.get(0);
                v_Valor = (int)v_Hecho.get(1);
                if(v_Antecedente==v_carHecho){
                    if(v_Valor!=1){
                        v_Bandera=false;
                    }
                }
            }
        }
        if(v_Bandera){
            a_banderaNuevoHecho= true;
            a_NuevoHecho = (char)a_Resolver.get(2+v_noAntecedentes);
        }
        a_noReglaDescartar = v_noRegla;
    }
    
    private boolean m_Consulta(char p_Hecho){
        String v_Opcion="";
        boolean v_Bandera=false;
        try{
            v_Opcion="1";
            a_Entrada = new Scanner(System.in);
            System.out.println("\nTiene : "+p_Hecho);
            System.out.println("[Si]=1\n[No]=Cualquier tecla");
            System.out.print("Opción: ");
            v_Opcion=a_Entrada.next();
            if(v_Opcion.equals("1")){
                v_Bandera=true;
            }else{
                v_Bandera=false;
            }
        }catch(Exception e){
            System.out.println("m_Consulta: Error valor no valido");
        }
        return v_Bandera;
    }
    
    private void m_actualizaBaseHechos(boolean p_Valor){
        RandomAccessFile v_baseHechos = null;
        int v_Valor;
        char v_carHecho;
        try{
            v_baseHechos = new RandomAccessFile(a_arcBaseHechos,"rw");
        }catch(Exception e){
            System.out.println("m_actualizaBaseHechos: Error al abrir el archivo: "+a_arcBaseHechos);
            System.out.println(e.toString());
        }
        if(v_baseHechos!=null){
            try{
                v_carHecho=a_NuevoHecho;
                if(p_Valor){v_Valor=1;}else{v_Valor=0;}
                v_baseHechos.seek(v_baseHechos.length());
                v_baseHechos.writeChar(v_carHecho);
                v_baseHechos.writeInt(v_Valor);
            }catch(Exception e){
                System.out.println("m_actualizaBaseHechos: Error al escribir en el archivo: "+a_arcBaseHechos);
                System.out.println(e.toString());
            }
            try{
                v_baseHechos.close();
            }catch(Exception e){
                System.out.println("m_actualizaBaseHechos: El archivo no se ha cerrado: "+a_arcBaseHechos);
                System.out.println(e.toString());
            }
        }
    }
    
    private void m_eliRegla(){
        ArrayList v_Regla;
        int v_noRegla;
        for (int i = 0; i < a_baseConocimiento.size(); i++) {
            v_Regla = (ArrayList)a_baseConocimiento.get(i);
            v_noRegla = (int)v_Regla.get(0);
            if(v_noRegla==a_noReglaDescartar){
                v_Regla.set(0,-1);
                a_baseConocimiento.set(i,v_Regla);
            }
        }
    }
}


