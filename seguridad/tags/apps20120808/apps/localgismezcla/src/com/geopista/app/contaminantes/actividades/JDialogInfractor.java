/**
 * The GEOPISTA project is a set of tools and applications to manage
 * geographical data for local administrations.
 *
 * Copyright (C) 2004 INZAMAC-SATEC UTE
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307,
 USA.
 *
 * For more information, contact:
 *
 *
 * www.geopista.com
 *
 *
 */
package com.geopista.app.contaminantes.actividades;

import com.geopista.protocol.licencias.CPersonaJuridicoFisica;
import com.geopista.app.utilidades.JDialogPersona;

import javax.swing.*;
import java.util.ResourceBundle;
import java.awt.*;

/**
 *
 * @author  angeles
 */
public class JDialogInfractor extends javax.swing.JDialog {
    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(JDialogInfractor.class);
    ResourceBundle messages;
    Frame parent;
    CPersonaJuridicoFisica persona=null;
    private boolean aceptado=false;

    /** Creates new form JDialogInfractor */
    public JDialogInfractor(java.awt.Frame parent, boolean modal, ResourceBundle messages, CPersonaJuridicoFisica persona) {
        super(parent, modal);
        this.parent=parent;
        this.messages=messages;
        initComponents();
        changeScreenLang(messages);
        load(persona);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanelBotonera = new javax.swing.JPanel();
        jButtonAceptar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jPanelDatos = new javax.swing.JPanel();
        jLabelDni = new javax.swing.JLabel();
        jTextFieldDni = new TextField(10);
        jLabelNombre = new javax.swing.JLabel();
        jTextFieldNombre = new TextField(32);
        jLabelApellido1 = new javax.swing.JLabel();
        jTextFieldApellido1 = new TextField(32);
        jLabelApellido2 = new javax.swing.JLabel();
        jTextFieldApellido2 = new TextField(32);
        jButtonBuscar = new javax.swing.JButton();

        Icon iconoZoom=null;
        try
        {
            ClassLoader cl =this.getClass().getClassLoader();
            iconoZoom = new javax.swing.ImageIcon(cl.getResource("img/zoom.gif"));
            jButtonBuscar.setIcon(iconoZoom);
        }catch(Exception e){}

        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarInfractor();
            }
        });
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
                           public void actionPerformed(java.awt.event.ActionEvent evt) {
                               aceptar();
                           }
                       });

        jPanelBotonera.add(jButtonAceptar);
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
                           public void actionPerformed(java.awt.event.ActionEvent evt) {
                               aceptado=false;
                               dispose();
                           }
                       });
        jPanelBotonera.add(jButtonCancelar);

        getContentPane().add(jPanelBotonera, java.awt.BorderLayout.SOUTH);

        jPanelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelDatos.setBorder(new javax.swing.border.EtchedBorder());
        jPanelDatos.add(jLabelDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jPanelDatos.add(jTextFieldDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 120, -1));

        jPanelDatos.add(jLabelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jPanelDatos.add(jTextFieldNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 250, -1));

        jPanelDatos.add(jLabelApellido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jPanelDatos.add(jTextFieldApellido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 250, -1));

        jPanelDatos.add(jLabelApellido2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jPanelDatos.add(jTextFieldApellido2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 250, -1));
        jPanelDatos.add(jButtonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 20, 20));

        getContentPane().add(jPanelDatos, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents
    

    public void load(CPersonaJuridicoFisica persona)
    {
       if (persona==null) persona=new CPersonaJuridicoFisica();
       this.persona=persona;
       if (persona.getDniCif()!=null && persona.getDniCif().length()>0)
           jTextFieldDni.setEditable(false);
       jTextFieldDni.setText(persona.getDniCif()==null?"":persona.getDniCif());
       jTextFieldNombre.setText(persona.getNombre()==null?"":persona.getNombre());
       jTextFieldApellido1.setText(persona.getApellido1()==null?"":persona.getApellido1());
       jTextFieldApellido2.setText(persona.getApellido2()==null?"":persona.getApellido2());

    }
    public void setEditable(boolean bValor)
    {
       jTextFieldDni.setEditable(jTextFieldDni.isEditable()&&bValor);
        jTextFieldNombre.setEditable(bValor);
        jTextFieldApellido1.setEditable(bValor);
        jTextFieldApellido2.setEditable(bValor);
        jButtonBuscar.setEnabled(bValor);

    }
    private void aceptar()
        {
            /** chequeamos que el tamanno de los anexos para la inspeccion no exceda el tamanno maximo */
            if (jTextFieldDni.getText().length()==0 || jTextFieldNombre.getText().length()==0){
                //debe rellenar el dni y el nombre
            }
            else
            {
                save();
                aceptado=true;
                dispose();
            }
        }

     public boolean isAceptado() {
        return aceptado;
    }
    public void buscarInfractor()
    {
        JDialogPersona dialogPersona = new JDialogPersona(this.parent,true, messages);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        dialogPersona.setSize(500,500);
        dialogPersona.setLocation(d.width/2 - dialogPersona.getSize().width/2, d.height/2 - dialogPersona.getSize().height/2);
        dialogPersona.setResizable(false);
        dialogPersona.show();
        if (dialogPersona.isAceptado())
        {
            load(dialogPersona.getPersona());
        }
        dialogPersona=null;
    }
    public void save()
    {
        if (persona==null) persona= new CPersonaJuridicoFisica();
        persona.setDniCif(jTextFieldDni.getText().length()==0?null:jTextFieldDni.getText());
        persona.setNombre(jTextFieldNombre.getText().length()==0?null:jTextFieldNombre.getText());
        persona.setApellido1(jTextFieldApellido1.getText().length()==0?null:jTextFieldApellido1.getText());
        persona.setApellido2(jTextFieldApellido2.getText().length()==0?null:jTextFieldApellido2.getText());
    }
    public CPersonaJuridicoFisica getPersona()
    {
        return persona;
    }
    public void changeScreenLang(ResourceBundle messages)
    {
        try
        {
            this.messages=messages;
            jButtonAceptar.setText(messages.getString("CPersonaJDialog.aceptarJButton.text"));
            jLabelApellido1.setText(messages.getString("CPersonaJDialog.apellido1JLabel.text"));
            jLabelApellido2.setText(messages.getString("CPersonaJDialog.apellido2JLabel.text"));
            jLabelDni.setText(messages.getString("CPersonaJDialog.dniJLabel.text"));
            jLabelNombre.setText(messages.getString("CPersonaJDialog.nombreJLabel.text"));
            jButtonCancelar.setText(messages.getString("CPersonaJDialog.cancelarJButton.text"));

            jButtonAceptar.setToolTipText(messages.getString("CPersonaJDialog.aceptarJButton.text"));
            jButtonBuscar.setToolTipText(messages.getString("CPersonaJDialog.jButtonBuscar.text"));
            jButtonCancelar.setToolTipText(messages.getString("CPersonaJDialog.cancelarJButton.text"));
        }catch(Exception e)
        {
            logger.error("Error al escribir las etiquetas: ",e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JLabel jLabelApellido1;
    private javax.swing.JLabel jLabelApellido2;
    private javax.swing.JLabel jLabelDni;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JPanel jPanelBotonera;
    private javax.swing.JPanel jPanelDatos;
    private TextField jTextFieldApellido1;
    private TextField jTextFieldApellido2;
    private TextField jTextFieldDni;
    private TextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables

}