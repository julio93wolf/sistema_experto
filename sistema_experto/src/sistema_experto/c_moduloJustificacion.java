package sistema_experto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class c_moduloJustificacion {
    
    private ArrayList a_Tratamiento;
    private char a_caraTratamiento;
    private String a_descTratamiento;
    
    public void m_AgregarTratemiento(char p_caraTratamiento,String p_descTratamiento){
        String v_Opcion="";
        ObjectInputStream v_inputTratamiento;
        ObjectOutputStream v_outputTratamiento = null;
        ArrayList v_Tratamiento;
        a_Tratamiento = null;
        try{
            v_inputTratamiento = new ObjectInputStream(new FileInputStream("src/files/tratamiento.dat"));
            a_Tratamiento = (ArrayList)v_inputTratamiento.readObject();
            //v_inputSintomas.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }
        if(a_Tratamiento==null){
            a_Tratamiento = new ArrayList();
        }
        try{
            v_outputTratamiento = new ObjectOutputStream(new FileOutputStream("src/files/tratamiento.dat"));
        }catch(Exception e){
            System.out.println(e.toString());
        }
        if(v_outputTratamiento!=null){
            try{
                v_Opcion="1";
                v_Tratamiento = new ArrayList();
                a_caraTratamiento = p_caraTratamiento;
                a_descTratamiento = p_descTratamiento;
                v_Tratamiento.add(a_caraTratamiento);
                v_Tratamiento.add(a_descTratamiento);
                a_Tratamiento.add(v_Tratamiento);
            }catch(Exception e){
                System.out.println(e.toString());
            }
            try{
                v_outputTratamiento.writeObject(a_Tratamiento);
                JOptionPane.showMessageDialog(null,"Se agrego tratamiento","Tratamiento",JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"No se pudo agregar tratamiento","Error",JOptionPane.ERROR_MESSAGE);
                System.out.println(e.toString());
            }
        }
    }
    
    public String m_BuscaTratamiento(char p_Tratamiento){
        char v_caraTratamiento;
        String v_descTratamiento=""+p_Tratamiento;
        ObjectInputStream v_inputTratamiento;
        a_Tratamiento = null;
        ArrayList v_Tratamiento;
        try{
            v_inputTratamiento = new ObjectInputStream(new FileInputStream("src/files/tratamiento.dat"));
            a_Tratamiento = (ArrayList)v_inputTratamiento.readObject();
        }catch(Exception e){
            System.out.println(e.toString());
        }
        if(a_Tratamiento!=null){
            for (int i = 0; i < a_Tratamiento.size(); i++) {
                v_Tratamiento = (ArrayList) a_Tratamiento.get(i);
                v_caraTratamiento = (char)v_Tratamiento.get(0);
                if(v_caraTratamiento == p_Tratamiento){
                    v_descTratamiento = (String)v_Tratamiento.get(1);
                }
            }
        }
        return v_descTratamiento;
    }
}
