/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_experto;

/**
 *
 * @author ValleRo
 */
public class c_principal {

    private c_modActualizacion o_modActualizacion;
    
    public c_principal() {
        o_modActualizacion = new c_modActualizacion();
        //o_modActualizacion.m_IngresaBC();
        //o_modActualizacion.m_LeeBC();
        //o_modActualizacion.m_ModificaBC();
        o_modActualizacion.m_LeeBC();
        o_modActualizacion.m_EliminarBC();
        o_modActualizacion.m_LeeBC();
    }
    
    public static void main(String[] args) {
        c_principal o_principal=new c_principal();
    }
    
}
