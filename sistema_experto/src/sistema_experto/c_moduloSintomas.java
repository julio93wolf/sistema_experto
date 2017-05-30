package sistema_experto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class c_moduloSintomas {
    
    private FileOutputStream a_archOutputSintomas = null;
    private FileInputStream a_archInputSintomas = null;
    private c_sintoma o_sintoma;
    private ArrayList a_Sintomas;
    private char a_caraSintoma;
    private String a_Sintoma;

    public c_moduloSintomas() {
        
    }
    
    public void m_AgregarSintoma(){
        String v_Opcion="";
        ObjectInputStream v_inputSintomas;
        ObjectOutputStream v_outputSintomas = null;
        ArrayList v_Sintoma;
        a_Sintomas = null;
        try{
            v_inputSintomas = new ObjectInputStream(new FileInputStream("src/files/sintomas.dat"));
            a_Sintomas = (ArrayList)v_inputSintomas.readObject();
            //v_inputSintomas.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }
        if(a_Sintomas==null){
            a_Sintomas = new ArrayList();
        }
        try{
            v_outputSintomas = new ObjectOutputStream(new FileOutputStream("src/files/sintomas.dat"));
        }catch(Exception e){
            System.out.println(e.toString());
        }
        if(v_outputSintomas!=null){
            do{
                try{
                    v_Opcion="1";
                    v_Sintoma = new ArrayList();
                    a_caraSintoma = JOptionPane.showInputDialog("Caracter: ").charAt(0);
                    a_Sintoma = JOptionPane.showInputDialog("Sintoma: ");
                    v_Sintoma.add(a_caraSintoma);
                    v_Sintoma.add(a_Sintoma);
                    a_Sintomas.add(v_Sintoma);
                    v_Opcion=JOptionPane.showInputDialog("¿Desea agregar otra regla?\n[Si]=1\n[No]=Cualquier tecla\nOpcion: ");
                }catch(Exception e){
                    System.out.println(e.toString());
                }
            }while(v_Opcion.equals("1"));
            try{
                v_outputSintomas.writeObject(a_Sintomas);
                //v_outputSintomas.close();
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
    }
    
    public String m_BuscaSintoma(char p_Sintoma){
        char v_caraSintoma;
        String v_Sintoma=""+p_Sintoma;
        ObjectInputStream v_inputSintomas;
        a_Sintomas = null;
        ArrayList v_Sintomas;
        try{
            v_inputSintomas = new ObjectInputStream(new FileInputStream("src/files/sintomas.dat"));
            a_Sintomas = (ArrayList)v_inputSintomas.readObject();
        }catch(Exception e){
            System.out.println(e.toString());
        }
        if(a_Sintomas!=null){
            for (int i = 0; i < a_Sintomas.size(); i++) {
                v_Sintomas = (ArrayList) a_Sintomas.get(i);
                v_caraSintoma = (char)v_Sintomas.get(0);
                if(v_caraSintoma == p_Sintoma){
                    v_Sintoma = (String)v_Sintomas.get(1);
                }
            }
        }
        return v_Sintoma;
    }
    
    public void m_MostrarSintomas(){
        char v_caraSintoma;
        String v_Sintoma="";
        ObjectInputStream v_inputSintomas;
        a_Sintomas = null;
        ArrayList v_Sintomas;
        try{
            v_inputSintomas = new ObjectInputStream(new FileInputStream("src/files/sintomas.dat"));
            a_Sintomas = (ArrayList)v_inputSintomas.readObject();
        }catch(Exception e){
            System.out.println(e.toString());
        }
        if(a_Sintomas!=null){
            for (int i = 0; i < a_Sintomas.size(); i++) {
                v_Sintomas = (ArrayList) a_Sintomas.get(i);
                v_caraSintoma=(char)v_Sintomas.get(0);
                v_Sintoma += ""+v_caraSintoma+" : "+(String)v_Sintomas.get(1)+"\n";
            }
        }
        JOptionPane.showMessageDialog(null,v_Sintoma,"Sintomas",JOptionPane.INFORMATION_MESSAGE);
    }
}
