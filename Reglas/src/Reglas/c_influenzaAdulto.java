package Reglas;

import java.io.IOException;
import java.io.PrintWriter;

public class c_influenzaAdulto {
    private int a_Reglas[];
    private int a_tipoC=0;
    private int a_tipoB=0;
    private int a_tipoD=0;
    private int a_comun=0;
    private PrintWriter a_Escritor=null;

    public c_influenzaAdulto() {
        a_Reglas = new int[12];
        m_Ciclos();
    }
    
    void m_Ciclos(){
        try{
            a_Escritor=new PrintWriter("src/Reglas/influenzaAdulto.pl");
        }
        catch(IOException e){
            System.out.println(e.toString());
        }
        if(a_Escritor!=null){
            for (int i = 0; i < 2; i++) {
                //pregunta('presenta_mas_cansancio_de_lo_habitual',P15)
                a_Reglas[0] = i;
                for (int j = 0; j < 2; j++) {
                    //pregunta('presenta_estornudos_recurrentes',P16),P16='si',
                    a_Reglas[1] = j;
                    for (int k = 0; k < 2; k++) {
                        //pregunta('ha_estado_postrado_en_cama_de_1_a_3_semanas',P17),P17='si',
                        a_Reglas[2] = k;
                        for (int l = 0; l < 2; l++) {
                            //pregunta('tiene_vomito',P18),P18='si',
                            a_Reglas[3] = l;
                            for (int m = 0; m < 2; m++) {
                                //pregunta('tiene_escurrimiento_nasal',P19),P19='no',
                                a_Reglas[4] = m;
                                for (int n = 0; n < 2; n++) {
                                    //pregunta('tiene_fiebre_de_41_grados_o_mas',P20),P20='no',
                                    a_Reglas[5] = n;
                                    for (int o = 0; o < 2; o++) {
                                        //pregunta('tiene_ojos_llorosos',P21),P21='no',
                                        a_Reglas[6] = o;
                                        for (int p = 0; p < 2; p++) {
                                            //pregunta('presenta_salpullido',P22),P22='no',
                                            a_Reglas[7] = p;                                            
                                            for (int q = 0; q < 2; q++) {
                                                //pregunta('tiene_falta_de_apetito',P23),P23='no',
                                                a_Reglas[8] = q;
                                                for (int r = 0; r < 2; r++) {
                                                    //pregunta('presenta_dolor_abdominal',P24),P24='no',
                                                    a_Reglas[9] = r;
                                                    for (int s = 0; s < 2; s++) {
                                                        //pregunta('presenta_dolor_oseo',P25),P25='no',
                                                        a_Reglas[10] = s;
                                                        for (int t = 0; t < 2; t++) {
                                                            //pregunta('presenta_neumonia',P26),P26='no',
                                                            a_Reglas[11]=t;
                                                            m_Identifica();
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
            }
            a_Escritor.close();
        }
        System.out.println("\n\tInfluenza Adultos");
        System.out.println("Influenza tipo C: "+a_tipoC);
        System.out.println("Influenza tipo B: "+a_tipoB);
        System.out.println("Resfriado común: "+a_comun);
        System.out.println("Influenza desconocida: "+a_tipoD);
        System.out.println("Total: "+(a_tipoC+a_tipoB+a_comun+a_tipoD));
    }
    
    private void m_Identifica(){
        if(a_Reglas[5]==1&&a_Reglas[10]==1&&a_Reglas[11]==1){
            m_Tipo("influenzaC");
            a_tipoC++;
        }else{
            if(a_Reglas[5]==0&&a_Reglas[2]==1&&a_Reglas[3]==1){
                m_Tipo("influenzaB");
                a_tipoB++;
            }else{
                if(m_Resfriado()){
                    m_Tipo("resfriadoC");
                    a_comun++;
                }else{
                    m_Tipo("influenzaD");
                    a_tipoD++;
                }
            }
        }
    }
    
    boolean m_Resfriado(){
        boolean v_Bandera=true;
        int v_Contador=0;
        for (int i = 0; i < a_Reglas.length; i++) {
            if(a_Reglas[i]==1){
                v_Contador++;
            }
        }
        if(v_Contador>=2){
            v_Bandera=false;
        }
        return v_Bandera;
    }
    
    private void m_Tipo(String p_Tipo){
        String v_Regla="";
        try{
            v_Regla+="preguntas_influenza(P15,P16,P17,P18,P19,P20,P21,P22,P23,P24,P25,P26):-";
            v_Regla+="pregunta('presenta_mas_cansancio_de_lo_habitual',P15),P15='"+m_SiNo(a_Reglas[0])+"',";
            v_Regla+="pregunta('presenta_estornudos_recurrentes',P16),P16='"+m_SiNo(a_Reglas[1])+"',";
            v_Regla+="pregunta('ha_estado_postrado_en_cama_de_1_a_3_semanas',P17),P17='"+m_SiNo(a_Reglas[2])+"',";
            v_Regla+="pregunta('tiene_vomito',P18),P18='"+m_SiNo(a_Reglas[3])+"',";
            v_Regla+="pregunta('tiene_escurrimiento_nasal',P19),P19='"+m_SiNo(a_Reglas[4])+"',";
            v_Regla+="pregunta('tiene_fiebre_de_41_grados_o_mas',P20),P20='"+m_SiNo(a_Reglas[5])+"',";
            v_Regla+="pregunta('tiene_ojos_llorosos',P21),P21='"+m_SiNo(a_Reglas[6])+"',";
            v_Regla+="pregunta('presenta_salpullido',P22),P22='"+m_SiNo(a_Reglas[7])+"',";
            v_Regla+="pregunta('tiene_falta_de_apetito',P23),P23='"+m_SiNo(a_Reglas[8])+"',";
            v_Regla+="pregunta('presenta_dolor_abdominal',P24),P24='"+m_SiNo(a_Reglas[9])+"',";
            v_Regla+="pregunta('presenta_dolor_oseo',P25),P25='"+m_SiNo(a_Reglas[10])+"',";
            v_Regla+="pregunta('presenta_neumonia',P26),P26='"+m_SiNo(a_Reglas[11])+"',";
            v_Regla+=""+p_Tipo+".\n";
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
    
}
