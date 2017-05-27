/**
 * @name: c_principal.java
 * @description: Clase principal del proyecto
 * @version 17.5.25
 * @author: 
 * @author: 
 * @author: Sanchez Martínez Humberto.
 * @author: Valle Rodríguez Julio Cesar.
 */
package sistema_experto;

public class c_principal {

    InterfazUsuario o_iu;
    /**
     * @name: c_principal
     * @description: Constructor de la clase c_principal
     */
    public c_principal() {
        o_iu=new InterfazUsuario(); // Implementación de la interfaz de usuario del proyecto. 
    }
    
    public static void main(String[] args) {
       c_principal o_principal=new c_principal();
    }
}
