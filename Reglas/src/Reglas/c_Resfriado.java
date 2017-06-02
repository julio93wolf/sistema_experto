package Reglas;


import java.io.IOException;
import java.io.PrintWriter;

public class c_Resfriado {
    
    private int a_Reglas[];
    private int a_Fracasos=0;
    private int a_Resfriados=0;
    private PrintWriter a_Escritor=null;

    public c_Resfriado() {
        a_Reglas = new int[11];
        m_Ciclos();
    }
    
    void m_Ciclos(){
        try{
            a_Escritor=new PrintWriter("src/Reglas/resfriado.pl");
        }
        catch(IOException e){
            System.out.println(e.toString());
        }
        if(a_Escritor!=null){
            for (int i = 0; i < 2; i++) {
                a_Reglas[0] = i;
                for (int j = 0; j < 2; j++) {
                    a_Reglas[1] = j;
                    for (int k = 0; k < 2; k++) {
                        a_Reglas[2] = k;
                        for (int l = 0; l < 2; l++) {
                            a_Reglas[3] = l;
                            for (int m = 0; m < 2; m++) {
                                a_Reglas[4] = m;
                                for (int n = 0; n < 2; n++) {
                                    a_Reglas[5] = n;
                                    for (int o = 0; o < 2; o++) {
                                        a_Reglas[6] = o;
                                        for (int p = 0; p < 2; p++) {
                                            a_Reglas[7] = p;
                                            for (int q = 0; q < 2; q++) {
                                                a_Reglas[8] = q;
                                                for (int r = 0; r < 2; r++) {
                                                    a_Reglas[9] = r;
                                                    for (int s = 0; s < 2; s++) {
                                                        a_Reglas[10] = s;
                                                        m_CuentaValores();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            a_Escritor.close();
        }
        System.out.println("\n\tResfriados");
        System.out.println("Resfriados: "+a_Resfriados);
        System.out.println("Fracasos: "+a_Fracasos);
        System.out.println("Total: "+(a_Resfriados+a_Fracasos));
    }
    
    private void m_CuentaValores(){
        int v_Contador=0;
        for (int i = 1; i < a_Reglas.length; i++) {
            if(a_Reglas[i]==1){
                v_Contador++;
            }
        }
        if(v_Contador>=3){
            //Resfriado
            m_Resfriado();
            a_Resfriados++;
        }else{
            //Fracaso
            m_Fracaso();
            a_Fracasos++;
        }
    }
    
    private void m_Resfriado(){
        if(a_Reglas[0]==0){
            m_Adulto();
        }else{
            m_Ninio();
        }
    }
    
    private void m_Adulto(){
        String v_Regla="";
        try{
            v_Regla+="preguntas_resfriado(P1,P2,P3,P4,P5,P6,P7,P8,P9,P10,P11):-";
            v_Regla+="pregunta('persona_mayor_de_12_anios',P1),P1='"+m_SiNo(1)+"',";
            v_Regla+="pregunta('tiene_dolor_de_cabeza',P2),P2='"+m_SiNo(a_Reglas[1])+"',";
            v_Regla+="pregunta('tiene_congestion_nasal',P3),P3='"+m_SiNo(a_Reglas[2])+"',";
            v_Regla+="pregunta('tiene_dolor_de_garganta',P4),P4='"+m_SiNo(a_Reglas[3])+"',";
            v_Regla+="pregunta('tiene_dolor_muscular',P5),P5='"+m_SiNo(a_Reglas[4])+"',";
            v_Regla+="pregunta('tiene_dolor_articular',P6),P6='"+m_SiNo(a_Reglas[5])+"',";
            v_Regla+="pregunta('siente_debilidad_o_fatiga',P7),P7='"+m_SiNo(a_Reglas[6])+"',";
            v_Regla+="pregunta('tiene_dificultad_para_respirar',P8),P8='"+m_SiNo(a_Reglas[7])+"',";
            v_Regla+="pregunta('tiene_diarrea',P9),P9='"+m_SiNo(a_Reglas[8])+"',";
            v_Regla+="pregunta('presenta_escalofrios',P10),P10='"+m_SiNo(a_Reglas[9])+"',";
            v_Regla+="pregunta('presenta_tos',P11),P11='"+m_SiNo(a_Reglas[10])+"',";
            v_Regla+="adulto.\n";
            a_Escritor.write(v_Regla);
        }catch(Exception e){}
    }
    
    private void m_Ninio(){
        String v_Regla="";
        try{
            v_Regla+="preguntas_resfriado(P1,P2,P3,P4,P5,P6,P7,P8,P9,P10,P11):-";
            v_Regla+="pregunta('persona_mayor_de_12_anios',P1),P1='"+m_SiNo(0)+"',";
            v_Regla+="pregunta('tiene_dolor_de_cabeza',P2),P2='"+m_SiNo(a_Reglas[1])+"',";
            v_Regla+="pregunta('tiene_congestion_nasal',P3),P3='"+m_SiNo(a_Reglas[2])+"',";
            v_Regla+="pregunta('tiene_dolor_de_garganta',P4),P4='"+m_SiNo(a_Reglas[3])+"',";
            v_Regla+="pregunta('tiene_dolor_muscular',P5),P5='"+m_SiNo(a_Reglas[4])+"',";
            v_Regla+="pregunta('tiene_dolor_articular',P6),P6='"+m_SiNo(a_Reglas[5])+"',";
            v_Regla+="pregunta('siente_debilidad_o_fatiga',P7),P7='"+m_SiNo(a_Reglas[6])+"',";
            v_Regla+="pregunta('tiene_dificultad_para_respirar',P8),P8='"+m_SiNo(a_Reglas[7])+"',";
            v_Regla+="pregunta('tiene_diarrea',P9),P9='"+m_SiNo(a_Reglas[8])+"',";
            v_Regla+="pregunta('presenta_escalofrios',P10),P10='"+m_SiNo(a_Reglas[9])+"',";
            v_Regla+="pregunta('presenta_tos',P11),P11='"+m_SiNo(a_Reglas[10])+"',";
            v_Regla+="nino.\n";
            a_Escritor.write(v_Regla);
        }catch(Exception e){}
    }
    
    private void m_Fracaso(){
        String v_Regla="";
        try{
            v_Regla+="preguntas_resfriado(P1,P2,P3,P4,P5,P6,P7,P8,P9,P10,P11):-";
            v_Regla+="pregunta('persona_mayor_de_12_anios',P1),P1='"+m_SiNo(a_Reglas[0])+"',";
            v_Regla+="pregunta('tiene_dolor_de_cabeza',P2),P2='"+m_SiNo(a_Reglas[1])+"',";
            v_Regla+="pregunta('tiene_congestion_nasal',P3),P3='"+m_SiNo(a_Reglas[2])+"',";
            v_Regla+="pregunta('tiene_dolor_de_garganta',P4),P4='"+m_SiNo(a_Reglas[3])+"',";
            v_Regla+="pregunta('tiene_dolor_muscular',P5),P5='"+m_SiNo(a_Reglas[4])+"',";
            v_Regla+="pregunta('tiene_dolor_articular',P6),P6='"+m_SiNo(a_Reglas[5])+"',";
            v_Regla+="pregunta('siente_debilidad_o_fatiga',P7),P7='"+m_SiNo(a_Reglas[6])+"',";
            v_Regla+="pregunta('tiene_dificultad_para_respirar',P8),P8='"+m_SiNo(a_Reglas[7])+"',";
            v_Regla+="pregunta('tiene_diarrea',P9),P9='"+m_SiNo(a_Reglas[8])+"',";
            v_Regla+="pregunta('presenta_escalofrios',P10),P10='"+m_SiNo(a_Reglas[9])+"',";
            v_Regla+="pregunta('presenta_tos',P11),P11='"+m_SiNo(a_Reglas[10])+"',";
            v_Regla+="fracaso.\n";
            a_Escritor.write(v_Regla);
        }catch(Exception e){}
    }
    
    private String m_SiNo(int p_Estado){
        String v_Resultado="si";
        if(p_Estado==0){
            v_Resultado="no";
        }   
        return v_Resultado;
    }
    
    public static void main(String[] args) {
        c_Resfriado o_Resfriado = new c_Resfriado();
        c_influenzaAdulto o_inflAdulto = new c_influenzaAdulto();
        c_influenzaNinio o_inflNiño = new c_influenzaNinio();
    }
}
