package sistema_experto;

import java.util.Scanner;

public class c_menu {
    
    private c_modActualizacion o_modActualizacion;
    
    c_menu(){
        o_modActualizacion = new c_modActualizacion();
    }
    
    public void m_MenuPrincipal(){
        Scanner v_Entrada;
        int v_Opcion = 0;
        do{
            try{
                v_Entrada = new Scanner(System.in);
                System.out.println("\n\tMenú\n");
                System.out.println("[1] Base de Conocimiento");
                System.out.println("[2] Base de Hechos");
                System.out.println("[3] Metas");
                System.out.println("[4] Encadenamiento Hacia Delante");
                System.out.println("[5] Salir");
                System.out.print("Opción: ");
                v_Opcion = v_Entrada.nextInt();
                if(v_Opcion>0 && v_Opcion<6)
                    m_opcPrincipal(v_Opcion);
                else
                    System.out.println("Error: Valor fuera de rango");
            }catch(Exception e){
                System.out.println("Error: Valor invalido");
            }
        }while(v_Opcion != 5);
    }
    
    private void m_opcPrincipal(int p_Opcion){
        
        switch(p_Opcion){
            case 1:{
                m_MenuBC();
                break;
            }
            case 2:{
                m_MenuBH();
                break;
            }
            case 3:{
                m_MenuM();
                break;
            }
            case 4:{
                
                break;
            }
        }
    } // Fin del método m_Opcion
    
    public void m_MenuBC(){
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
                    m_opcBaseConocimiento(v_Opcion);
                else
                    System.out.println("Error: Valor fuera de rango");
            }catch(Exception e){
                System.out.println("Error: Valor invalido");
            }
        }while(v_Opcion<=0 && v_Opcion>=5);
    }
    
    public void m_opcBaseConocimiento(int p_Opcion){
        switch(p_Opcion){
            case 1:{
                o_modActualizacion.m_InsertarBC();
                break;
            }
            case 2:{
                o_modActualizacion.m_MostrarBC();
                break;
            }
            case 3:{
                o_modActualizacion.m_ModificaBC();
                break;
            }
            case 4:{
                o_modActualizacion.m_EliminarBC();
                break;
            }
        }
    }
    
    public void m_MenuBH(){
        Scanner v_Entrada;
        int v_Opcion = 0;
        do{
            try{
                v_Entrada = new Scanner(System.in);
                System.out.println("\n[1] Agregar Hecho\t[BH]");
                System.out.println("[2] Mostrar Hecho\t[BH]");
                System.out.println("[3] Vaciar Hechos\t[BH]");
                System.out.print("Opción: ");
                v_Opcion = v_Entrada.nextInt();
                if(v_Opcion>0 && v_Opcion<4)
                    m_opcBaseHechos(v_Opcion);
                else
                    System.out.println("Error: Valor fuera de rango");
            }catch(Exception e){
                System.out.println("Error: Valor invalido");
            }
        }while(v_Opcion<=0 && v_Opcion>=4);
    }

    public void m_opcBaseHechos(int p_Opcion){
        switch(p_Opcion){
            case 1:{
                o_modActualizacion.m_InsertarBH();
                break;
            }
            case 2:{
                o_modActualizacion.m_MostrarBH();
                break;
            }
            case 3:{
                o_modActualizacion.m_VaciarBH();
                break;
            }
        }
    }
    
    public void m_MenuM(){
        Scanner v_Entrada;
        int v_Opcion = 0;
        do{
            try{
                v_Entrada = new Scanner(System.in);
                System.out.println("\n[1] Agregar Meta\t[BH]");
                System.out.println("[2] Mostrar Metas\t[BH]");
                System.out.println("[3] Vaciar Metas\t[BH]");
                System.out.print("Opción: ");
                v_Opcion = v_Entrada.nextInt();
                if(v_Opcion>0 && v_Opcion<4)
                    m_opcMetas(v_Opcion);
                else
                    System.out.println("Error: Valor fuera de rango");
            }catch(Exception e){
                System.out.println("Error: Valor invalido");
            }
        }while(v_Opcion<=0 && v_Opcion>=4);
    }

    public void m_opcMetas(int p_Opcion){
        switch(p_Opcion){
            case 1:{
                o_modActualizacion.m_InsertarM();
                break;
            }
            case 2:{
                o_modActualizacion.m_MostrarM();
                break;
            }
            case 3:{
                o_modActualizacion.m_VaciarM();
                break;
            }
        }
    }
}
