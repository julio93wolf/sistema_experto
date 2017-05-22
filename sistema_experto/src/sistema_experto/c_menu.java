package sistema_experto;

import java.util.Scanner;

public class c_menu {
    
    private c_modActualizacion o_modActualizacion;
    
    c_menu(){
        o_modActualizacion = new c_modActualizacion();
    }
    
    public void m_Menu(){
        Scanner v_Entrada;
        int v_Opcion = 0;
        do{
            try{
                v_Entrada = new Scanner(System.in);
                System.out.println("\n\tMenú\n");
                System.out.println("[1] Agrega Regla\t[BC]");
                System.out.println("[2] Mostrar Reglas\t[BC]");
                System.out.println("[3] Modifica Regla\t[BC]");
                System.out.println("[4] Eliminar Regla\t[BC]");
                System.out.println("[5] Agregar Hecho\t[BH]");
                System.out.println("[6] Mostrar Hecho\t[BH]");
                System.out.println("[7] Eliminar Hecho\t[BH]");
                System.out.println("[8] Encadenamiento Hacia Delante");
                System.out.println("[9] Salir");
                System.out.print("Opción: ");
                v_Opcion = v_Entrada.nextInt();
                if(v_Opcion>0 && v_Opcion<10)
                    m_Opcion(v_Opcion);
                else
                    System.out.println("Error: Valor fuera de rango");
            }catch(Exception e){
                System.out.println("Error: Valor invalido");
            }
        }while(v_Opcion != 9);
    }
    
    private void m_Opcion(int p_Opcion){
        
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
            case 5:{
                o_modActualizacion.m_InsertarBH();
                break;
            }
            case 6:{
                o_modActualizacion.m_MostrarBH();
                break;
            }
            case 7:{
                
                break;
            }
        }
    } // Fin del método m_Opcion
}
