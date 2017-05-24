package sistema_experto;

import java.io.RandomAccessFile;
import java.util.ArrayList;

public class c_motInferencia {
    
    private ArrayList a_Hechos;
    private ArrayList a_baseHechos;
    private ArrayList a_Reglas;
    private ArrayList a_baseConocimiento;
    private ArrayList a_conConflicto;
    private ArrayList a_Metas;
    private ArrayList a_Resolver;
    
    private int a_Llave;
    private int a_noAntecedentes;
    private char a_Antecedentes[];
    private char a_Consecuente;
    private char a_Hecho;
    private int a_Valor;
    private char a_Meta;
    private boolean a_NuevoHecho=false;
    private int a_eliRegla;
    
    // Direccion de la base de conocimiento
    final private String a_arcBaseConocimiento="src/files/bc.dat";
    
    // Direccion de la base de hechos
    final private String a_arcBaseHechos="src/files/bh.dat";
    
    // Direccion de las metas
    final private String a_arcMetas="src/files/metas.dat";
    
    public void m_encAdelante(){
        m_baseHechos();
        m_baseConocimiento();
        m_Metas();
        if(a_baseHechos.size()>0&&a_baseConocimiento.size()>0){
            a_conConflicto = new ArrayList();
            a_conConflicto.add(a_baseConocimiento.get(0));
            m_conMeta();
            while(!m_conMeta()&&a_conConflicto.size()>0){
                a_NuevoHecho=false;
                a_conConflicto = new ArrayList();
                m_Equiparar();
                if(a_conConflicto.size()>0){
                    a_Resolver=(ArrayList)a_conConflicto.get(0);
                    m_Aplicar();
                    if(a_NuevoHecho){
                        m_actBaseHechos();
                        m_baseHechos();
                        m_eliRegla();
                    }
                }
            }
            if(m_conMeta()){
                System.out.println("Éxito");
            }else{
                System.out.println("Fracaso");
            }
        }
    }
    
    private void m_baseHechos(){
        a_baseHechos = new ArrayList();
        RandomAccessFile v_baseHechos = null;
        long v_apActual=0,v_apFinal;
        try{
            v_baseHechos = new RandomAccessFile(a_arcBaseHechos,"r");
        }catch(Exception e){
            System.out.println("Error al Leer: Error al abrir el archivo: "+a_arcBaseHechos);
            System.out.println(e.toString());
        }
        if(v_baseHechos!=null){
            try{
                v_apActual=v_baseHechos.getFilePointer();
                v_apFinal=v_baseHechos.length();
                while(v_apActual!=v_apFinal){
                    a_Hecho=v_baseHechos.readChar();
                    a_Valor=v_baseHechos.readInt();
                    a_Hechos = new ArrayList();
                    a_Hechos.add(a_Hecho);
                    a_Hechos.add(a_Valor);
                    a_baseHechos.add(a_Hechos);
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
    
    private void m_baseConocimiento(){
        a_baseConocimiento = new ArrayList();
        RandomAccessFile v_baseConocimiento = null;
        long v_apActual=0,v_apFinal;
        try{
            v_baseConocimiento = new RandomAccessFile(a_arcBaseConocimiento,"r");
        }catch(Exception e){
            System.out.println("Error al Leer: Error al abrir el archivo: "+a_arcBaseConocimiento);
            System.out.println(e.toString());
        }
        if(v_baseConocimiento!=null){
            try{
                v_apActual=v_baseConocimiento.getFilePointer();
                v_apFinal=v_baseConocimiento.length();
                while(v_apActual!=v_apFinal){
                    a_Llave=v_baseConocimiento.readInt();
                    a_noAntecedentes=v_baseConocimiento.readInt();
                    a_Antecedentes=new char[a_noAntecedentes];
                    for (int i = 0; i < a_noAntecedentes; i++) {
                        a_Antecedentes[i]=v_baseConocimiento.readChar();   
                    }
                    a_Consecuente=v_baseConocimiento.readChar();
                    if(a_Llave>0){
                        a_Reglas = new ArrayList();
                        a_Reglas.add(a_Llave);
                        a_Reglas.add(a_noAntecedentes);
                        for (int i = 0; i < a_noAntecedentes; i++) {
                            a_Reglas.add(a_Antecedentes[i]);
                        }
                        a_Reglas.add(a_Consecuente);
                        a_baseConocimiento.add(a_Reglas);
                    }
                    v_apActual=v_baseConocimiento.getFilePointer();
                    v_apFinal=v_baseConocimiento.length();
                }
            }catch(Exception e){
                System.out.println("Error al Leer: Error al leer el archivo: "+a_arcBaseConocimiento);
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
    
    private void m_Metas(){
        a_Metas = new ArrayList();
        RandomAccessFile v_Metas = null;
        long v_apActual=0,v_apFinal;
        try{
            v_Metas = new RandomAccessFile(a_arcMetas,"r");
        }catch(Exception e){
            System.out.println("Error al Leer: Error al abrir el archivo: "+a_arcMetas);
            System.out.println(e.toString());
        }
        if(v_Metas!=null){
            try{
                v_apActual=v_Metas.getFilePointer();
                v_apFinal=v_Metas.length();
                while(v_apActual!=v_apFinal){
                    a_Meta=v_Metas.readChar();
                    a_Metas.add(a_Meta);
                    v_apActual=v_Metas.getFilePointer();
                    v_apFinal=v_Metas.length();
                }
            }catch(Exception e){
                System.out.println("Error al Leer: Error al leer el archivo: "+a_arcMetas);
                System.out.println(e.toString());
            }
            try{
                v_Metas.close();
            }catch(Exception e){
                System.out.println("Error al Leer: El archivo no se a cerrado");
                System.out.println(e.toString());
            }
        }
    }
    
    private boolean m_conMeta(){
        boolean v_Bandera=false;
        char v_Meta1,v_Meta2;
        for (int i = 0; i < a_Metas.size(); i++) {
            v_Meta1=(char)a_Metas.get(i);
            for (int j = 0; j < a_baseHechos.size(); j++) {
                a_Reglas=(ArrayList)a_baseHechos.get(j);
                v_Meta2=(char)a_Reglas.get(0);
                if(v_Meta1==v_Meta2){
                    v_Bandera=true;
                }
            }
        }
            return v_Bandera;
    }
    
    private void m_Equiparar(){
        boolean v_Bandera1,v_Bandera2;
        char v_Antecendete,v_Hecho;
        for (int i = 0; i < a_baseConocimiento.size(); i++) {
            a_Reglas = (ArrayList)a_baseConocimiento.get(i);
            a_Llave = (int)a_Reglas.get(0);
            if(a_Llave>0){
                a_noAntecedentes = (int)a_Reglas.get(1);
                v_Bandera1 = true;
                for (int j = 0; j < a_noAntecedentes; j++) {
                    v_Bandera2 = false;
                    v_Antecendete = (char)a_Reglas.get(2+j);
                    for (int k = 0; k < a_baseHechos.size(); k++) {
                        a_Hechos = (ArrayList)a_baseHechos.get(k);
                        v_Hecho = (char)a_Hechos.get(0);
                        if(v_Antecendete==v_Hecho){
                            v_Bandera2=true;
                        }
                    }
                    if(v_Bandera2==false){
                        v_Bandera1=false;
                    }
                }
                if(v_Bandera1==true){
                    a_conConflicto.add(a_Reglas);
                }
            }
        }
    }
    
    private void m_Aplicar(){
        boolean v_Bandera=true;
        char v_Antecedente,v_Hecho;
        int v_Valor;
        a_Llave = (int)a_Resolver.get(0);
        a_noAntecedentes = (int)a_Resolver.get(1);
        for (int i = 0; i < a_noAntecedentes; i++) {
            v_Antecedente = (char)a_Resolver.get(2+i);
            for (int j = 0; j < a_baseHechos.size(); j++) {
                a_Hechos = (ArrayList)a_baseHechos.get(j);
                v_Hecho = (char)a_Hechos.get(0);
                v_Valor = (int)a_Hechos.get(1);
                if(v_Antecedente==v_Hecho){
                    if(v_Valor!=1){
                        v_Bandera=false;
                    }
                }
            }
        }
        if(v_Bandera){
            a_eliRegla = a_Llave;
            a_NuevoHecho = true;
            a_Consecuente = (char)a_Resolver.get(2+a_noAntecedentes);
        }
    }
    
    private void m_actBaseHechos(){
        RandomAccessFile v_baseHechos = null;
        try{
            v_baseHechos = new RandomAccessFile(a_arcBaseHechos,"rw");
        }catch(Exception e){
            System.out.println("Error al Insertar: Error al abrir el archivo: "+a_arcBaseHechos);
            System.out.println(e.toString());
        }
        if(v_baseHechos!=null){
            try{
                a_Hecho=a_Consecuente;
                a_Valor=1;
                v_baseHechos.seek(v_baseHechos.length());
                v_baseHechos.writeChar(a_Hecho);
                v_baseHechos.writeInt(a_Valor);
            }catch(Exception e){
                System.out.println("Error al Insertar: Valor no Valido");
                System.out.println(e.toString());
            }
            try{
                v_baseHechos.close();
            }catch(Exception e){
                System.out.println("Error al Insertar: El archivo no se a cerrado");
                System.out.println(e.toString());
            }
        }
    }
    
    private void m_eliRegla(){
        for (int i = 0; i < a_baseConocimiento.size(); i++) {
            a_Reglas = (ArrayList)a_baseConocimiento.get(i);
            a_Llave = (int)a_Reglas.get(0);
            if(a_Llave==a_eliRegla){
                a_Reglas.set(0,-1);
                a_baseConocimiento.set(i,a_Reglas);
            }
        }
    }
}


