/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SugerenciasForm.java
 *
 * Created on 05-dic-2011, 17:33:27
 */
package com.geopista.app.sugerencias;

import org.apache.log4j.Logger;

import com.geopista.app.AppContext;
import com.vividsolutions.jump.util.StringUtil;
import com.vividsolutions.jump.workbench.ui.ErrorDialog;


public class SugerenciasForm extends javax.swing.JFrame {

	Logger logger=Logger.getLogger(SugerenciasForm.class);
	
	/**
	 * Detalle de error proporcionado por ErrorDialog
	 */
	String details="";
	
    public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
		sugerenciasPanel1.setExternalDescription(details);
		sugerenciasPanel1.setExternalType("INCIDENCIA");
	}

	/** Creates new form SugerenciasForm */
    public SugerenciasForm() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        sugerenciasPanel1 = new com.geopista.app.sugerencias.SugerenciasPanel();
        jbAceptar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Registro sugerencias/incidencias");

        jbAceptar.setText("Aceptar");
        jbAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAceptarActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(sugerenciasPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jbAceptar)
                .addGap(115, 115, 115)
                .addComponent(jbCancelar)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sugerenciasPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbCancelar)
                    .addComponent(jbAceptar))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>

private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                           
  this.setVisible(false);
}                                          

private void jbAceptarActionPerformed(java.awt.event.ActionEvent evt) {
   Sugerencia s;
   s=this.sugerenciasPanel1.getSugerencia();
   String d="\n[Entidad :"+AppContext.getIdEntidad()+
            " Municipio: "+AppContext.getIdMunicipio()+ " " +
            " Usuario: " +AppContext.getApplicationContext().getUserPreference("LAST_LOGIN",null,false)+ "]";
   
   s.addDetallesAdicionales(d);
   try {
	 
     MantisConnectModeloClient.createIssue(s);
   }catch (Exception ex) {
	   ErrorDialog.show(this, "ERROR", "ERROR", StringUtil.stackTrace(ex));
   }
   
   this.setVisible(false);

}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SugerenciasForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SugerenciasForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SugerenciasForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SugerenciasForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new SugerenciasForm().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jbAceptar;
    private javax.swing.JButton jbCancelar;
    private com.geopista.app.sugerencias.SugerenciasPanel sugerenciasPanel1;
    // End of variables declaration
}
