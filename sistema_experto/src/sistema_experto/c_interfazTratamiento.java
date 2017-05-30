package sistema_experto;

import javax.swing.ImageIcon;

public class c_interfazTratamiento extends javax.swing.JFrame {

    private c_moduloJustificacion o_modJustificacion;
    
    public c_interfazTratamiento() {
        o_modJustificacion = new c_moduloJustificacion();
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("../imagenes/img_icono.png")).getImage());// Nos permite modificar el icono de la ventana.
        setLocationRelativeTo(null);   
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        a_lblCaracter = new javax.swing.JLabel();
        a_txtfCaracter = new javax.swing.JTextField();
        a_scrlTratamiento = new javax.swing.JScrollPane();
        a_txtaTratamiento = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        a_btnGuardar = new javax.swing.JButton();

        setTitle("Tratamiento");
        setResizable(false);

        a_lblCaracter.setText("Carácter:");

        a_txtaTratamiento.setColumns(20);
        a_txtaTratamiento.setRows(5);
        a_scrlTratamiento.setViewportView(a_txtaTratamiento);

        jLabel1.setText("Tratamiento:");

        a_btnGuardar.setText("Guardar");
        a_btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(a_txtfCaracter, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(a_lblCaracter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(356, 356, 356))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(a_scrlTratamiento)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(a_btnGuardar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(a_lblCaracter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(a_txtfCaracter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(a_scrlTratamiento, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(a_btnGuardar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void a_btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_btnGuardarActionPerformed
        char v_caraTratamiento = a_txtfCaracter.getText().charAt(0);
        String v_descTratamiento = a_txtaTratamiento.getText();
        o_modJustificacion.m_AgregarTratemiento(v_caraTratamiento,v_descTratamiento);
        setVisible(false);
    }//GEN-LAST:event_a_btnGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton a_btnGuardar;
    private javax.swing.JLabel a_lblCaracter;
    private javax.swing.JScrollPane a_scrlTratamiento;
    private javax.swing.JTextArea a_txtaTratamiento;
    private javax.swing.JTextField a_txtfCaracter;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
