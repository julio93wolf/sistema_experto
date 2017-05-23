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

    private c_menu o_Menu;
    
    public c_principal() {
        o_Menu=new c_menu();
        o_Menu.m_MenuPrincipal();
    }
    
    public static void main(String[] args) {
        c_principal o_principal=new c_principal();
    }
    
}
