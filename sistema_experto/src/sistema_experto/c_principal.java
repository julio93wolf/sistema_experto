/**
 * @name: c_principal.java
 * @description: Clase principal del proyecto
 * @version 17.5.25
 * @author: 
 * @author: 
 * @author: Sanchez Martínez Humberto
 * @author: Valle Rodríguez Julio Cesar
 */
package sistema_experto;

public class c_principal {

    private c_menu o_Menu;          // Objeto de la clase c_Menú
    
    /**
     * @name: c_principal
     * @description: Constructor de la clase c_principal
     */
    public c_principal() {
        o_Menu=new c_menu();        // Implementación del menú del proyecto 
    }
    
    public static void main(String[] args) {
        c_principal o_principal=new c_principal();
    }
}
