/*
 * Copyright (C) 2005 - 2007 JasperSoft Corporation.  All rights reserved. 
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased a commercial license agreement from JasperSoft,
 * the following license terms apply:
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * This program is distributed WITHOUT ANY WARRANTY; and without the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see http://www.gnu.org/licenses/gpl.txt
 * or write to:
 *
 * Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330,
 * Boston, MA  USA  02111-1307
 *
 *
 *
 *
 * TextWizardForm.java
 * 
 * Created on 30 luglio 2004, 16.34
 *
 */

package it.businesslogic.ireport.plugin.textwizard;
import it.businesslogic.ireport.Band;
import it.businesslogic.ireport.IReportConnection;
import it.businesslogic.ireport.JRField;
import it.businesslogic.ireport.ReportElement;
import it.businesslogic.ireport.StaticTextReportElement;
import it.businesslogic.ireport.TextFieldReportElement;
import it.businesslogic.ireport.gui.event.ReportElementChangedEvent;
import it.businesslogic.ireport.undo.InsertElementOperation;
import it.businesslogic.ireport.util.I18n;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author  Administrator
 */
public class TextWizardForm extends javax.swing.JDialog {
    
    /** Creates new form TextWizardForm */
    public TextWizardForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        applyI18n();
        
        javax.swing.SpinnerNumberModel smodel = new javax.swing.SpinnerNumberModel();
        smodel.setMinimum(new Integer(0));
        smodel.setMaximum(new Integer(1000000));
        jSpinner1.setModel( smodel );
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(10, 70));
        jLabel1.setText("Max report width (chars): ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jSpinner1.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        jPanel1.add(jSpinner1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 0);
        jPanel1.add(jSeparator1, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Check for fields widths");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanel3.add(jButton1, gridBagConstraints);

        jButton2.setText("Add elements");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanel3.add(jButton2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel4.add(jPanel1, java.awt.BorderLayout.NORTH);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Column", "Display size", "Cut to..."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        // Per ogni campo creiamo etichetta nelle banda delle etichette e un adeguato textfield nella
        // banda di dettaglio...
        
        // Rimuoviamo tutti i campi dalla banda delle colonne....
        if (it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame() == null)
        {
            javax.swing.JOptionPane.showMessageDialog(this,
                    I18n.getString("messages.textWizardForm.noReportFrame",
                    "No report frame selected!") );
            return;
        }
        
        
        int count_chars = 0;
        
        int[] selectedRows = jTable1.getSelectedRows();
        for (int i=0; i<jTable1.getSelectedRowCount(); ++i)
        {
            count_chars += ((Integer)jTable1.getValueAt(selectedRows[i],2)).intValue();
        }
        
        if (count_chars > ((Integer)jSpinner1.getValue()).intValue() )
        {
            if (javax.swing.JOptionPane.OK_OPTION != javax.swing.JOptionPane.showConfirmDialog(this,
                    I18n.getString("messages.textWizardForm.outOfMargin","Fields will go out of margins. Continue anyway?") ) )
            {
                return;
            }
        }
        
        
        Vector elements = it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame().getReport().getElements();
        Enumeration enum_elements = elements.elements();
        Vector elements_to_remove = new Vector();
        while (enum_elements.hasMoreElements())
        {
            ReportElement element = (ReportElement)enum_elements.nextElement();
            if (element.getBand().getName().equalsIgnoreCase("columnHeader") ||
                element.getBand().getName().equalsIgnoreCase("detail"))
            {
                elements_to_remove.add(element);
            }
        }
        
        it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame().setSelectedElements( elements_to_remove );
        it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame().deleteSelectedElements();
        
        int left_char = 0;
        Band columnHeader = it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame().getReport().getBandByName("columnHeader");
        Band detail = it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame().getReport().getBandByName("detail");
        for (int i=0; i<jTable1.getSelectedRowCount(); ++i)
        {
            // Aggiungiamo un elemento di etichetta e uno di testo....
            int cut_to = ((Integer)jTable1.getValueAt(selectedRows[i],2)).intValue();
            StaticTextReportElement stre = new StaticTextReportElement(left_char*10 + 10, columnHeader.getBandYLocation()+10, 10*cut_to,  20);
            stre.setBand( columnHeader);
            stre.setText( jTable1.getValueAt(selectedRows[i],0) + "");
            stre.setFontName("Monospaced");
            stre.setFontSize(14);
            
            it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame().fireReportListenerReportElementsChanged(new ReportElementChangedEvent(it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame(), stre , ReportElementChangedEvent.ADDED));
            it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame().addUndoOperation( new  InsertElementOperation( it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame(), stre ) );
            it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame().getReport().getElements().add(stre);
            
            TextFieldReportElement tfre = new TextFieldReportElement(left_char*10  + 10, detail.getBandYLocation()  + 10, 10*cut_to ,  20);
            tfre.setBand( detail);
            tfre.setFontName("Monospaced");
            tfre.setFontSize(14);
            String name = "$F{" + jTable1.getValueAt(selectedRows[i],0) + "}";
            
            // cerchiamo il campo e vediamo se e numerico...
            Enumeration fields = it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame().getReport().getFields().elements();
            
            boolean is_string = false;
            String type = "";
            
            while (fields.hasMoreElements())
            {
                JRField field = (JRField)fields.nextElement();
                if (field.getName().equalsIgnoreCase( jTable1.getValueAt(selectedRows[i],0)+"" ))
                {
                   if (field.getClassType().equalsIgnoreCase("java.lang.String"))
                       is_string = true;
                   type = field.getClassType();
                   break;
                }
            }
            
            if (is_string)
            {
                tfre.setText( "( ((" + name + "!=null) && (" +name+".length() > " +  cut_to + ")) ? " + name + ".substring(0,"+ cut_to +") : " + name + ")");
            }
            else
            {
                tfre.setText( name );
                
            }
            tfre.setClassExpression( type );
            
            it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame().fireReportListenerReportElementsChanged(new ReportElementChangedEvent(it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame(), tfre , ReportElementChangedEvent.ADDED));
            it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame().addUndoOperation( new  InsertElementOperation( it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame(), tfre ) );
            it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame().getReport().getElements().add(tfre);
            
            left_char += cut_to+1;
        }          
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel dtm = (DefaultTableModel)jTable1.getModel();
        dtm.setRowCount(0);
        
        
        
        if (it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame() == null)
        {
            javax.swing.JOptionPane.showMessageDialog(this,I18n.getString("messages.textWizardForm.noReportFrame",
                    "No report frame selected!"));
            return;
        }
        
        int chars = it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame().getReport().getWidth() / 10;
        ((javax.swing.SpinnerNumberModel)jSpinner1.getModel()).setValue( new Integer(chars) );
        
        IReportConnection conn = (IReportConnection)it.businesslogic.ireport.gui.MainFrame.getMainInstance().getProperties().get("DefaultConnection");
        if (!conn.isJDBCConnection()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    I18n.getString("messages.textWizardForm.noActiveJDBC",
                    "The active connection is not of type JDBC. Activate a JDBC connection first.")
                     );
            return;
        }
        
        String query = it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame().getReport().getQuery();

        
        if (query.toLowerCase().indexOf("where") >= 0)
        {
            query = query.substring(0, query.toLowerCase().lastIndexOf("where"));
        }
        
        //
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        try {
            con = conn.getConnection();
            stmt = con.createStatement();
            try {
            stmt.setMaxRows(1);
            } catch (Exception ex) { }
            rs = stmt.executeQuery(query);
            rsmd = rs.getMetaData();
            
            for (int i=1; i<=rsmd.getColumnCount(); ++i)
            {
                String name = rsmd.getColumnName( i );
                TextColumn ct = new TextColumn(name, rsmd.getColumnDisplaySize( i ));
                
                Enumeration fields = it.businesslogic.ireport.gui.MainFrame.getMainInstance().getActiveReportFrame().getReport().getFields().elements();
                boolean found = false;
            while (fields.hasMoreElements())
            {
                JRField field = (JRField)fields.nextElement();
                if (field.getName().equalsIgnoreCase( name ))
                {
                  found = true;
                }
            }
                if (found)
                dtm.addRow(new Object[]{ct, new Integer(ct.getSize()), new Integer(ct.getSize())});
            }
        } catch (Exception ex)
        {
            javax.swing.JOptionPane.showMessageDialog(this,
                    I18n.getFormattedString( "messages.textWizardForm.queryError",
                    "error during query execution: {0}",
                    new Object[]{ ex.getMessage() }) );
            ex.printStackTrace();
        } finally
        {

             try { if (stmt != null) stmt.close(); } catch (Exception ex){}
             try { if (con != null) con.close(); } catch (Exception ex){}
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed
    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new TextWizardForm(new javax.swing.JFrame(), true).setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    
    public void applyI18n(){
                // Start autogenerated code ----------------------
                jButton1.setText(I18n.getString("textWizardForm.button1","Check for fields widths"));
                jButton2.setText(I18n.getString("textWizardForm.button2","Add elements"));
                jLabel1.setText(I18n.getString("textWizardForm.label1","Max report width (chars): "));
                // End autogenerated code ----------------------
                
                jTable1.getColumnModel().getColumn(0).setHeaderValue(I18n.getString("textWizardForm.tablecolumn.column","Column") );
                jTable1.getColumnModel().getColumn(1).setHeaderValue(I18n.getString("textWizardForm.tablecolumn.displaySize","Display size") );
                jTable1.getColumnModel().getColumn(2).setHeaderValue(I18n.getString("textWizardForm.tablecolumn.cutTo","Cut to...") );
    }
}
