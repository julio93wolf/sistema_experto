/**
 * @name: c_menu.java
 * @description: Menú principal del proyecto
 * @version 17.5.25
 * @author: Valle Rodríguez Julio Cesar
 */
package sistema_experto;
import java.util.Scanner;

public class c_menu {
    
    private c_modActualizacion o_modActualizacion;      // Objeto de la clase del módulo de actualización
    private c_motInferencia o_motInferencia;            // Objeto de la clase del motor de inferencia
    
    /**
     * @name: c_menu
     * @description: Constructor de la clase c_menu
     */
    c_menu(){
        o_modActualizacion = new c_modActualizacion();  // Implementación del módulo de actualización
        o_motInferencia = new c_motInferencia();        // Implementacion del módulo de inferencia
        m_menuPrincipal();                              // Ejecución del menú de opciones
    }// Fin del constructor de la clase c_menu
    
    /**
     * @name: m_menuPrincipal
     * @description: Método del menú de opciones principales
     */
    private void m_menuPrincipal(){
        Scanner v_Entrada;
        int v_Opcion = 0;
        do{
            try{
                v_Entrada = new Scanner(System.in);
                System.out.println("\n\tMenú\n");
                System.out.println("[1] Modulo de Actualización");
                System.out.println("[2] Metas");
                System.out.println("[3] Inferir");
                System.out.println("[4] Salir");
                System.out.print("Opción: ");
                v_Opcion = v_Entrada.nextInt();
                if(v_Opcion>0 && v_Opcion<5)
                    m_opcPrincipal(v_Opcion);
                else
                    System.out.println("m_menuPrincipal: Error valor fuera de rango");
            }catch(Exception e){
                System.out.println("m_menuPrincipal: Error valor invalido");
            }
        }while(v_Opcion != 4);
    }// Fin del método m_menuPrincipal
    
    /**
     * @name: m_opcPrincipal
     * @description: Método con las opciones del menú principal
     * @param p_Opcion Parámetro de la opcion del menú
     */
    private void m_opcPrincipal(int p_Opcion){
        
        switch(p_Opcion){
            case 1:{
                m_menuModuloActualizacion();
                break;
            }
            case 2:{
                break;
            }
            case 3:{
                m_menuMetas();
                break;
            }
            case 4:{
                o_motInferencia.m_encAdelante();
                break;
            }
        }
    } // Fin del método m_opcPrincipal
    
    /**
     * @name: m_menuModuloActualizacion
     * @description: Método con el menú del módulo de actualización
     */
    private void m_menuModuloActualizacion(){
        Scanner v_Entrada;
        int v_Opcion = 0;
        do{
            try{
                v_Entrada = new Scanner(System.in);
                System.out.println("\n[1] Agregar Regla\t[BC]");
                System.out.println("[2] Mostrar Reglas\t[BC]");
                System.out.println("[3] Modifica Regla\t[BC]");
                System.out.println("[4] Eliminar Regla\t[BC]");
                System.out.print("Opción: ");
                v_Opcion = v_Entrada.nextInt();
                if(v_Opcion>0 && v_Opcion<5)
                    m_opcModuloActualizacion(v_Opcion);
                else
                    System.out.println("m_menuModuloActualizacion: Error valor fuera de rango");
            }catch(Exception e){
                System.out.println("m_menuModuloActualizacion: Error valor invalido");
            }
        }while(v_Opcion<=0 && v_Opcion>=5);
    } // Fin del método m_menuModuloActualizacion
    
    /**
     * @name: m_opcModuloActualizacion
     * @description: Método con las opciones del módulo de actualización
     * @param p_Opcion Parámetro de la opcion del menú 
     */
    private void m_opcModuloActualizacion(int p_Opcion){
        switch(p_Opcion){
            case 1:{
                o_modActualizacion.m_insertarBaseConocimiento();
                break;
            }
            case 2:{
                o_modActualizacion.m_mostrarBaseConocimiento();
                break;
            }
            case 3:{
                o_modActualizacion.m_modificaBaseConocimiento();
                break;
            }
            case 4:{
                o_modActualizacion.m_eliminarBaseConocimiento();
                break;
            }
        }
    }// Fin del método m_opcModuloActualizacion
    
    /**
     * @name: m_menuMetas
     * @description: Método con el menú de opciones de las metas
     */
    private void m_menuMetas(){
        Scanner v_Entrada;
        int v_Opcion = 0;
        do{
            try{
                v_Entrada = new Scanner(System.in);
                System.out.println("\n[1] Agregar Meta");
                System.out.println("[2] Mostrar Metas");
                System.out.println("[3] Vaciar Metas");
                System.out.print("Opción: ");
                v_Opcion = v_Entrada.nextInt();
                if(v_Opcion>0 && v_Opcion<4)
                    m_opcMetas(v_Opcion);
                else
                    System.out.println("m_menuMetas: Error valor fuera de rango");
            }catch(Exception e){
                System.out.println("m_menuMetas: Error valor invalido");
            }
        }while(v_Opcion<=0 && v_Opcion>=4);
    }// Fin del método m_menuMetas

    /**
     * @name: m_opcMetas
     * @description: Método con las opciones del menú de metas
     * @param p_Opcion Parámetro de la opcion del menú 
     */
    private void m_opcMetas(int p_Opcion){
        switch(p_Opcion){
            case 1:{
                o_modActualizacion.m_insertarMeta();
                break;
            }
            case 2:{
                o_modActualizacion.m_mostrarMetas();
                break;
            }
            case 3:{
                o_modActualizacion.m_vaciarMetas();
                break;
            }
        }
    }// Fin del método m_opcMetas
}
