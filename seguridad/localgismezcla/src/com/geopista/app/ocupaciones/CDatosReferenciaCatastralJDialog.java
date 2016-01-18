/*
 * JSearch.java
 *
 * Created on 4 de mayo de 2004, 12:04
 */

package com.geopista.app.ocupaciones;

import com.geopista.protocol.licencias.*;


import java.util.Vector;
import java.util.Hashtable;
import java.util.Date;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.io.StringWriter;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author avivar
 */
public class CDatosReferenciaCatastralJDialog extends javax.swing.JDialog {

	Logger logger = Logger.getLogger(CDatosReferenciaCatastralJDialog.class);

	/**
	 * Creates new form JSearch
	 */
    public CDatosReferenciaCatastralJDialog(java.awt.Frame parent, boolean modal, CReferenciaCatastral referencia, boolean mostrarCheckBox) {
        super(parent, modal);
        initComponents();
        configureComponents(mostrarCheckBox, referencia);
    }


	private void configureComponents(boolean b, CReferenciaCatastral referenciaCatastral) {
        if (referenciaCatastral != null){
            tipoViaJTextField.setText(referenciaCatastral.getTipoVia());
            nombreViaJTextField.setText(referenciaCatastral.getNombreVia());
            numeroViaJTextField.setText(referenciaCatastral.getPrimerNumero());
            puertaJTextField.setText(referenciaCatastral.getPuerta());
            plantaJTextField.setText(referenciaCatastral.getPlanta());
            letraJTextField.setText(referenciaCatastral.getPrimeraLetra());
            bloqueJTextField.setText(referenciaCatastral.getBloque());
            escaleraJTextField.setText(referenciaCatastral.getEscalera());
        }

        emplazamientoJCheckBox.setVisible(b);
        emplazamientoJCheckBox.setEnabled(b);
        emplazamientoJCheckBox.setSelected(false);

		renombrarComponentes();
}

	/**
	 * This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    private void initComponents() {//GEN-BEGIN:initComponents
        datosCatastralesJPanel = new javax.swing.JPanel();
        nombreViaJLabel = new javax.swing.JLabel();
        tipoViaJTextField = new javax.swing.JTextField();
        nombreViaJTextField = new javax.swing.JTextField();
        numeroViaJLabel = new javax.swing.JLabel();
        numeroViaJTextField = new javax.swing.JTextField();
        puertaJTextField = new javax.swing.JTextField();
        plantaJTextField = new javax.swing.JTextField();
        letraJTextField = new javax.swing.JTextField();
        bloqueJTextField = new javax.swing.JTextField();
        escaleraJTextField = new javax.swing.JTextField();
        escaleraJLabel = new javax.swing.JLabel();
        separadorJLabel = new javax.swing.JLabel();
        separadorJLabel1 = new javax.swing.JLabel();
        separadorJLabel2 = new javax.swing.JLabel();
        separadorJLabel3 = new javax.swing.JLabel();
        separadorJLabel4 = new javax.swing.JLabel();
        salirJButton = new javax.swing.JButton();
        emplazamientoJCheckBox = new javax.swing.JCheckBox();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Referencia Catastral");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        datosCatastralesJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        datosCatastralesJPanel.setBorder(new javax.swing.border.TitledBorder("Datos Referencia Catastral"));
        nombreViaJLabel.setText("Tipo v\u00eda / Nombre V\u00eda:");
        datosCatastralesJPanel.add(nombreViaJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, 20));

        tipoViaJTextField.setEditable(false);
        tipoViaJTextField.setBorder(null);
        datosCatastralesJPanel.add(tipoViaJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 110, 20));

        nombreViaJTextField.setEditable(false);
        nombreViaJTextField.setBorder(null);
        datosCatastralesJPanel.add(nombreViaJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 260, 20));

        numeroViaJLabel.setText("N\u00ba / Letra / Bloque:");
        datosCatastralesJPanel.add(numeroViaJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 180, 20));

        numeroViaJTextField.setEditable(false);
        numeroViaJTextField.setBorder(null);
        datosCatastralesJPanel.add(numeroViaJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 110, 20));

        puertaJTextField.setEditable(false);
        puertaJTextField.setBorder(null);
        datosCatastralesJPanel.add(puertaJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 110, 20));

        plantaJTextField.setEditable(false);
        plantaJTextField.setBorder(null);
        datosCatastralesJPanel.add(plantaJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 110, 20));

        letraJTextField.setEditable(false);
        letraJTextField.setBorder(null);
        datosCatastralesJPanel.add(letraJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 110, 20));

        bloqueJTextField.setEditable(false);
        bloqueJTextField.setBorder(null);
        datosCatastralesJPanel.add(bloqueJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 110, 20));

        escaleraJTextField.setEditable(false);
        escaleraJTextField.setBorder(null);
        datosCatastralesJPanel.add(escaleraJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 110, 20));

        escaleraJLabel.setText("Escalera / Planta / Puerta:");
        datosCatastralesJPanel.add(escaleraJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 180, 20));

        separadorJLabel.setText("/");
        datosCatastralesJPanel.add(separadorJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 10, 20));

        separadorJLabel1.setText("/");
        datosCatastralesJPanel.add(separadorJLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 10, 20));

        separadorJLabel2.setText("/");
        datosCatastralesJPanel.add(separadorJLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 10, 20));

        separadorJLabel3.setText("/");
        datosCatastralesJPanel.add(separadorJLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 10, 20));

        separadorJLabel4.setText("/");
        datosCatastralesJPanel.add(separadorJLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 10, 20));

        getContentPane().add(datosCatastralesJPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 600, 110));

        salirJButton.setText("Salir");
        salirJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirJButtonActionPerformed(evt);
            }
        });

        getContentPane().add(salirJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, -1, -1));

        emplazamientoJCheckBox.setText("Dir. Emplazamiento");
        getContentPane().add(emplazamientoJCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 490, -1));

        pack();
    }//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void salirJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirJButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_salirJButtonActionPerformed


    public boolean esDirEmplazamiento(){
        if (emplazamientoJCheckBox.isSelected()) return true;
        return false;
    }


	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		new CHistoricoJDialog(new javax.swing.JFrame(), true).show();
	}

	private DefaultTableModel expedientesJTableModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bloqueJTextField;
    private javax.swing.JPanel datosCatastralesJPanel;
    private javax.swing.JCheckBox emplazamientoJCheckBox;
    private javax.swing.JLabel escaleraJLabel;
    private javax.swing.JTextField escaleraJTextField;
    private javax.swing.JTextField letraJTextField;
    private javax.swing.JLabel nombreViaJLabel;
    private javax.swing.JTextField nombreViaJTextField;
    private javax.swing.JLabel numeroViaJLabel;
    private javax.swing.JTextField numeroViaJTextField;
    private javax.swing.JTextField plantaJTextField;
    private javax.swing.JTextField puertaJTextField;
    private javax.swing.JButton salirJButton;
    private javax.swing.JLabel separadorJLabel;
    private javax.swing.JLabel separadorJLabel1;
    private javax.swing.JLabel separadorJLabel2;
    private javax.swing.JLabel separadorJLabel3;
    private javax.swing.JLabel separadorJLabel4;
    private javax.swing.JTextField tipoViaJTextField;
    // End of variables declaration//GEN-END:variables



		private void renombrarComponentes() {

		try {

			setTitle(CMainOcupaciones.literales.getString("CDatosReferenciaCatastralJDialog.JInternalFrame.title"));
			datosCatastralesJPanel.setBorder(new javax.swing.border.TitledBorder(CMainOcupaciones.literales.getString("CDatosReferenciaCatastralJDialog.datosCatastralesJPanel.TitleBorder")));
			nombreViaJLabel.setText(CMainOcupaciones.literales.getString("CDatosReferenciaCatastralJDialog.nombreViaJLabel.text"));
			numeroViaJLabel.setText(CMainOcupaciones.literales.getString("CDatosReferenciaCatastralJDialog.numeroViaJLabel.text"));
			escaleraJLabel.setText(CMainOcupaciones.literales.getString("CDatosReferenciaCatastralJDialog.escaleraJLabel.text"));
			salirJButton.setText(CMainOcupaciones.literales.getString("CDatosReferenciaCatastralJDialog.salirJButton.text"));
            emplazamientoJCheckBox.setText(CMainOcupaciones.literales.getString("CDatosReferenciaCatastralJDialog.DirEmplazamientoJCheckBox.text"));

		} catch (Exception ex) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			logger.error("Exception: " + sw.toString());
		}
	}
}