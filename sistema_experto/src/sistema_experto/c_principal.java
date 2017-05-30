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

import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;
import javax.swing.UIManager;

public class c_principal {

    private c_interfazUsuario o_iu;
    /**
     * @name: c_principal
     * @description: Constructor de la clase c_principal
     */
    public c_principal() {
        o_iu=new c_interfazUsuario();// Implementación de la interfaz de usuario del proyecto. 
        o_iu.setVisible(true);
    }
    
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(new SyntheticaPlainLookAndFeel());         //Carga el tema look and feel
        }catch (Exception e){
            e.printStackTrace();
        }
        c_principal o_principal=new c_principal();
    }
}
