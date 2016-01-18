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
 * EventsForm.java
 * 
 * Created on 11 marzo 2004, 0.04
 *
 */

package it.businesslogic.ireport.gui;
import it.businesslogic.ireport.*;
import javax.swing.tree.*;
import java.util.*;
import it.businesslogic.ireport.util.I18n;
import it.businesslogic.ireport.util.LanguageChangedEvent;
import it.businesslogic.ireport.util.LanguageChangedListener;

/**
 *
 * @author  Administrator
 */
public class EventsForm extends javax.swing.JFrame {
    
    /** Creates new form EventsForm */
    public EventsForm() {
        initComponents();
        
        // We add all common events...
        jComboBoxEvents.addItem(I18n.getString("EventsForm.declarationsItem", "<Imports and global declarations>") );
        jComboBoxEvents.addItem("afterColumnInit()");
        jComboBoxEvents.addItem("afterDetailEval()"); 
        jComboBoxEvents.addItem("afterGroupInit(java.lang.String groupName)"); 
        jComboBoxEvents.addItem("afterPageInit()"); 
        jComboBoxEvents.addItem("afterReportInit()"); 
        jComboBoxEvents.addItem("beforeColumnInit()"); 
        jComboBoxEvents.addItem("beforeDetailEval()"); 
        jComboBoxEvents.addItem("beforeGroupInit(java.lang.String groupName)"); 
        jComboBoxEvents.addItem("beforePageInit()"); 
        jComboBoxEvents.addItem("beforeReportInit()");  
        
        I18n.addOnLanguageChangedListener( new LanguageChangedListener() {
            public void languageChanged(LanguageChangedEvent evt) {
                applyI18n();
            }
        } );
        
        applyI18n();
        
        org.syntax.jedit.SyntaxDocument sd = new org.syntax.jedit.SyntaxDocument();
        sd.setTokenMarker(new org.syntax.jedit.tokenmarker.JavaTokenMarker() );
        this.jEditTextArea1.setDocument( sd );
        
        this.jTree1.setCellRenderer(new DocumentExpressionEditorTreeCellRenderer());
        
        this.jTree1.setModel(new javax.swing.tree.DefaultTreeModel(new DefaultMutableTreeNode("")));
      
        // OPEN this window with a size of 75% x 75% of the screen...
        java.awt.Toolkit tk = java.awt.Toolkit.getDefaultToolkit();
        this.setSize((int)(tk.getScreenSize().getWidth()*0.75), 
                     (int)(tk.getScreenSize().getHeight()*0.75));
        
        it.businesslogic.ireport.util.Misc.centerFrame(this);
        
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxEvents = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditTextArea1 = new org.syntax.jedit.JEditTextArea();
        jButtonSave = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jSplitPane1.setDividerSize(6);
        jSplitPane1.setResizeWeight(0.2);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Events");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 0, 4);
        jPanel1.add(jLabel1, gridBagConstraints);

        jComboBoxEvents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEventsActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 2, 4, 2);
        jPanel1.add(jComboBoxEvents, gridBagConstraints);

        jEditTextArea1.setMinimumSize(new java.awt.Dimension(20, 20));
        jEditTextArea1.setPreferredSize(new java.awt.Dimension(150, 150));
        jScrollPane1.setViewportView(jEditTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 2);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 2, 4);
        jPanel1.add(jButtonSave, gridBagConstraints);

        jSplitPane1.setRightComponent(jPanel1);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Objects");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 0, 4);
        jPanel2.add(jLabel2, gridBagConstraints);

        jTree1.setRootVisible(false);
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });

        jScrollPane2.setViewportView(jTree1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel2.add(jScrollPane2, gridBagConstraints);

        jSplitPane1.setLeftComponent(jPanel2);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        
         int portion = getSelectedPortion();
        jrf.getReport().getScripletCode().setPortionCode( portion,  this.jEditTextArea1.getText() );
        jComboBoxEventsActionPerformed(new java.awt.event.ActionEvent(jButtonSave,0,""));
        jrf.getReport().incrementReportChanges();
        
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
       
       if (evt.getClickCount() == 2 && evt.getButton() == java.awt.event.MouseEvent.BUTTON1)
       {
          TreePath path = jTree1.getSelectionPath();
          if (path == null) return;
          TreeNode node = (TreeNode)path.getLastPathComponent();
          if (node instanceof DefaultMutableTreeNode)
          {
             DefaultMutableTreeNode nodem = (DefaultMutableTreeNode)node;
             Object obj = nodem.getUserObject();
             if (obj != null && obj instanceof JRVariable)
             {
                 String s = "getVariableValue(\""+ obj +"\")";
                 if (((JRVariable)obj).getClassType() != null && ((JRVariable)obj).getClassType().length() > 0)
                {
                    s = "("+ ((JRVariable)obj).getClassType() + ")" + s;
                }
                try
                {
                    jEditTextArea1.getDocument().insertString( jEditTextArea1.getCaretPosition(), s+"", null);
                } catch (Exception ex){}
             }
             else if (obj != null && obj instanceof JRParameter)
             {
                String s = "(" + ((JRParameter)obj).getClassType() + ")getParameterValue(\""+ obj +"\")";
                try
                {
                    jEditTextArea1.getDocument().insertString( jEditTextArea1.getCaretPosition(), s+"", null);
                } catch (Exception ex){}
             }
             else if (obj != null && obj instanceof JRField)
             {
                 
                String s = "(" + ((JRField)obj).getClassType() + ")getFieldValue(\""+ obj +"\")";
                
                try
                {
                    jEditTextArea1.getDocument().insertString( jEditTextArea1.getCaretPosition(), s+"", null);
                } catch (Exception ex){}
             }
          }
       }
        
    }//GEN-LAST:event_jTree1MouseClicked

    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
      
       
    }//GEN-LAST:event_jTree1ValueChanged

    private void jComboBoxEventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEventsActionPerformed
        if (jrf == null) return;
        int portion = getSelectedPortion();
        this.jEditTextArea1.setText( jrf.getReport().getScripletCode().getPortion( portion ));
    }//GEN-LAST:event_jComboBoxEventsActionPerformed
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        setVisible(false);
    }//GEN-LAST:event_exitForm
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new EventsForm().setVisible(true);
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxEvents;
    private org.syntax.jedit.JEditTextArea jEditTextArea1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
    
    private JReportFrame jrf;
    
    public int getSelectedPortion()
    {
        return (int)Math.max(0,jComboBoxEvents.getSelectedIndex());
    }
    
    /** Getter for property jrf.
     * @return Value of property jrf.
     *
     */
    public it.businesslogic.ireport.gui.JReportFrame getJrf() {
        return jrf;
    }
    
    /** Setter for property jrf.
     * @param jrf New value of property jrf.
     *
     */
    public void setJReportFrame(it.businesslogic.ireport.gui.JReportFrame jrf) {
        this.jrf = jrf;
        
        if (jrf == null || 
            jrf.getReport().getScriptletHandling() != Report.SCRIPTLET_IREPORT_INTERNAL_HANDLING ||
            jrf.getReport().getScripletCode() == null)
        {
            this.setVisible(false);
            return;
        }          
                
        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)jTree1.getModel().getRoot();
        dmtn.removeAllChildren();
        
        DefaultMutableTreeNode dmtnFields = new DefaultMutableTreeNode("Fields");
        Enumeration enumFields = jrf.getReport().getFields().elements();
        while (enumFields.hasMoreElements())
        {
           dmtnFields.add( new DefaultMutableTreeNode(enumFields.nextElement()));
        }
        dmtn.add( dmtnFields );
        
        
        DefaultMutableTreeNode dmtnVariables = new DefaultMutableTreeNode("Variables");
        Enumeration enumVariables = jrf.getReport().getVariables().elements();
        while (enumVariables.hasMoreElements())
        {
           dmtnVariables.add( new DefaultMutableTreeNode(enumVariables.nextElement()));
        }
        dmtn.add( dmtnVariables );
        
        
        DefaultMutableTreeNode dmtnParameters = new DefaultMutableTreeNode("Parameters");
        Enumeration enumParameters = jrf.getReport().getParameters().elements();
        while (enumParameters.hasMoreElements())
        {
           dmtnParameters.add( new DefaultMutableTreeNode(enumParameters.nextElement()));
        }
        dmtn.add( dmtnParameters );

        jTree1.updateUI();
        
        int portion = getSelectedPortion();
        this.jEditTextArea1.setText( jrf.getReport().getScripletCode().getPortion( portion ));
        this.jEditTextArea1.setCaretPosition(0);
        this.jEditTextArea1.updateUI();
        this.jEditTextArea1.updateScrollBars();

    }
    
    public void applyI18n(){
                // Start autogenerated code ----------------------
                jButtonSave.setText(I18n.getString("eventsForm.buttonSave","Save"));
                jLabel1.setText(I18n.getString("eventsForm.label1","Events"));
                jLabel2.setText(I18n.getString("eventsForm.label2","Objects"));
                // End autogenerated code ----------------------
    }
}