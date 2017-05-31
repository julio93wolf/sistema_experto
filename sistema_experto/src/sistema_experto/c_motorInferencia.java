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
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class c_motorInferencia {
    
    private c_moduloSintomas o_Sintomas;
    private c_baseHechos o_baseHechos;                  // Objeto de la clase de la base de hechos
    private c_baseConocimiento o_baseConocimiento;      // Objeto de la clase de la base de conocimiento
    
    private ArrayList a_baseHechos;                     // Arreglo de la base de hechos
    private ArrayList a_baseConocimiento;               // Arreglo de la base de conocimiento
    private ArrayList a_conjuntoConflicto;              // Arreglo del conjunto conflicto
    private ArrayList a_Resolver;                       // Arreglo con la regla a resolver    
    private int a_noReglaDescartar;                     // Numero de regla a descartar
    private boolean a_banderaNuevoHecho=false;          // Badera de nuevo hecho
    private char a_NuevoHecho;                          // Nuevo hecho
    
    private ArrayList a_Estrategia;

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
        o_Sintomas = new c_moduloSintomas();
        o_baseHechos = new c_baseHechos();              // Instancia de la base de hechos
        o_baseConocimiento = new c_baseConocimiento();  // Instancia de la base de conocimiento
    }// Fin del constructor de la clase c_motorInferencia
    
    /**
     * @name: m_encAdelante
     * @description: Método de encadenamiento adelante
     */
    public char m_encadenamientoAdelante(){
        a_Estrategia = new ArrayList();
        int v_contador=0;
        boolean v_BanderaCC=true;
        o_baseHechos.m_ingresaBaseHechos();                                     // Ingresa los hechos iniciales
        a_baseHechos = o_baseHechos.m_cargaBaseHechos();                        // Carga la base de hechos
        a_baseConocimiento = o_baseConocimiento.m_cargarBaseConocimiento();     // Carga la base de conocimiento
        if(a_baseHechos.size()>0&&a_baseConocimiento.size()>0){       
            a_conjuntoConflicto = new ArrayList();
            a_conjuntoConflicto.add(a_baseConocimiento.get(0));                 // Extraer cualquier regla de la base de conocimieto
            a_Resolver=(ArrayList)a_baseConocimiento.get(0);
            a_NuevoHecho=(char)a_Resolver.get(a_Resolver.size()-1);
            while(!m_contenidaMeta(a_NuevoHecho)&&a_conjuntoConflicto.size()>0){            // Mientras la meta no este en la BH y el CC no este vacio
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
                                if(m_esMeta(a_NuevoHecho)){
                                    m_actualizaBaseHechos(m_esMeta(a_NuevoHecho));
                                }else{
                                    m_actualizaBaseHechos(m_Consulta(a_NuevoHecho));
                                }
                                String a_Ciclo [];
                                a_Ciclo = m_Ciclo();
                                a_Estrategia.add(a_Ciclo);
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
            if(m_contenidaMeta(a_NuevoHecho)){
                JOptionPane.showMessageDialog(null,"Diagnosico: "+o_Sintomas.m_BuscaSintoma(a_NuevoHecho),"Diagnostico Encontrado",JOptionPane.INFORMATION_MESSAGE);;
            }else{
                JOptionPane.showMessageDialog(null,"Diagnosico: "+o_Sintomas.m_BuscaSintoma(a_NuevoHecho),"Diagnostico Incompleto",JOptionPane.ERROR_MESSAGE);;
            }
        }
        return a_NuevoHecho;
    }
    
    /**
     * @name: m_contenidaMeta()
     * @description: Método para detectar si la meta se encuentra en la base de hechos
     * @return booleano
     */
    private boolean m_contenidaMeta(char p_Meta){
        boolean v_Bandera=false;
        ArrayList v_Hecho;
        char v_carHecho,v_Meta=p_Meta;
        if(m_esMeta(v_Meta)){
            for (int j = 0; j < a_baseHechos.size(); j++) {
                v_Hecho=(ArrayList)a_baseHechos.get(j);
                v_carHecho=(char)v_Hecho.get(0);
                if(v_Meta==v_carHecho){
                    v_Bandera=true;
                }
            }
        }
        return v_Bandera;
    }// Fin del método m_contenidaMeta
    
    private boolean m_esMeta(char p_Meta){
        boolean v_Bandera=true;
        ArrayList v_Regla;
        int v_noAntecedentes;
        char v_Antecedente,v_Meta=p_Meta;
        for (int i = 0; i < a_baseConocimiento.size(); i++) {
            v_Regla=(ArrayList)a_baseConocimiento.get(i);
            v_noAntecedentes = (int)v_Regla.get(1);
            for (int j = 0; j < v_noAntecedentes; j++) {
                v_Antecedente = (char) v_Regla.get(2+j);
                if(v_Antecedente==v_Meta){
                    v_Bandera=false;
                }
            }
        }
        return v_Bandera;
    }
    
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
            v_Opcion=JOptionPane.showInputDialog("\nTiene : "+o_Sintomas.m_BuscaSintoma(p_Hecho)+"\n[Si]=1\n[No]=Cualquier tecla\nOpción: ");
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
    
    private String[] m_Ciclo(){
        ArrayList v_Hecho;
        ArrayList v_Regla;
        int v_Resolucion;
        String v_Ciclo[] = new String[3];
        v_Ciclo[0]="{";
        for (int i = 0; i < a_baseHechos.size(); i++) {
            v_Hecho=(ArrayList)a_baseHechos.get(i);
            v_Ciclo[0]+=v_Hecho.get(0);
            if(i+1<a_baseHechos.size()){
                v_Ciclo[0]+=",";
            }
        }
        v_Ciclo[0]+="}";      
        v_Ciclo[1]="{";
        for (int i = 0; i < a_conjuntoConflicto.size(); i++) {
            v_Regla=(ArrayList)a_conjuntoConflicto.get(i);
            v_Ciclo[1]+=v_Regla.get(0);
            if(i+1<a_conjuntoConflicto.size()){
                v_Ciclo[1]+=",";
            }
        }
        v_Ciclo[1]+="}";
        v_Ciclo[2]= ""+(int)a_Resolver.get(0);
        return v_Ciclo;
    }
    
    public ArrayList m_getEstrategia(){
        return a_Estrategia;
    }
}


